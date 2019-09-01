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

import com.thesledgehammer.groovymc.api.INBTCompound
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

import javax.annotation.Nonnull
import javax.annotation.Nullable

class ForgeEnergy extends ForgeEnergyStorage implements ICapabilityProvider, INBTCompound {

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
/*
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
    */
    private void modifyEnergyStored(int feEnergy) {
        this.feEnergy = feEnergy;
        if(feEnergy > getMaxEnergyStored()) {
            this.feEnergy = getMaxEnergyStored();
        } else if(this.feEnergy < 0) {
            this.feEnergy = 0;
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tag) {
        if(feEnergy < 0) {
            feEnergy = 0;
        }
        tag.setInteger("feEnergy", feEnergy);
        return tag;
    }

    @Override
    void readFromNBT(NBTTagCompound tag) {
        this.feEnergy = tag.getInteger("feEnergy");
        if(feEnergy > capacity) {
            feEnergy = capacity;
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
            ForgeEnergy FE = new ForgeEnergy(capacity, maxReceive, maxExtract, feEnergy)
            IEnergyStorage storage = new IEnergyStorage() {
                @Override
                int receiveEnergy(int maxReceive, boolean simulate) {
                    return FE.receiveEnergy(maxReceive, simulate);
                }

                @Override
                int extractEnergy(int maxExtract, boolean simulate) {
                    return FE.extractEnergy(maxExtract, simulate);
                }

                @Override
                int getEnergyStored() {
                    return FE.getEnergyStored();
                }

                @Override
                int getMaxEnergyStored() {
                    return FE.getMaxEnergyStored();
                }

                @Override
                boolean canExtract() {
                    return FE.canExtract()
                }

                @Override
                boolean canReceive() {
                    return FE.canReceive();
                }
            }
            return CapabilityEnergy.ENERGY.cast(storage);
        }
        return null
    }
}
