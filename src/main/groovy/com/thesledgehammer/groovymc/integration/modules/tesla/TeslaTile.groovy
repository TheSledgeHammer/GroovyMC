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

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

import javax.annotation.Nullable

class TeslaTile extends GroovyTileBasic implements ITeslaConsumer, ITeslaProducer, ITeslaHolder {

    protected final Tesla tesla;

    TeslaTile(long capacity) {
        this(capacity, capacity, capacity, 0);
    }

    TeslaTile(long capacity, long maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    TeslaTile(long capacity, long maxReceive, long maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    TeslaTile(long capacity, long maxReceive, long maxExtract,  long teslaEnergy) {
        tesla = new Tesla(capacity, maxReceive, maxExtract, teslaEnergy);
    }

    @Override
    long givePower(long power, boolean simulated) {
        return tesla.givePower(power, simulated);
    }

    @Override
    long getStoredPower() {
        return tesla.getStoredPower();
    }

    @Override
    long getCapacity() {
        return tesla.getCapacity();
    }

    @Override
    long takePower(long power, boolean simulated) {
        return tesla.takePower(power, simulated);
    }

    @Override
    void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        tesla.readFromNBT(tagCompound);
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tesla.writeToNBT(tagCompound);
        return tagCompound;
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return tesla.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        T teslaCapability = tesla.getCapability(capability, facing);
        if(teslaCapability != null) {
            return teslaCapability;
        }
        return super.getCapability(capability, facing);
    }
}
