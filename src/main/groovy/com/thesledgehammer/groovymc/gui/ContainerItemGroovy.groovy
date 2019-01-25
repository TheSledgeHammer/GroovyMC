/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ClickType
import net.minecraft.inventory.Slot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ContainerItemGroovy<T extends Item> extends ContainerGroovy {
	
	protected final T item;
	
	protected ContainerItemGroovy(T item) {
		this.item = item;
	}
	
	protected ContainerItemGroovy(T item, InventoryPlayer playerInventory, int xInv, int yInv) {
		this(item);
		
		addPlayerInventory(playerInventory, xInv, yInv);
		setContainerInventorySize(sizeOfInventory);
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
