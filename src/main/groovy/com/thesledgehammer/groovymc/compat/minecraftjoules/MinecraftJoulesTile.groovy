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


import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.nbt.CompoundNBT
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional

import javax.annotation.Nonnull
import javax.annotation.Nullable

/*
@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "buildcraft.api.mj.IMjConnector", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReceiver", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjPassiveProvider", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReadable", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjRedstoneReceiver", modid = "buildcraft")
        ]
)*/
abstract class MinecraftJoulesTile extends GroovyTileBasic implements IMjStorage/*, IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver*/ {

    protected MinecraftJoules mj;

    MinecraftJoulesTile(TileEntityType tileEntityTypeIn, long capacity, long maxTransfer) {
        super(tileEntityTypeIn);
        mj = new MinecraftJoules(capacity, maxTransfer);
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
    boolean canConnect(@Nonnull IMjStorage other) {
        return mj.canConnect(other);
    }
/*
    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return mj.canConnect(other);
    }
    */

    void read(CompoundNBT compound) {
        super.read(compound);
        mj.read(compound);
    }

    @Override
    CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        mj.write(compound);
        return compound;
    }

    @Override
    @Nullable
    <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        LazyOptional<T> mjCapability = mj.getCapability(cap, side);
        if(mjCapability != null) {
            return mjCapability;
        }
        return super.getCapability(cap, side);
    }
}
