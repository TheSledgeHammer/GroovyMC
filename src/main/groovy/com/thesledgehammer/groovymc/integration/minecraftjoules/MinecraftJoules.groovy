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
package com.thesledgehammer.groovymc.integration.minecraftjoules

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.IMjReceiver
import buildcraft.api.mj.IMjRedstoneReceiver
import buildcraft.api.mj.MjAPI
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.MjTools
import com.thesledgehammer.groovymc.integration.modules.buildcraft.BuildcraftModule
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable

import javax.annotation.Nonnull
import javax.annotation.Nullable

class MinecraftJoules extends MinecraftJoulesStorage implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    MinecraftJoules(long capacity) {
        super(capacity)
    }

    MinecraftJoules(long capacity, long maxTransfer) {
        super(capacity, maxTransfer)
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract) {
        super(capacity, maxReceive, maxExtract)
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract, long mjEnergy) {
        super(capacity, maxReceive, maxExtract, mjEnergy)
    }

    void drainPower(long amount) {
        modifyPowerStored(mjEnergy - amount)
    }

    void generatePower(long amount) {
        modifyPowerStored(mjEnergy + amount);
    }

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

    private void modifyPowerStored(long mjEnergy) {
        this.mjEnergy = mjEnergy;
        if(mjEnergy > getCapacity()) {
            this.mjEnergy = getCapacity();
        } else if(this.mjEnergy < 0) {
            this.mjEnergy = 0;
        }
    }

    @Override
    NBTTagCompound serializeNBT() {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("mjEnergy", mjEnergy);
        return nbt;
    }

    @Override
    void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("mjEnergy")) {
            final long tempEnergy = nbt.getLong("mjEnergy");
        }
    }

    @Override
    boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        if (capability == MjAPI.CAP_CONNECTOR) {
            return true;
        }
        if (capability == MjAPI.CAP_RECEIVER) {
            return true;
        }
        if (capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return true;
        }
        if (capability == MjAPI.CAP_READABLE) {
            return true;
        }
        if (capability == MjAPI.CAP_REDSTONE_RECEIVER) {
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
        if (BuildcraftModule.hasMjCapability(capability)) {
            MinecraftJoules MJ = new MinecraftJoules(capacity, maxReceive, maxExtract, mjEnergy);
            if (capability == MjAPI.CAP_CONNECTOR) {
                IMjConnector connector = new IMjConnector() {
                    @Override
                    boolean canConnect(@Nonnull IMjConnector other) {
                        return MJ.canConnect(other);
                    }
                };
                return MjAPI.CAP_CONNECTOR.cast(connector);
            }
            if (capability == MjAPI.CAP_RECEIVER) {
                IMjReceiver receiver = new IMjReceiver() {
                    @Override
                    long getPowerRequested() {
                        return MJ.getPowerRequested();
                    }

                    @Override
                    long receivePower(long microJoules, boolean simulate) {
                        return MJ.receivePower(microJoules, simulate);
                    }

                    @Override
                    boolean canConnect(@Nonnull IMjConnector other) {
                        return MJ.canConnect(other);
                    }
                }
                return MjAPI.CAP_RECEIVER.cast(receiver);
            }
            if (capability == MjAPI.CAP_PASSIVE_PROVIDER) {
                IMjPassiveProvider passiveProvider = new IMjPassiveProvider() {
                    @Override
                    long extractPower(long min, long max, boolean simulate) {
                        return MJ.extractPower(min, max, simulate);
                    }

                    @Override
                    boolean canConnect(@Nonnull IMjConnector other) {
                        return MJ.canConnect(other);
                    }
                }
                return MjAPI.CAP_PASSIVE_PROVIDER.cast(passiveProvider);
            }
            if (capability == MjAPI.CAP_READABLE) {
                IMjReadable readable = new IMjReadable() {
                    @Override
                    long getStored() {
                        return MJ.getStored();
                    }

                    @Override
                    long getCapacity() {
                        return MJ.getCapacity()
                    }

                    @Override
                    boolean canConnect(@Nonnull IMjConnector other) {
                        return MJ.canConnect(other);
                    }
                }
                return MjAPI.CAP_READABLE.cast(readable);
            }
            if (capability == MjAPI.CAP_REDSTONE_RECEIVER) {
                IMjRedstoneReceiver redstoneReceiver = new IMjRedstoneReceiver() {
                    @Override
                    long getPowerRequested() {
                        return MJ.getPowerRequested();
                    }

                    @Override
                    long receivePower(long microJoules, boolean simulate) {
                        return MJ.receivePower(microJoules, simulate);
                    }

                    @Override
                    boolean canConnect(@Nonnull IMjConnector other) {
                        return MJ.canConnect(other);
                    }
                }
                return MjAPI.CAP_REDSTONE_RECEIVER.cast(redstoneReceiver);
            }
        }
        return null;
    }
}
