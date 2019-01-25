/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.energy;

import net.minecraftforge.energy.IEnergyStorage

//May be Converted to a BigInteger or BigDecimal
trait ForgeEnergyTraits implements IEnergyStorage {

    int energy;
    int capacity;
    int maxReceive;
    int maxExtract;

    void setEnergyStoredFETrait(int energy) {
      this.energy = energy;
    }

    void setMaxCapacityFETrait(int capacity) {
        this.capacity = capacity;
    }

    void setMaxReceiveFETrait(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtractFETrait(int maxExtract) {
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
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    int getEnergyStored() {
        return energy;
    }

    @Override
    int getMaxEnergyStored() {
        return capacity;
    }

    @Override
    boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    boolean canReceive() {
        return this.maxReceive > 0;
    }
}
