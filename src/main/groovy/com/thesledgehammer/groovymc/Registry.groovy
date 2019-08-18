package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.items.GroovyBlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntityType

class Registry {

    static TestBlock testBlock
    static TileEntityType<TileTest> testType;
    static TileTest test;

    static void init() {
        testBlock = new TestBlock();
        test = new TileTest(testType);
        EventRegistry.addToBlockList(testBlock, new GroovyBlockItem(testBlock, new Item.Properties().group(ItemGroup.MISC)), "testblock");
        EventRegistry.addToTileEntityTypeList(test, testBlock, "test");
    }
}
