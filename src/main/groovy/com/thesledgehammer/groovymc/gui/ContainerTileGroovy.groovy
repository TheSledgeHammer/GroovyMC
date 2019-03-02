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
