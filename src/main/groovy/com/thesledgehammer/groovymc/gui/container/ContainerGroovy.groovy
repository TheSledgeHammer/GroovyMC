/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.gui.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ClickType
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

abstract class ContainerGroovy extends Container {
	
	private int sizeOfInventory = 9;
	
	protected void addPlayerInventory(InventoryPlayer playerInventory, int xInv, int yInv) {
		// Player inventory
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 9; column++) {
				addSlot(new Slot(playerInventory, column + row * 9 + 9, xInv + column * 18, yInv + row * 18));
			}
		}
		// Player hotbar
		for (int column = 0; column < 9; column++) {
			addHotbarSlot(playerInventory, column, xInv + column * 18, yInv + 58);
		}
	}
	
	protected void addHotbarSlot(InventoryPlayer playerInventory, int slot, int x, int y) {
		addSlot(new Slot(playerInventory, slot, x, y));
	}

	@Override
	Slot addSlot(Slot slot) {
		return super.addSlot(slot);
	}

	@Override
	ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	/*
	@Override
	final ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			itemstack = stack1.copy();
			
			if(index < sizeOfInventory) {
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
	*/
	
	void setContainerInventorySize(int sizeOfInventory) {
		this.sizeOfInventory = sizeOfInventory;
	}

	int getContainerInventorySize() {
		return sizeOfInventory;
	}
}
