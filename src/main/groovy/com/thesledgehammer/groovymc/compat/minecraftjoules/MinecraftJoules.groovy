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
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional

import javax.annotation.Nonnull
import javax.annotation.Nullable

class MinecraftJoules extends MinecraftJoulesStorage implements ICapabilityProvider, INBTCompound {

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
        super(capacity, maxReceive, maxExtract, mjEnergy);
    }

    void drainPower(long amount) {
        modifyPowerStored(mjEnergy - amount)
    }

    void generatePower(long amount) {
        modifyPowerStored(mjEnergy + amount);
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
    CompoundNBT write(CompoundNBT tag) {
        if(mjEnergy < 0) {
            mjEnergy = 0;
        }
        tag.putLong("mjEnergy", mjEnergy);
        return tag;
    }

    @Override
    void read(CompoundNBT tag) {
        this.mjEnergy = tag.getLong("mjEnergy");
        if(mjEnergy > capacity) {
            mjEnergy = capacity;
        }
    }

    @Override
    def <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE as LazyOptional<T>
        }/*
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
        }*/
        return null
    }

    @Override
    def <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE as LazyOptional<T>
        }
        return null;
    }
}
