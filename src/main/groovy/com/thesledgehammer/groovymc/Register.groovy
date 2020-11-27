package com.thesledgehammer.groovymc

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import com.thesledgehammer.groovyforge.FMLGroovyModLoadingContext
import com.thesledgehammer.groovymc.blocks.GroovyBlock
import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.block.Block
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.ContainerType
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

class Register {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    private static final DeferredRegister<TileEntityType<? extends TileEntity>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);
    private static final DeferredRegister<ContainerType<? extends Container>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    /* Table of Objects Registered */
    private static final Table<String, Item, RegistryObject<? extends Item>> ITEM_TABLE = HashBasedTable.create();
    private static final Table<String, Block, RegistryObject<? extends Block>> BLOCK_TABLE = HashBasedTable.create();
    private static final Table<String, TileEntity, RegistryObject<TileEntityType<? extends TileEntity>>> TILE_TABLE = HashBasedTable.create();
    private static final Table<String, Container, RegistryObject<ContainerType<? extends Container>>> CONTAINER_TABLE = HashBasedTable.create();

    static void RegisterInit(IEventBus eventBus) {
        BLOCKS.register(FMLGroovyModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLGroovyModLoadingContext.get().getModEventBus());
        TILES.register(FMLGroovyModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLGroovyModLoadingContext.get().getModEventBus());
        Init();
    }

    static void Init() {
        registerItem("test", new TestItem());
    }

    static <I extends Item> void registerItem(String name, I item) {
        RegistryObject<I> registryObject = ITEMS.register(name, () -> item);
        ITEM_TABLE.put(name, item, registryObject);
    }

    static <B extends Block> void registerBlock(String name, B block) {
        RegistryObject<B> registryObject = BLOCKS.register(name, () -> block);
        BLOCK_TABLE.put(name, block, registryObject);
    }

    static <T extends TileEntity> void registerTile(String name, T tile , String blockName, Block block) {
        RegistryObject<TileEntityType<? extends TileEntity>> registryObject = TILES.register(name, () -> TileEntityType.Builder.create(() -> tile, BLOCK_TABLE.get(blockName, block).get()).build(null));
        TILE_TABLE.put(name, tile, registryObject);
    }

    static <C extends Container> void registerContainer(String name, C container) {
        RegistryObject<ContainerType<? extends Container>> registryObject = CONTAINERS.register(name, () -> ContainerType.create(() -> container));
        CONTAINER_TABLE.put(name, container, registryObject);
    }

    static <I extends Item> RegistryObject getRegisteredItem(String name, I item) {
        if(ITEM_TABLE.contains(name, item)) {
            return ITEM_TABLE.get(name, item);
        }
        return null;
    }

    static <B extends Block> RegistryObject getRegisteredBlock(String name, B block) {
        if(BLOCK_TABLE.contains(name, block)) {
            return BLOCK_TABLE.get(name, block);
        }
        return null;
    }

    static <T extends TileEntity> RegistryObject getRegisteredTile(String name, T tile) {
        if(TILE_TABLE.contains(name, tile)) {
            return TILE_TABLE.get(name, tile);
        }
        return null;
    }

    static <C extends Container> RegistryObject getRegisteredContainer(String name, C container) {
        if(CONTAINER_TABLE.contains(name, container)) {
            return CONTAINER_TABLE.get(name, container);
        }
        return null;
    }
}
