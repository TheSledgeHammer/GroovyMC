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
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

import javax.annotation.Nullable

class TieredMinecraftJoulesTile extends MinecraftJoulesTile implements IVoltageTier {

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
        setVoltageTier(voltage);
    }

    @Override
    void setVoltageTier(EnumVoltage voltage) {
       mj.setVoltageTier(voltage)
    }

    @Override
    EnumVoltage getVoltageTier() {
        return mj.getVoltageTier();
    }

    @Override
    long getVoltage() {
        return mj.getVoltage();
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return mj.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        T mjCapability = mj.getCapability(capability, facing);
        if(mjCapability != null) {
            return mjCapability;
        }
        return super.getCapability(capability, facing);
    }
}
