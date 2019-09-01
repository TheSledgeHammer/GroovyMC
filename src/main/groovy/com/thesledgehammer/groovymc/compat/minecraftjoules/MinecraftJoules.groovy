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
package com.thesledgehammer.groovymc.compat.minecraftjoules

import com.thesledgehammer.groovymc.api.INBTCompound
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

import javax.annotation.Nonnull
import javax.annotation.Nullable

class MinecraftJoules extends MinecraftJoulesStorage implements ICapabilityProvider, INBTCompound {

    MinecraftJoules(long capacity) {
        super(capacity);
    }

    MinecraftJoules(long capacity, long maxTransfer) {
        super(capacity, maxTransfer);
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract, long mjEnergy) {
        super(capacity, maxReceive, maxExtract, mjEnergy);
    }

    void drainPower(long amount) {
        modifyPowerStored(mjEnergy - amount);
    }

    void generatePower(long amount) {
        modifyPowerStored(mjEnergy + amount);
    }
    /*
    void drainPower(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * MjTools.getMJ();
        if(amount >= volts) {
            amount = volts;
        }
        long drain = Math.min(mjEnergy, volts);
        long maxDrain =- Math.max(drain, amount);
        modifyPowerStored(maxDrain);
    }

    void generatePower(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * MjTools.getMJ();
        if(amount >= volts) {
            amount = volts;
        }
        long generate = Math.min(mjEnergy, volts);
        long maxGenerate =+ Math.max(generate, amount);
        modifyPowerStored(maxGenerate);
    }
    */
    private void modifyPowerStored(long mjEnergy) {
        this.mjEnergy = mjEnergy;
        if(mjEnergy > getCapacity()) {
            this.mjEnergy = getCapacity();
        } else if(this.mjEnergy < 0) {
            this.mjEnergy = 0;
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tag) {
        if(mjEnergy < 0) {
            mjEnergy = 0;
        }
        tag.setLong("mjEnergy", mjEnergy);
        return tag;
    }

    @Override
    void readFromNBT(NBTTagCompound tag) {
        this.mjEnergy = tag.getLong("mjEnergy");
        if(mjEnergy > capacity) {
            mjEnergy = capacity;
        }
    }

    @Override
    boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        return false
    }

    @Override
    @Nullable
    def <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE.cast(this);
        }
        return null;
    }
}
