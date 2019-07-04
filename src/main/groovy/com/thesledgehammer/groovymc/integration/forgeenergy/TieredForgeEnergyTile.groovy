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
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier

class TieredForgeEnergyTile extends ForgeEnergyTile implements IVoltageTier {

    TieredForgeEnergyTile(String tileName, int capacity, EnumVoltage voltage) {
        this(tileName, capacity, 0, voltage)
    }

    TieredForgeEnergyTile(String tileName, int capacity, int maxTransfer, EnumVoltage voltage) {
        this(tileName, capacity, maxTransfer, 0, voltage)
    }

    TieredForgeEnergyTile(String tileName, int capacity, int maxReceive, int maxExtract, EnumVoltage voltage) {
        this(tileName, capacity, maxReceive, maxExtract, 0, voltage)
    }

    TieredForgeEnergyTile(String tileName, int capacity, int maxReceive, int maxExtract, int feEnergy, EnumVoltage voltage) {
        super(tileName, capacity, maxReceive, maxExtract, feEnergy);
        setVoltageTier(voltage);
    }

    @Override
    void setVoltageTier(EnumVoltage voltage) {
        fe.setVoltageTier(voltage);
    }

    @Override
    EnumVoltage getVoltageTier() {
        return fe.getVoltageTier();
    }

    @Override
    long getVoltage() {
        return fe.getVoltage();
    }

    @Override
    int receiveEnergy(int maxReceive, boolean simulate) {
        int receive = fe.receiveEnergy(maxReceive, simulate);
        if(receive >= getVoltage() * 32) {
            return getVoltage() * 32;
        }
        return receive;
    }

    @Override
    int extractEnergy(int maxExtract, boolean simulate) {
        int extract = fe.extractEnergy(maxExtract, simulate);
        if(extract >= getVoltage() * 32) {
            return getVoltage() * 32;
        }
        return extract;
    }
}
