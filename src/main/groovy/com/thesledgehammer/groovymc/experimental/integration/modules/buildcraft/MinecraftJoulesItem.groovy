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
package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.IMjReceiver
import buildcraft.api.mj.IMjRedstoneReceiver
import buildcraft.api.mj.MjAPI
import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

import javax.annotation.Nonnull

class MinecraftJoulesItem extends GroovyItem implements IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver, ICapabilityProvider {

    private ItemStack container;
    protected MinecraftJoules MJ;

    MinecraftJoulesItem(ItemStack container, long capacity, long maxTransfer) {
        setItemContainer(container);
        MJ = new MinecraftJoules(capacity, maxTransfer);
    }

    MinecraftJoulesItem(long capacity, long maxTransfer) {
        setItemContainer(container);
        MJ = new MinecraftJoules(capacity, maxTransfer);
    }

    private void setItemContainer(ItemStack container) {
        this.container = container;
    }

    boolean canExtract() {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.canExtract();
        }
        return false
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.extractPower(min, max, simulate);
        }
        return 0
    }

    @Override
    long getStored() {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.getStored();
        }
        return 0
    }

    @Override
    long getCapacity() {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.getCapacity();
        }
        return 0
    }

    @Override
    long getPowerRequested() {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.getPowerRequested();
        }
        return 0
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.receivePower(microJoules, simulate);
        }
        return 0
    }

    @Override
    boolean canReceive() {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.canReceive();
        }
        return false;
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        if(container instanceof MinecraftJoulesStorage) {
            MinecraftJoulesStorage mjItem = container as MinecraftJoulesStorage;
            return mjItem.canConnect(other);
        }
        return false
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == MjAPI.CAP_CONNECTOR) {
            return true;
        }
        if(capability == MjAPI.CAP_RECEIVER) {
            return true;
        }
        if(capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return true;
        }
        if(capability == MjAPI.CAP_READABLE) {
            return true;
        }
        if(capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == MjAPI.CAP_CONNECTOR) {
            return MjAPI.CAP_CONNECTOR.cast(this);
        }
        if(capability == MjAPI.CAP_RECEIVER) {
            return MjAPI.CAP_RECEIVER.cast(this);
        }
        if(capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return MjAPI.CAP_PASSIVE_PROVIDER.cast(this);
        }
        if(capability == MjAPI.CAP_READABLE) {
            return MjAPI.CAP_READABLE.cast(this);
        }
        if(capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return MjAPI.CAP_REDSTONE_RECEIVER.cast(this);
        }
        return null;
    }
}
