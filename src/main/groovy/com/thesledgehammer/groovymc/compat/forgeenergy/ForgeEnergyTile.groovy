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

package com.thesledgehammer.groovymc.compat.forgeenergy

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.energy.IEnergyStorage

import javax.annotation.Nullable

abstract class ForgeEnergyTile extends GroovyTileBasic implements IEnergyStorage {

    protected ForgeEnergy fe;
    ForgeEnergyTile(TileEntityType tileEntityTypeIn, int capacity, int maxTransfer) {
        super(tileEntityTypeIn);
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

    void read(CompoundNBT tag) {
        super.write()
        fe.read(tag);
    }

    @Override
    CompoundNBT write(CompoundNBT tag) {
        super.write(tag);
        fe.write(tag);
        return tag;
    }

    @Override
    @Nullable
    def <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
        T feCapability = fe.getCapability(capability, facing) as T;
        if(feCapability != null) {
            return feCapability as LazyOptional<T>;
        }
        return super.getCapability(capability, facing);
    }
}
