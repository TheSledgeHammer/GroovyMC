package com.thesledgehammer.groovymc.compat.forgeenergy

import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyStorage implements IEnergyStorage {

    protected int feEnergy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    ForgeEnergyStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    ForgeEnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    ForgeEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    ForgeEnergyStorage(int capacity, int maxReceive, int maxExtract, int feEnergy) {
        setMaxCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
        this.feEnergy = Math.max(0 , Math.min(capacity, feEnergy));
    }

    void setMaxCapacity(int capacity) {
        this.capacity = capacity;
    }

    void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    int getMaxReceive() {
        return maxReceive;
    }

    int getMaxExtract() {
        return maxExtract;
    }

    @Override
    int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

        int energyReceived = Math.min(capacity - feEnergy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            feEnergy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract()) {
            return 0;
        }

        int energyExtracted = Math.min(feEnergy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            feEnergy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    int getEnergyStored() {
        return feEnergy;
    }

    @Override
    int getMaxEnergyStored() {
        return getCapacity();
    }

    @Override
    boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    boolean canReceive() {
        return this.maxReceive > 0;
    }

    protected int getCapacity() {
        return capacity;
    }
}
