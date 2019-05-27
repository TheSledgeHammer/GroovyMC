package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

import ic2.api.energy.EnergyNet
import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import ic2.api.energy.tile.IEnergySink
import ic2.api.energy.tile.IEnergySource
import net.minecraft.util.EnumFacing

//IEnergyConductor
class EnergyUnitStorage implements IEnergyUnitStorage {

    private double energy;
    private double capacity;
    private double maxReceive;
    private double maxExtract;
    private int sourceTier;
    private int sinkTier;

    private EnergyUnitAcceptor euAcceptor;
    private EnergyUnitEmitter euEmitter;

    EnergyUnitStorage(double capacity, int sourceTier, int sinkTier) {
        setCapacity(capacity);
        setSourceTier(sourceTier);
        setSinkTier(sinkTier);
        this.euAcceptor = new EnergyUnitAcceptor();
        this.euEmitter = new EnergyUnitEmitter();
    }

    EnergyUnitStorage(double capacity, double maxTransfer, int sourceTier, int sinkTier) {
        setCapacity(capacity);
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
        setSourceTier(sourceTier);
        setSinkTier(sinkTier);
        this.euAcceptor = new EnergyUnitAcceptor();
        this.euEmitter = new EnergyUnitEmitter();
    }

    EnergyUnitStorage(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier) {
        setCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
        setSourceTier(sourceTier);
        setSinkTier(sinkTier);
        this.euAcceptor = new EnergyUnitAcceptor();
        this.euEmitter = new EnergyUnitEmitter();
    }

    void setEnergyStored(double energy) {
        this.energy = energy;
    }

    void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    void setMaxReceive(double maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(double maxExtract) {
        this.maxExtract = maxExtract;
    }

    EnergyUnitAcceptor EnergyAcceptor() {
        return euAcceptor;
    }

    EnergyUnitEmitter EnergyEmitter() {
        return euEmitter;
    }

    void setSinkTier(int sinkTier) {
        if (sinkTier < 0) {
            throw new IllegalArgumentException("invalid tier: ${sinkTier}");
        } else {
            this.sinkTier = sinkTier;
        }
    }

    void setSourceTier(int sourceTier) {
        if(sourceTier < 0) {
            throw new IllegalArgumentException("invalid tier: ${sourceTier}");
        } else {
            double power = EnergyNet.instance.getPowerFromTier(sourceTier);
            if(capacity < power) {
                setCapacity(power);
            }
            this.sourceTier = sourceTier;
        }
    }

    double getMaxReceive() {
        return maxReceive;
    }

    double getMaxExtract() {
        return maxExtract;
    }

    double getMaxEnergyStored() {
        return capacity;
    }

    void modifyPowerStored(double energy) {
        this.energy = energy;
        if(energy > this.capacity) {
            this.energy = this.capacity;
        } else if(this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    double getDemandedEnergy() {
        return Math.max(0.0, capacity - energy);
    }

    @Override
    double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        if(!euAcceptor.acceptsEnergyFrom(this, enumFacing)) {
            return 0
        }
        double toInject = Math.min(energy, Math.min(this.maxExtract, amount));
        energy -= toInject;
        return toInject;
    }

    @Override
    void drawEnergy(double amount) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if(!euEmitter.emitsEnergyTo(this, face)) {
                amount = 0;
            }
        }
        double toDraw = Math.min(capacity - energy, Math.min(this.maxReceive, amount));
        energy += toDraw;
        amount = toDraw;
    }


    @Override
    double getOfferedEnergy() {
        return energy;
    }

    @Override
    int getSinkTier() {
        return sinkTier;
    }

    @Override
    int getSourceTier() {
        return sourceTier;
    }

    @Override
    boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        iEnergyAcceptor = euEmitter.getEnergyUnitAcceptor();
        return euEmitter.emitsEnergyTo(iEnergyAcceptor, enumFacing);
    }

    @Override
    boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        iEnergyEmitter = euAcceptor.getEnergyUnitEmitter();
        return euAcceptor.acceptsEnergyFrom(iEnergyEmitter, enumFacing);
    }
}
