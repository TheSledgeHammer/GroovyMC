package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.items.GroovyBlockItem
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.registries.ForgeRegistries

import javax.annotation.Nullable

class Registry {

    static void init() {

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
        return registerBlock(block, new GroovyBlockItem(block, new Item.Properties().group(ItemGroup.MISC)), name);
    }

    private static <T extends TileEntity> T registerTileEntity(T tile, @Nullable Block block, String name) {
        ForgeRegistries.TILE_ENTITIES.register(tile.getType().setRegistryName(GroovyMC.MOD_ID, name));
        if(block != null) {
            registerBlock(block, name);
        }
        return tile;
    }
}
