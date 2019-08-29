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

package com.thesledgehammer.groovymc.api

import net.minecraft.util.IStringSerializable

enum EnumEnergyType implements IStringSerializable {
    LV(EnumVoltage.LOW),
    MV(EnumVoltage.MEDIUM),
    HV(EnumVoltage.HIGH),
    UV(EnumVoltage.ULTRA),
    SV(EnumVoltage.SUPER),
    EV(EnumVoltage.EXTREME),
    IV(EnumVoltage.INSANE),
    INFINIV(EnumVoltage.INFINITE);

    static final EnumEnergyType[] VALUES = values();
    private final String name;
    private final EnergyConfig RF;
    private final EnergyConfig MJ;
    private int capacity_multiplier = 0;
    private final EnumVoltage voltage;

    EnumEnergyType(EnumVoltage voltage) {
        this.name = toString().toLowerCase(Locale.ENGLISH);
        this.voltage = voltage;
        setCapacityMultiplier(capacity_multiplier);
        this.MJ = EnergyConfig.createMJConfig(voltage.getVoltage() * getCapacityMultiplier(), EnergyConfig.maxTransferMJ(voltage.getVoltage(), voltage), EnergyConfig.maxTransferMJ(voltage.getVoltage(), voltage));
        this.RF = EnergyConfig.createRFConfig((int) voltage.getVoltage() * getCapacityMultiplier(), (int) EnergyConfig.maxTransferRF(voltage.getVoltage(), voltage), (int) EnergyConfig.maxTransferRF(voltage.getVoltage(), voltage));
    }

    void setCapacityMultiplier(int capacity_multiplier) {
        if(capacity_multiplier <= 0) {
            this.capacity_multiplier = 1;
        } else {
            this.capacity_multiplier = capacity_multiplier;
        }
    }

    EnumVoltage getVoltage() {
        return voltage;
    }

    EnergyConfig getMJ() {
        return MJ;
    }

    EnergyConfig getRF() {
        return RF;
    }

    int getCapacityMultiplier() {
        return capacity_multiplier;
    }

    @Override
    String getName() {
        return name;
    }
}