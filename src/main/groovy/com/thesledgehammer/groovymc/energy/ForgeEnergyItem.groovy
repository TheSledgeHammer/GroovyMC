/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.energy

import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyItem extends GroovyItem implements IEnergyStorage, ICapabilityProvider {

    private ItemStack container;
    protected ForgeEnergy FE;

    ForgeEnergyItem(ItemStack container, int capacity, int maxTransfer) {
        setItemContainer(container);
        FE = new ForgeEnergy(capacity, maxTransfer);
    }

    ForgeEnergyItem(int capacity, int maxTransfer) {
        setItemContainer(container);
        FE = new ForgeEnergy(capacity, maxTransfer);
    }

    private void setItemContainer(ItemStack container) {
        this.container = container;
    }

    @Override
    int receiveEnergy(int maxReceive, boolean simulate) {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.receiveEnergy(maxReceive, simulate);
        }
        return 0;
    }

    @Override
    int extractEnergy(int maxExtract, boolean simulate) {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.extractEnergy(maxExtract, simulate);
        }
        return 0
    }

    @Override
    int getEnergyStored() {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.getEnergyStored();
        }
        return 0
    }

    @Override
    int getMaxEnergyStored() {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.getMaxEnergyStored();
        }
        return 0
    }

    @Override
    boolean canExtract() {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.canExtract();
        }
        return false
    }

    @Override
    boolean canReceive() {
        if(container instanceof IEnergyStorage) {
            IEnergyStorage feItem = container as IEnergyStorage;
            return feItem.canReceive();
        }
        return false
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this);
        }
        return null;
    }
}
