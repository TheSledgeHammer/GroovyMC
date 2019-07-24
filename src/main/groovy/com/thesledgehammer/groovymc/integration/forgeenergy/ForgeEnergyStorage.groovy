package com.thesledgehammer.groovymc.integration.forgeenergy

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier
import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyStorage implements IEnergyStorage, IVoltageTier {

    protected int feEnergy;
    private int capacity;
    private int maxReceive;
    private int maxExtract;
    protected EnumVoltage voltage;

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
        setEnergyStored(feEnergy);
    }

    void setEnergyStored(int feEnergy) {
        this.feEnergy = feEnergy;
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

    @Override
    void setVoltageTier(EnumVoltage voltage) {
        if(voltage != null) {
            this.voltage = voltage;
        }
        this.voltage = null;
    }

    @Override
    EnumVoltage getVoltageTier() {
        return voltage;
    }

    @Override
    long getVoltage() {
        return voltage.getVoltage();
    }
}
