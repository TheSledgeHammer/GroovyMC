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

package com.thesledgehammer.groovymc.integration.forgeenergy

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.IEnergyStorage

import javax.annotation.Nullable

abstract class ForgeEnergyTile extends GroovyTileBasic implements IEnergyStorage {

    protected ForgeEnergy fe;
    ForgeEnergyTile(int capacity, int maxTransfer) {
        this.fe = new ForgeEnergy(capacity, maxTransfer);
    }

    @Override
    int receiveEnergy(int maxReceive, boolean simulate) {
        return fe.receiveEnergy(maxReceive, simulate);
    }

    @Override
    int extractEnergy(int maxExtract, boolean simulate) {
        return fe.extractEnergy(maxExtract, simulate);
    }

    @Override
    int getEnergyStored() {
        return fe.getEnergyStored();
    }

    @Override
    int getMaxEnergyStored() {
        return fe.getMaxEnergyStored();
    }

    @Override
    boolean canExtract() {
        return fe.canExtract();
    }

    @Override
    boolean canReceive() {
        return fe.canReceive();
    }

    @Override
    void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        fe.readFromNBT(tagCompound);
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        fe.writeToNBT(tagCompound);
        return tagCompound;
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return fe.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        T feCapability = fe.getCapability(capability, facing);
        if(feCapability != null) {
            return feCapability;
        }
        return super.getCapability(capability, facing);
    }
}
