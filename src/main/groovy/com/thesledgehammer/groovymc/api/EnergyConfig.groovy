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

class EnergyConfig {

    private long capacity;
    private long maxReceive;
    private long maxExtract;

    static EnergyConfig createRFConfig(int capacity) {
        return new EnergyConfig(toRF(capacity));
    }

    static EnergyConfig createMJConfig(long capacity) {
        return new EnergyConfig(toMJ(capacity));
    }

    static EnergyConfig createRFConfig(int capacity, int maxTransfer) {
        return new EnergyConfig(toRF(capacity), toRF(maxTransfer));
    }

    static EnergyConfig createMJConfig(long capacity, long maxTransfer) {
        return new EnergyConfig(toMJ(capacity), toMJ(maxTransfer));
    }

    static EnergyConfig createRFConfig(int capacity, int maxReceive, int maxExtract) {
        return new EnergyConfig(toRF(capacity), toRF(maxReceive), toRF(maxExtract));
    }

    static EnergyConfig createMJConfig(long capacity, long maxReceive, long maxExtract) {
        return new EnergyConfig(toMJ(capacity), toMJ(maxReceive), toMJ(maxExtract));
    }

    private EnergyConfig(long capacity) {
        setCapacity(capacity);
    }

    private EnergyConfig(long capacity, long maxTransfer) {
        setCapacity(capacity);
        setMaxTransfer(maxTransfer);
    }

    private EnergyConfig(long capacity, long maxReceive, long maxExtract) {
        setCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
    }

    long getCapacity() {
        return capacity;
    }

    long getMaxReceive() {
        return maxReceive;
    }

    long getMaxExtract() {
        return maxExtract;
    }

    private void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    private void setMaxReceive(long maxReceive) {
        this.maxReceive = maxReceive;
    }

    private void setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
    }

    private void setMaxTransfer(long maxTransfer) {
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    private static long toMJ(long amount) {
        return amount * MjTools.MJ;
    }

    private static int toRF(long amount) {
        return (int) (toMJ(amount)/ MjTools.MJ);
    }

    static long maxTransferMJ(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * MjTools.MJ;
        if(amount >= volts) {
            amount = volts;
        }
        return toMJ(amount);
    }

    static long maxTransferRF(long amount, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * 32;
        if(amount >= volts) {
            amount = volts;
        }
        return toRF(amount);
    }
}
