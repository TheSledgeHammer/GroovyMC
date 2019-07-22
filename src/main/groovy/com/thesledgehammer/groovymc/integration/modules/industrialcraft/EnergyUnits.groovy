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
package com.thesledgehammer.groovymc.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage

class EnergyUnits extends EnergyUnitStorage {


    EnergyUnits(double capacity, int sourceTier, int sinkTier) {
        super(capacity, sourceTier, sinkTier)
    }

    EnergyUnits(double capacity, double maxTransfer, int sourceTier, int sinkTier) {
        super(capacity, maxTransfer, sourceTier, sinkTier)
    }

    EnergyUnits(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier) {
        super(capacity, maxReceive, maxExtract, sourceTier, sinkTier)
    }

    EnergyUnits(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier, double euEnergy) {
        super(capacity, maxReceive, maxExtract, sourceTier, sinkTier, euEnergy)
    }

    void drainEnergy(double amount) {
        modifyEnergyStored(euEnergy - amount)
    }

    void generateEnergy(double amount) {
        modifyEnergyStored(euEnergy + amount);
    }

    /*
    void drainEnergy(double amount, EnumVoltage voltage) {
        if(amount >= voltage.getVoltage() * 32) {
            amount = voltage.getVoltage() * 32;
        }
        modifyEnergyStored(euEnergy - amount);
    }

    void generateEnergy(double amount, EnumVoltage voltage) {
        if(amount >= voltage.getVoltage() * 32) {
            amount = voltage.getVoltage() * 32;
        }
        modifyEnergyStored(euEnergy + amount);
    }
    */

    private void modifyEnergyStored(double euEnergy) {
        this.euEnergy = euEnergy;
        if(euEnergy > getMaxEnergyStored()) {
            this.euEnergy = getMaxEnergyStored();
        } else if(this.euEnergy < 0) {
            this.euEnergy = 0;
        }
    }
}
