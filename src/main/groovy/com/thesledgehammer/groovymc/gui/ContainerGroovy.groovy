/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.gui

import com.sun.jna.platform.win32.WinUser
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ClickType
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

abstract class ContainerGroovy extends Container {
	
	int sizeOfInventory = 9;
	
	protected void addPlayerInventory(InventoryPlayer playerInventory, int xInv, int yInv) {
		// Player inventory
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 9; column++) {
				addSlotToContainer(new Slot(playerInventory, column + row * 9 + 9, xInv + column * 18, yInv + row * 18));
			}
		}
		// Player hotbar
		for (int column = 0; column < 9; column++) {
			addHotbarSlot(playerInventory, column, xInv + column * 18, yInv + 58);
		}
	}
	
	protected void addHotbarSlot(InventoryPlayer playerInventory, int slot, int x, int y) {
		addSlotToContainer(new Slot(playerInventory, slot, x, y));
	}
	
	@Override
	ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	@Override
	final ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			itemstack = stack1.copy();
			
			if(index < WinUser.SIZE) {
				if(!this.mergeItemStack(stack1, sizeOfInventory, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if(!this.mergeItemStack(stack1, 0, sizeOfInventory, false)) {
				return null;
			}
			if(stack1.getMaxStackSize() == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	int setContainerInventorySize(int sizeOfInventory) {
		this.sizeOfInventory = sizeOfInventory;
		return sizeOfInventory;
	}
}
