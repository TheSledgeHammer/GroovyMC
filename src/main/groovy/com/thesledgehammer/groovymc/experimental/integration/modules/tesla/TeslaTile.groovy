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
package com.thesledgehammer.groovymc.experimental.integration.modules.tesla

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy

class TeslaTile extends GroovyTileBasic implements ITeslaConsumer, ITeslaProducer, ITeslaHolder {

    protected Tesla tesla;
    private long power;
    private String tileName;

    TeslaTile(String tileName, int capacity, int maxTransfer) {
        tesla = new Tesla(capacity, maxTransfer);
        this.tileName = tileName;
    }

    TeslaTile(String tileName, int capacity, int maxReceive, int maxExtract) {
        tesla = new Tesla(capacity, maxReceive, maxExtract);
        this.tileName = tileName;
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
        if (tagCompound.hasKey(tileName)) {
            power = tagCompound.getCompoundTag(tileName).getLong("teslaEnergy");
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (power > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setLong("teslaEnergy", getStoredPower());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_HOLDER) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(TeslaModule.hasTeslaCapability(capability)) {
            if(capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
                return TeslaCapabilities.CAPABILITY_CONSUMER.cast(this);
            }
            if(capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
                return TeslaCapabilities.CAPABILITY_PRODUCER.cast(this);
            }
            if(capability == TeslaCapabilities.CAPABILITY_HOLDER) {
                return TeslaCapabilities.CAPABILITY_HOLDER.cast(this);
            }
        }
        return null;
    }
}
