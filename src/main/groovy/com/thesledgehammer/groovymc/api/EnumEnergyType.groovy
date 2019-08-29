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

import com.thesledgehammer.groovymc.api.minecraftjoules.MjTools
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

    EnumEnergyType(EnumVoltage voltage) {
        this.name = toString().toLowerCase(Locale.ENGLISH);
        setCapacityMultiplier(capacity_multiplier);
        this.MJ = EnergyConfig.createMJConfig(MJ_Voltage(voltage) * getCapacityMultiplier(), MJ_Voltage(voltage), MJ_Voltage(voltage));
        this.RF = EnergyConfig.createRFConfig(RF_Voltage(voltage) * getCapacityMultiplier(), RF_Voltage(voltage), RF_Voltage(voltage));
    }

    void setCapacityMultiplier(int capacity_multiplier) {
        if(capacity_multiplier <= 0) {
            this.capacity_multiplier = 1;
        } else {
            this.capacity_multiplier = capacity_multiplier;
        }
    }

    EnergyConfig getMJ() {
        return MJ;
    }

    EnergyConfig getRF() {
        return RF;
    }

    private long MJ_Voltage(EnumVoltage voltage) {
        return voltage.getVoltage() * MjTools.getMJ();
    }

    private int RF_Voltage(EnumVoltage voltage) {
        return (int) (MJ_Voltage(voltage) / MjTools.getMJ()) * 32;
    }

    int getCapacityMultiplier() {
        return capacity_multiplier;
    }

    @Override
    String getName() {
        return name;
    }
}