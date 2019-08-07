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

import buildcraft.api.mj.*
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import com.thesledgehammer.groovymc.compat.modules.buildcraft.BuildcraftModule
import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fml.common.Optional

import javax.annotation.Nonnull

@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "buildcraft.api.mj.IMjConnector", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReceiver", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjPassiveProvider", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReadable", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjRedstoneReceiver", modid = "buildcraft")
        ]
)
class MinecraftJoulesItem extends GroovyItem implements IMjStorage, IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver, ICapabilityProvider {

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
    boolean canConnectToStorage(@Nonnull IMjStorage other) {
        if(container instanceof IMjStorage) {
            IMjStorage mjItem = container as IMjStorage;
            return mjItem.canConnect(other);
        }
        return false
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        if(container instanceof IMjConnector) {
            IMjConnector mjItem = container as IMjConnector;
            return mjItem.canConnect(other);
        }
        return false
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        if(BuildcraftModule.hasMjCapability(capability)) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE.cast(this);
        }
        if(BuildcraftModule.hasMjCapability(capability)) {
            if (capability == MjAPI.CAP_CONNECTOR) {
                return MjAPI.CAP_CONNECTOR.cast(this);
            }
            if (capability == MjAPI.CAP_RECEIVER) {
                return MjAPI.CAP_RECEIVER.cast(this);
            }
            if (capability == MjAPI.CAP_PASSIVE_PROVIDER) {
                return MjAPI.CAP_PASSIVE_PROVIDER.cast(this);
            }
            if (capability == MjAPI.CAP_READABLE) {
                return MjAPI.CAP_READABLE.cast(this);
            }
            if (capability == MjAPI.CAP_REDSTONE_RECEIVER) {
                return MjAPI.CAP_REDSTONE_RECEIVER.cast(this);
            }
        }
        return null;
    }
}
