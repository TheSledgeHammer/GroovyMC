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
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

import javax.annotation.Nonnull

class MinecraftJoulesTile extends GroovyTileBasic implements IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver {

    protected MinecraftJoules mj;
    private long power;
    private String tileName; //Needed For Tile NBT Only

    MinecraftJoulesTile(String tileName, long capacity, long maxTransfer) {
        mj = new MinecraftJoules(capacity, maxTransfer);
        this.tileName = tileName;
    }

    MinecraftJoulesTile(String tileName, long capacity, long maxReceive, long maxExtract) {
        mj = new MinecraftJoules(capacity, maxReceive, maxExtract);
        this.tileName = tileName;
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        return mj.extractPower(min, max, simulate);
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
    long receivePower(long microJoules, boolean simulate) {
        return mj.receivePower(microJoules, simulate);
    }

    @Override
    boolean canReceive() {
        return mj.canReceive();
    }

    boolean canExtract() {
        return mj.canExtract();
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return mj.canConnect(other);
    }

    @Override
    void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (tagCompound.hasKey(tileName)) {
            power = tagCompound.getCompoundTag(tileName).getLong("power");
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (power > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setLong("power", getStored());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
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
        return super.hasCapability(capability, facing);
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
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
