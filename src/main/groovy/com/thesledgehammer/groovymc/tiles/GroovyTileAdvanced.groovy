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

package com.thesledgehammer.groovymc.tiles

import com.thesledgehammer.groovymc.gui.inventory.InventoryAdaptor
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
        setIInventory(new InventoryAdaptor());
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
