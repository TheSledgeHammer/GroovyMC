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
<<<<<<< HEAD
=======

>>>>>>> 1.16.x
package com.thesledgehammer.groovymc.compat.minecraftjoules

import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage

import javax.annotation.Nonnull

<<<<<<< HEAD
class MinecraftJoulesStorage implements IMjStorage {

    protected long mjEnergy;
=======
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
class MinecraftJoulesStorage implements IMjStorage/*, IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver*/ {

    long mjEnergy;
>>>>>>> 1.16.x
    protected long capacity;
    protected long maxReceive;
    protected long maxExtract;

    MinecraftJoulesStorage(long capacity) {
        this(capacity, capacity, capacity, 0);
    }

    MinecraftJoulesStorage(long capacity, long maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    MinecraftJoulesStorage(long capacity, long maxReceive, long maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    MinecraftJoulesStorage(long capacity, long maxReceive, long maxExtract, long mjEnergy) {
        setMaxCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
        this.mjEnergy = Math.max(0 , Math.min(capacity, mjEnergy));
    }

    void setMaxCapacity(long capacity) {
        this.capacity = capacity;
    }

    void setMaxReceive(long maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
    }

    long getMaxReceive() {
        return maxReceive;
    }

    long getMaxExtract() {
        return maxExtract;
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        if (!canExtract()) {
            return 0;
        }
<<<<<<< HEAD
        long powerExtracted = Math.min(mjEnergy, Math.min(maxExtract, (max - min)));
=======
        long powerExtracted = Math.min(mjEnergy, Math.min(this.maxExtract, (max - min)));
>>>>>>> 1.16.x
        if (!simulate) {
            mjEnergy -= powerExtracted;
        }
        return powerExtracted;
    }

    @Override
    long getStored() {
        return mjEnergy;
    }

    @Override
    long getCapacity() {
        return capacity
    }

    @Override
    long getPowerRequested() {
<<<<<<< HEAD
        return Math.min(mjEnergy, Math.min(maxExtract, maxReceive));
=======
        return Math.min(mjEnergy, Math.min(maxExtract, maxReceive));;
>>>>>>> 1.16.x
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

<<<<<<< HEAD
        long powerReceived = Math.min(capacity - mjEnergy, Math.min(maxReceive, microJoules));
=======
        long powerReceived = Math.min(capacity - mjEnergy, Math.min(this.maxReceive, microJoules));
>>>>>>> 1.16.x
        if (!simulate) {
            mjEnergy += powerReceived;
        }
        return powerReceived;
    }

    @Override
    boolean canReceive() {
        return this.maxReceive > 0;
    }

    boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    boolean canConnect(@Nonnull IMjStorage other) {
        return true;
    }
<<<<<<< HEAD
=======
/*
    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return true;
    }
*/
>>>>>>> 1.16.x
}
