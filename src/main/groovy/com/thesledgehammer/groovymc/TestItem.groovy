package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemGroup

class TestItem extends GroovyItem {

    TestItem() {
        super(new Properties().group(ItemGroup.MISC))
    }
}
