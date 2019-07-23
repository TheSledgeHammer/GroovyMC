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

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.CapabilityEnergy

import javax.annotation.Nonnull
import javax.annotation.Nullable

class ForgeEnergy extends ForgeEnergyStorage implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    ForgeEnergy(int capacity) {
        super(capacity)
    }

    ForgeEnergy(int capacity, int maxTransfer) {
        super(capacity, maxTransfer)
    }

    ForgeEnergy(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract)
    }

    ForgeEnergy(int capacity, int maxReceive, int maxExtract, int feEnergy) {
        super(capacity, maxReceive, maxExtract, feEnergy)
    }

    void drainEnergy(int amount) {
        modifyEnergyStored(feEnergy - amount)
    }

    void generateEnergy(int amount) {
        modifyEnergyStored(feEnergy + amount);
    }

    void drainEnergy(int amount, EnumVoltage voltage) {
        int volts = (int) voltage.getVoltage() * 32;
        if(amount >= volts) {
            amount = volts;
        }
        int drain = Math.min(feEnergy, volts);
        int maxDrain =- Math.max(drain, amount);
        modifyEnergyStored(maxDrain);
    }

    void generateEnergy(int amount, EnumVoltage voltage) {
        int volts = (int) voltage.getVoltage() * 32;
        if(amount >= volts) {
            amount = volts;
        }
        int generate = Math.min(feEnergy, volts);
        int maxGenerate =+ Math.max(generate, amount);
        modifyEnergyStored(maxGenerate);
    }

    private void modifyEnergyStored(int feEnergy) {
        this.feEnergy = feEnergy;
        if(feEnergy > getMaxEnergyStored()) {
            this.feEnergy = getMaxEnergyStored();
        } else if(this.feEnergy < 0) {
            this.feEnergy = 0;
        }
    }

    @Override
    NBTTagCompound serializeNBT() {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("feEnergy", feEnergy);
        return nbt;
    }

    @Override
    void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("feEnergy")) {
            final int tempEnergy = nbt.getInteger("feEnergy");
        }
    }

    @Override
    boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return false
    }

    @Override
    def <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this);
        }
        return null
    }
}
