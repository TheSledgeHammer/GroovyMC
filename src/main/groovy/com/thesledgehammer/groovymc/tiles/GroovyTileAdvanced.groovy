/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.tiles

import com.thesledgehammer.groovymc.tiles.traits.TileInventoryTraits
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.wrapper.InvWrapper
import net.minecraftforge.items.wrapper.SidedInvWrapper

class GroovyTileAdvanced extends GroovyTileBasic implements TileInventoryTraits {

    private IItemHandler itemHandler;
    private IItemHandler itemHandlerSided;

    GroovyTileAdvanced() {
        setTileEntity(this);
        setIInventory(this);
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == null) {
                if(itemHandler == null) {
                    itemHandler = new InvWrapper((IInventory) this);
                }
                return (T) itemHandler;
            } else {
                if(itemHandlerSided == null) {
                    itemHandlerSided = new SidedInvWrapper((ISidedInventory) this, facing);
                }
                return (T) itemHandlerSided;
            }
        }
        return super.getCapability(capability, facing);
    }
}
