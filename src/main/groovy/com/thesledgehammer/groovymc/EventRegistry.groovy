package com.thesledgehammer.groovymc


import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import javax.annotation.Nullable

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
class EventRegistry {

    private static List<Item> ITEMLIST = new ArrayList<>();
    private static List<Block> BLOCKLIST = new ArrayList<>();
    private static List<TileEntityType<? extends TileEntity>> TILEENTITYLIST = new ArrayList<>()

    @SubscribeEvent
    static void init() {
        addItem(new TestItem(), "othertest");
        addItem(new GroovyItem(ItemGroup.MISC), "groovyitem");
    }

    static <I extends Item> void addItem(I item, String name) {
        ITEMLIST.add(item.setRegistryName(Constants.MOD_ID, name));

    }

    static <B extends Block> void addBlock(B block, String name) {
        BLOCKLIST.add(block.setRegistryName(Constants.MOD_ID, name));
    }

    static <T extends TileEntity> void addTileEntity(T tile, @Nullable Block block, String name) {
        TILEENTITYLIST.add(TileEntityType.Builder.create(() -> tile, block).build(null).setRegistryName(Constants.MOD_ID, name));
    }

    @SubscribeEvent
    static void registerItemEvent(final RegistryEvent.Register<Item> event) {
        for(int i = 0; i < ITEMLIST.size(); i++) {
            event.getRegistry().register(ITEMLIST.get(i))
            Register.ITEMS.register(ITEMLIST.get(i).toString(), () -> ITEMLIST.get(i));
        }
    }

    @SubscribeEvent
    static void registerBlockEvent(final RegistryEvent.Register<Block> event) {
        for(int i = 0; i < BLOCKLIST.size(); i++) {
            event.getRegistry().register(BLOCKLIST.get(i))
        }
    }

    @SubscribeEvent
    static void registerTileEntityEvent(final RegistryEvent.Register<TileEntityType<? extends TileEntity>> event) {
        for(int i = 0; i < TILEENTITYLIST.size(); i++) {
           event.getRegistry().register(TILEENTITYLIST.get(i))
        }
    }
}
