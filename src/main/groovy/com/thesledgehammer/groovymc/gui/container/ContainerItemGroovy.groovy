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

import com.thesledgehammer.groovymc.gui.inventory.InventoryAdaptor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ClickType
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ContainerItemGroovy<I extends Item> extends ContainerGroovy {
	
	protected final I item;
	
	protected ContainerItemGroovy(I item) {
		this.item = item;
		addPlayerInventory(playerInventory, xInv, yInv);
		setContainerInventorySize(getContainerInventorySize());
	}
	
	protected ContainerItemGroovy(I item, InventoryPlayer playerInventory, int xInv, int yInv) {
		this.item = item;
		
		addPlayerInventory(playerInventory, xInv, yInv);
		setContainerInventorySize(getContainerInventorySize());
	}
	
	@Override
	boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player) {
		ItemStack stack =  super.slotClick(slot, button, flag, player);
		if(slot > 0) {
			Slot slots = this.getSlot(slot);
			slots.getSlotIndex();
			
		}
		return stack;
	}
}
