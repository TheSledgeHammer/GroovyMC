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
package com.thesledgehammer.groovymc.compat.minecraftjoules

import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.IMjReceiver
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

import javax.annotation.Nonnull

class MinecraftJoulesItem extends GroovyItem implements IMjStorage, ICapabilityProvider {

    private ItemStack container;

    MinecraftJoulesItem(ItemStack container) {
        this.container = container;
    }

    @Override
    long getStored() {
        if(container instanceof IMjReadable) {
            IMjReadable mjItem = container as IMjReadable;
            return mjItem.getStored();
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.getStored();
        }
        return 0
    }

    @Override
    long getCapacity() {
        if(container instanceof IMjReadable) {
            IMjReadable mjItem = container as IMjReadable;
            return mjItem.getCapacity();
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.getCapacity();
        }
        return 0
    }

    @Override
    long getPowerRequested() {
        if(container instanceof IMjReceiver) {
            IMjReceiver mjItem = container as IMjReceiver;
            return mjItem.getPowerRequested();
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.getPowerRequested();
        }
        return 0
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        if(container instanceof IMjReceiver) {
            IMjReceiver mjItem = container as IMjReceiver;
            return mjItem.receivePower(microJoules, simulate);
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.receivePower(microJoules, simulate);
        }
        return 0
    }

    @Override
    boolean canReceive() {
        if(container instanceof IMjReceiver) {
            IMjReceiver mjItem = container as IMjReceiver;
            return mjItem.canReceive();
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.canReceive();
        }
        return false;
    }

    @Override
    boolean canExtract() {
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.canExtract();
        }
        return false
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        if(container instanceof IMjPassiveProvider) {
            IMjPassiveProvider mjItem = container as IMjPassiveProvider;
            return mjItem.extractPower(min, max, simulate);
        }
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.extractPower(min, max, simulate);
        }
        return 0
    }

    @Override
    boolean canConnect(@Nonnull IMjStorage other) {
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.canConnect(other);
        }
        return false
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE.cast(this);
        }
        return null;
    }
}
