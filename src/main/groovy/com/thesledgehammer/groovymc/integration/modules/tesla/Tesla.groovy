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
package com.thesledgehammer.groovymc.integration.modules.tesla

import com.thesledgehammer.groovymc.api.INBTCompound
import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable

import javax.annotation.Nullable

class Tesla extends TeslaStorage implements ICapabilityProvider, INBTCompound {

    Tesla(long capacity) {
        super(capacity)
    }

    Tesla(long capacity, long maxTransfer) {
        super(capacity, maxTransfer)
    }

    Tesla(long capacity, long maxReceive, long maxExtract) {
        super(capacity, maxReceive, maxExtract)
    }

    Tesla(long capacity, long maxReceive, long maxExtract, long teslaEnergy) {
        super(capacity, maxReceive, maxExtract, teslaEnergy)
    }

    void drainPower(long amount) {
        modifyPowerStored(teslaEnergy - amount)
    }

    void generatePower(long amount) {
        modifyPowerStored(teslaEnergy + amount);
    }

    void drainPower(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * 32;
        if(amount >= volts) {
            amount = volts;
        }
        long drain = Math.min(teslaEnergy, volts);
        long maxDrain =- Math.max(drain, amount);
        modifyPowerStored(maxDrain);
    }

    void generatePower(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * 32;
        if(amount >= volts) {
            amount = volts;
        }
        long generate = Math.min(teslaEnergy, volts);
        long maxGenerate =+ Math.max(generate, amount);
        modifyPowerStored(maxGenerate);
    }

    private void modifyPowerStored(long teslaEnergy) {
        this.teslaEnergy = teslaEnergy;
        if(teslaEnergy > getCapacity()) {
            this.teslaEnergy = getCapacity();
        } else if(this.teslaEnergy < 0) {
            this.teslaEnergy = 0;
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tag) {
        if(teslaEnergy < 0) {
            teslaEnergy = 0;
        }
        tag.setLong("teslaEnergy", teslaEnergy);
        return tag;
    }

    @Override
    void readFromNBT(NBTTagCompound tag) {
        this.teslaEnergy = tag.getLong("teslaEnergy");
        if(teslaEnergy > capacity) {
            teslaEnergy = capacity;
        }
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_HOLDER) {
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    def <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(TeslaModule.hasTeslaCapability(capability)) {
            Tesla TESLA = new Tesla(capacity, maxReceive, maxExtract, teslaEnergy);
            if(capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
                ITeslaConsumer consumer = new ITeslaConsumer() {
                    @Override
                    long givePower(long power, boolean simulated) {
                        return TESLA.givePower(power, simulated);
                    }
                }
                return TeslaCapabilities.CAPABILITY_CONSUMER.cast(consumer);
            }
            if(capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
                ITeslaProducer producer = new ITeslaProducer() {
                    @Override
                    long takePower(long power, boolean simulated) {
                        return TESLA.takePower(power, simulated);
                    }
                }
                return TeslaCapabilities.CAPABILITY_PRODUCER.cast(producer);
            }
            if(capability == TeslaCapabilities.CAPABILITY_HOLDER) {
                ITeslaHolder holder = new ITeslaHolder() {
                    @Override
                    long getStoredPower() {
                        return TESLA.getStoredPower();
                    }

                    @Override
                    long getCapacity() {
                        return TESLA.getCapacity();
                    }
                }
                return TeslaCapabilities.CAPABILITY_HOLDER.cast(holder);
            }
        }
        return null;
    }
}
