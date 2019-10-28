package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.items.GroovyBlockItem
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.registries.ForgeRegistries

import javax.annotation.Nullable

//@Mod.EventBusSubscriber(modid = GroovyMC.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
class EventRegistry {
/*
    private static List<TileEntityType<?>> tileEntities = new ArrayList<>()
    private static List<Block> blocks = new ArrayList<>()
    private static List<Item> items = new ArrayList<>()

    static addToItemList(Item item, String name) {
        items.add(item.setRegistryName(GroovyMC.MOD_ID, name))
    }

    static void addToBlockList(Block block, @Nullable BlockItem blockItem, String name) {
        blocks.add(block.setRegistryName(GroovyMC.MOD_ID, name))
        addToItemList(blockItem, name)
        if(blockItem != null) {

        }
    }

    static void addToTileEntityTypeList(TileEntity tile, @Nullable Block block, String name) {
        tileEntities.add(TileEntityType.Builder.create(tile.&new, block).build(null).setRegistryName(GroovyMC.MOD_ID, name))
    }

    static void addToItemList(Item item, String modID, String name) {
        items.add(item.setRegistryName(modID, name))
    }

    static void addToBlockList(Block block,  @Nullable BlockItem blockItem, String modID, String name) {
        blocks.add(block.setRegistryName(modID, name))
        if(blockItem != null) {
            addToItemList(blockItem, name)
        }
    }

    static void addToTileEntityTypeList(TileEntity tile, @Nullable Block block, String modID, String name) {
        tileEntities.add(TileEntityType.Builder.create(tile.&new, block).build(null).setRegistryName(modID, name))
    }

    @SubscribeEvent
    static void registerItemEvent(final RegistryEvent.Register<Item> event) {
        for(int i = 0; i < items.size(); i++) {
           event.getRegistry().register(items.get(i))
        }
    }

    @SubscribeEvent
    static void registerBlockEvent(final RegistryEvent.Register<Block> event) {
        for(int i = 0; i < blocks.size(); i++) {
            event.getRegistry().register(blocks.get(i))
        }
    }

    @SubscribeEvent
    static void registerTileEntityEvent(final RegistryEvent.Register<TileEntityType<?>> event) {
        for(int i = 0; i < tileEntities.size(); i++) {
           event.getRegistry().register(tileEntities.get(i))
        }
    }
*/

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
