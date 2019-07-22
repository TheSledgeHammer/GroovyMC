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

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage

class Tesla extends TeslaStorage {

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
        if(amount >= voltage.getVoltage() * 32) {
            amount = voltage.getVoltage() * 32;
        }
        modifyPowerStored(teslaEnergy - amount);
    }

    void generatePower(long amount, EnumVoltage voltage) {
        if(amount >= voltage.getVoltage() * 32) {
            amount = voltage.getVoltage() * 32;
        }
        modifyPowerStored(teslaEnergy + amount);
    }

    private void modifyPowerStored(long teslaEnergy) {
        this.teslaEnergy = teslaEnergy;
        if(teslaEnergy > getCapacity()) {
            this.teslaEnergy = getCapacity();
        } else if(this.teslaEnergy < 0) {
            this.teslaEnergy = 0;
        }
    }
}
