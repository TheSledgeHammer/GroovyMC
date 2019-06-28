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

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.MjTools

class TieredMinecraftJoulesTile extends MinecraftJoulesTile {

    private EnumVoltage voltage;

    TieredMinecraftJoulesTile(String tileName, long capacity, EnumVoltage voltage) {
        this(tileName, capacity, capacity, capacity, 0, voltage);
    }

    TieredMinecraftJoulesTile(String tileName, long capacity, long maxTransfer, EnumVoltage voltage) {
        this(tileName, capacity, maxTransfer, maxTransfer, 0, voltage);
    }

    TieredMinecraftJoulesTile(String tileName, long capacity, long maxReceive, long maxExtract, EnumVoltage voltage) {
        this(tileName, capacity, maxReceive, maxExtract, 0, voltage);
    }

    TieredMinecraftJoulesTile(String tileName, long capacity, long maxReceive, long maxExtract, long power, EnumVoltage voltage) {
        super(tileName, capacity, maxReceive, maxExtract, power);
        this.voltage = voltage;
    }

    EnumVoltage getVoltageTier() {
        return voltage;
    }

    long getVoltage() {
        return voltage.getVoltage();
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        long extracted = mj.extractPower(min, max, simulate);
        if(extracted >= maxMjIO(voltage)) {
            return maxMjIO(voltage);
        }
        return extracted;
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        long received = mj.receivePower(microJoules, simulate);
        if(received >= maxMjIO(voltage)) {
            return maxMjIO(voltage);
        }
        return received;
    }

    private static long maxMjIO(EnumVoltage voltage) {
        long volts = voltage.getVoltage();
        return (volts * MjTools.MJ);
    }
}
