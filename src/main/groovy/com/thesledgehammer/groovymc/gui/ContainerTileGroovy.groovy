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
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ContainerTileGroovy<T extends TileEntity> extends ContainerGroovy {
	
	protected final T tile;
	
	ContainerTileGroovy(T tile) {
		this.tile = tile;
	}
	
	protected ContainerTileGroovy(T tile, InventoryPlayer playerInventory, int xInv, int yInv) {
		this(tile);
		
		addPlayerInventory(playerInventory, xInv, yInv);
		setContainerInventorySize(sizeOfInventory);
	}
	
	@Override
	boolean canInteractWith(EntityPlayer playerIn) {
		if(tile == null) {
			return false;
		}
		BlockPos pos = tile.getPos();
		World world = tile.getWorld();
		
		if(tile.isInvalid()) {
			return false;
		}
		
		if(world.getTileEntity(pos) != tile) {
			return false;
		}
		return playerIn.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 64;
	}
}
