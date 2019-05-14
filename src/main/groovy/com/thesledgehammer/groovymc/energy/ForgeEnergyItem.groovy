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

package com.thesledgehammer.groovymc.energy

import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

import javax.annotation.Nonnull
import javax.annotation.Nullable

class ForgeEnergyItem extends GroovyItem implements IEnergyStorage, ICapabilityProvider {

    private ItemStack container;
    protected ForgeEnergy FE;

    ForgeEnergyItem(Properties properties, ItemStack container, int capacity, int maxTransfer) {
        super(properties);
        setItemContainer(container);
        FE = new ForgeEnergy(capacity, maxTransfer);
    }

    ForgeEnergyItem(Properties properties, int capacity, int maxTransfer) {
        super(properties);
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
/*
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
    }*/

    @Override
    def <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        return null
    }

    @Override
    <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return getCapability(cap) as LazyOptional<T>;
    }
}
