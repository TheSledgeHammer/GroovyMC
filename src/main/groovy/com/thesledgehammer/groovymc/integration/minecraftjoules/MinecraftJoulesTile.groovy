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

import buildcraft.api.mj.*
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import com.thesledgehammer.groovymc.integration.modules.buildcraft.BuildcraftModule
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
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
class MinecraftJoulesTile extends GroovyTileBasic implements IMjStorage, IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver {

    protected MinecraftJoules mj;
    private long power; //change to protected
    private String tileName; //Needed For Tile NBT Only

    MinecraftJoulesTile(String tileName, long capacity) {
        this(tileName, capacity, capacity, capacity, 0);
    }

    MinecraftJoulesTile(String tileName, long capacity, long maxTransfer) {
        this(tileName, capacity, maxTransfer, maxTransfer, 0);
    }

    MinecraftJoulesTile(String tileName, long capacity, long maxReceive, long maxExtract) {
        this(tileName, capacity, maxReceive, maxExtract, 0);
    }

    MinecraftJoulesTile(String tileName, long capacity, long maxReceive, long maxExtract, long mjEnergy) {
        mj = new MinecraftJoules(capacity, maxReceive, maxExtract, mjEnergy);
        this.tileName = tileName;
        this.power = mjEnergy;
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        return mj.extractPower(min, max, simulate);
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        return mj.receivePower(microJoules, simulate);
    }

    @Override
    long getStored() {
        return mj.getStored();
    }

    @Override
    long getCapacity() {
        return mj.getCapacity();
    }

    @Override
    long getPowerRequested() {
        return mj.getPowerRequested();
    }

    @Override
    boolean canReceive() {
        return mj.canReceive();
    }

    boolean canExtract() {
        return mj.canExtract();
    }

    @Override
    boolean canConnectToStorage(@Nonnull IMjStorage other) {
        return mj.canConnectToStorage(other);
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return mj.canConnect(other);
    }


    @Override
    void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (tagCompound.hasKey(tileName)) {
            power = tagCompound.getCompoundTag(tileName).getLong("mjEnergy");
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (power > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setLong("mjEnergy", getStored());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        if (capability == MjAPI.CAP_CONNECTOR) {
            return true;
        }
        if (capability == MjAPI.CAP_RECEIVER) {
            return true;
        }
        if (capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return true;
        }
        if (capability == MjAPI.CAP_READABLE) {
            return true;
        }
        if (capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE.cast(this);
        }
        if (BuildcraftModule.hasMjCapability(capability)) {
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
        return super.getCapability(capability, facing);
    }
}
