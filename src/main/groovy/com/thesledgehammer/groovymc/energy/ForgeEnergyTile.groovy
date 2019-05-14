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

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyTile extends GroovyTileBasic implements IEnergyStorage {

    protected ForgeEnergy fe;
    private int energy;
    private String tileName;//Needed For Tile NBT Only

    ForgeEnergyTile(TileEntityType<?> tileEntityTypeIn, String tileName, int capacity, int maxTransfer) {
        super(tileEntityTypeIn)
        fe = new ForgeEnergy(capacity, maxTransfer);
        this.tileName = tileName;
    }

    ForgeEnergyTile(TileEntityType<?> tileEntityTypeIn, String tileName, int capacity, int maxReceive, int maxExtract) {
        super(tileEntityTypeIn)
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
    void read(NBTTagCompound tagCompound) {
        super.read(tagCompound);
        if (tagCompound.hasKey(tileName)) {
            energy = tagCompound.getCompound(tileName).getInt("energy");
        }
    }

    @Override
    NBTTagCompound write(NBTTagCompound tagCompound) {
        super.write(tagCompound);
        if (energy > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setInt("energy", getEnergyStored());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
    }
/*
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
    }*/
}
