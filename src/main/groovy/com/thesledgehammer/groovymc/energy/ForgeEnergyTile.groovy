/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.energy

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyTile extends GroovyTileBasic implements IEnergyStorage {

    protected ForgeEnergy fe;
    private int energy;
    private String tileName; //Needed For Tile NBT Only

    ForgeEnergyTile(String tileName, int capacity, int maxTransfer) {
        fe = new ForgeEnergy(capacity, maxTransfer);
        this.tileName = tileName;
    }

    ForgeEnergyTile(String tileName, int capacity, int maxReceive, int maxExtract) {
        fe = new ForgeEnergy(capacity, maxReceive, maxExtract);
        this.tileName = tileName;
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
        if (tagCompound.hasKey(tileName)) {
            energy = tagCompound.getCompoundTag(tileName).getInteger("energy");
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (energy > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setInteger("energy", getEnergyStored());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this);
        }
        return super.getCapability(capability, facing);
    }
}
