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

import com.thesledgehammer.groovymc.items.GroovyItem
import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

class TeslaItem extends GroovyItem implements ITeslaConsumer, ITeslaProducer, ITeslaHolder, ICapabilityProvider {

    private ItemStack container;
    protected Tesla tesla;

    TeslaItem(ItemStack container, long capacity) {
        this(container, capacity, capacity, capacity, 0);
    }

    TeslaItem(ItemStack container, long capacity, long maxTransfer) {
        this(container, capacity, maxTransfer, maxTransfer, 0);
    }

    TeslaItem(ItemStack container, long capacity, long maxReceive, long maxExtract) {
        this(container, capacity, maxReceive, maxExtract, 0);
    }

    TeslaItem(ItemStack container, long capacity, long maxReceive, long maxExtract,  long teslaEnergy) {
        tesla = new Tesla(capacity, maxReceive, maxExtract, teslaEnergy);
        this.container = container;
    }

    @Override
    long givePower(long power, boolean simulated) {
        if(container instanceof  ITeslaConsumer) {
            ITeslaConsumer teslaItem = container as ITeslaConsumer;
            return teslaItem.givePower(power, simulated);
        }
        return 0
    }

    @Override
    long getStoredPower() {
        if(container instanceof  ITeslaHolder) {
            ITeslaHolder teslaItem = container as ITeslaHolder;
            return teslaItem.getStoredPower();
        }
        return 0
    }

    @Override
    long getCapacity() {
        if(container instanceof  ITeslaHolder) {
            ITeslaHolder teslaItem = container as ITeslaHolder;
            return teslaItem.getCapacity();
        }
        return 0
    }

    @Override
    long takePower(long power, boolean simulated) {
        if(container instanceof  ITeslaProducer) {
            ITeslaProducer teslaItem = container as ITeslaProducer;
            return teslaItem.takePower(power, simulated);
        }
        return 0
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
        return false;
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
