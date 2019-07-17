package com.thesledgehammer.groovymc

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.registries.ForgeRegistries

import javax.annotation.Nullable

class Registry {

    static Block1 block1;
    static TileEntityType<TileEntityTest1> TEST;

    static void init() {
        MinecraftForge.EVENT_BUS.register(Registry.class);
        block1 = registerBlock(new Block1(),"test1");
    }

    private static <T extends Item> T registerItem(T item, String name) {
        item.setRegistryName(GroovyMC.MOD_ID, name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

    private static <T extends Block> T registerBlock(T block, @Nullable BlockItem blockItem, String name) {
        block.setRegistryName(GroovyMC.MOD_ID, name);
        ForgeRegistries.BLOCKS.register(block);

        if(blockItem != null) {
            blockItem.setRegistryName(GroovyMC.MOD_ID, name);
            ForgeRegistries.ITEMS.register(blockItem);
        }
        return block;
    }

    private static <T extends Block> T registerBlock(T block, String name) {
        return registerBlock(block, new BlockItem(block, new Item.Properties().group(ItemGroup.MISC)), name);
    }

    @SubscribeEvent
    static void registerTiles(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(TileEntityTest1.&new, Registry.block1).build(null).setRegistryName("test1"));
    }
}
