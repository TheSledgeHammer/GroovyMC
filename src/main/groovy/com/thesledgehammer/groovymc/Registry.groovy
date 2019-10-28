package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.blocks.GroovyBlock
import com.thesledgehammer.groovymc.items.GroovyBlockItem
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.ForgeRegistries

import javax.annotation.Nullable

class Registry {

    static GroovyBlock block;

    static void init() {
        block = new GroovyBlock(Material.IRON);
        registerBlock(block, ItemGroup.MISC, "test");
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

    private static <T extends Block> T registerBlock(T block, ItemGroup itemGroup, String name) {
        return registerBlock(block, new GroovyBlockItem(block, new Item.Properties().group(itemGroup)), name);
    }

    private static <T extends Block> T registerBlock(T block, String name) {
        return registerBlock(block, new GroovyBlockItem(block, new Item.Properties().group(ItemGroup.MISC)), name);
    }

    private static <T extends TileEntity> T registerTileEntity(T tile, @Nullable Block block, @Nullable ItemGroup itemGroup, String name) {
        ForgeRegistries.TILE_ENTITIES.register(tile.getType().setRegistryName(GroovyMC.MOD_ID, name));
        if(block != null && itemGroup != null) {
            registerBlock(block, itemGroup, name);
        } else {
            registerBlock(block, name);
        }
        return tile;
    }

    private static <T extends TileEntityType> T registerTileEntityType(T tileEntityType, @Nullable Block block, @Nullable ItemGroup itemGroup, String name) {
        tileEntityType.setRegistryName(GroovyMC.MOD_ID, name);
        ForgeRegistries.TILE_ENTITIES.register(tileEntityType);
        if(block != null && itemGroup != null) {
            registerBlock(block, itemGroup, name);
        } else {
            registerBlock(block, name);
        }
        return tileEntityType;
    }
}
