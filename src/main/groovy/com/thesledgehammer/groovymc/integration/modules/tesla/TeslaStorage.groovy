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

import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.minecraftforge.fml.common.Optional

@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "tesla.api.ITeslaConsumer", modid = "tesla"),
                @Optional.Interface(iface = "tesla.api.ITeslaProducer", modid = "tesla"),
                @Optional.Interface(iface = "tesla.api.ITeslaHolder", modid = "tesla")
        ]
)
class TeslaStorage implements ITeslaConsumer, ITeslaProducer, ITeslaHolder {

    private long teslaEnergy;
    private long capacity;
    private long maxReceive;
    private long maxExtract;

    TeslaStorage(long capacity) {
        this(capacity, capacity, capacity, 0);
    }

    TeslaStorage(long capacity, long maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    TeslaStorage(long capacity, long maxReceive, long maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    TeslaStorage(long capacity, long maxReceive, long maxExtract,  long teslaEnergy) {
        setMaxCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
        setEnergyStored(teslaEnergy);
    }

    void setEnergyStored(long teslaEnergy) {
        this.teslaEnergy = teslaEnergy;
    }

    void setMaxCapacity(long capacity) {
        this.capacity = capacity;
    }

    void setMaxReceive(long maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
    }

    long getMaxReceive() {
        return maxReceive;
    }

    long getMaxExtract() {
        return maxExtract;
    }

    void modifyPowerStored(long teslaEnergy) {
        this.teslaEnergy = teslaEnergy;
        if(teslaEnergy > this.capacity) {
            this.teslaEnergy = this.capacity;
        } else if(this.teslaEnergy < 0) {
            this.teslaEnergy = 0;
        }
    }

    @Override
    long givePower(long power, boolean simulated) {
        long powerReceived = Math.min(capacity - power, Math.min(this.maxReceive, maxReceive));
        if (!simulated) {
            teslaEnergy += powerReceived;
        }
        return powerReceived;
    }

    @Override
    long getStoredPower() {
        return teslaEnergy;
    }

    @Override
    long getCapacity() {
        return capacity;
    }

    @Override
    long takePower(long power, boolean simulated) {
        long powerExtracted = Math.min(power, Math.min(this.maxExtract, maxExtract));
        if (!simulated) {
            teslaEnergy -= powerExtracted;
        }
        return powerExtracted;
    }
}
