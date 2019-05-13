package com.thesledgehammer.groovymc.gui.slots

import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot

class SlotGroovy extends Slot {

    SlotGroovy(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition)
    }
}
