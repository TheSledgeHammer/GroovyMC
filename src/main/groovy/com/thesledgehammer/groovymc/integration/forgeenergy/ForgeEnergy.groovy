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

class ForgeEnergy extends ForgeEnergyStorage {

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
        if(amount >= voltage.getVoltage() * 32) {
            amount = (int) voltage.getVoltage() * 32;
        }
        modifyEnergyStored(feEnergy - amount);
    }

    void generateEnergy(int amount, EnumVoltage voltage) {
        if(amount >= voltage.getVoltage() * 32) {
            amount = (int) voltage.getVoltage() * 32;
        }
        modifyEnergyStored(feEnergy + amount);
    }

    private void modifyEnergyStored(int feEnergy) {
        this.feEnergy = feEnergy;
        if(feEnergy > getMaxEnergyStored()) {
            this.feEnergy = getMaxEnergyStored();
        } else if(this.feEnergy < 0) {
            this.feEnergy = 0;
        }
    }
}
