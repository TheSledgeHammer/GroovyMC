package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.IMjReceiver
import buildcraft.api.mj.IMjRedstoneReceiver
import net.minecraftforge.fml.common.Optional

import javax.annotation.Nonnull

class MinecraftJoulesStorage implements IMinecraftJoulesStorage {

    private long power;
    private long capacity;
    private long maxReceive;
    private long maxExtract;

    MinecraftJoulesStorage(long capacity) {
        setMaxCapacity(capacity);
    }

    MinecraftJoulesStorage(long capacity, long maxTransfer) {
        setMaxCapacity(capacity);
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    MinecraftJoulesStorage(long capacity, long maxReceive, long maxExtract) {
        setMaxCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
    }

    void setPowerStored(long power) {
        this.power = power;
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

    void modifyPowerStored(long power) {
        this.power = power;
        if(power > this.capacity) {
            this.power = this.capacity;
        } else if(this.power < 0) {
            this.power = 0;
        }
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        if (!canExtract()) {
            return 0;
        }
        long powerExtracted = Math.min(power, Math.min(this.maxExtract, (max - min)));
        if (!simulate) {
            power -= powerExtracted;
        }
        return powerExtracted;
    }

    @Override
    long getStored() {
        return power;
    }

    @Override
    long getCapacity() {
        return capacity
    }

    @Override
    long getPowerRequested() {
        long powerRequest = Math.min(power, Math.min(maxExtract, maxReceive));
        return powerRequest;
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

        long powerReceived = Math.min(capacity - power, Math.min(this.maxReceive, microJoules));
        if (!simulate) {
            power += powerReceived;
        }
        return powerReceived;
    }

    @Override
    boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        if(other != null && other.canConnect(this)) {
            return true;
        }
        return false;
    }
}
