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
 *
 * Base of MinecraftForge's EnergyStorage
 */

package com.thesledgehammer.groovymc.energy.traits;

import net.minecraftforge.energy.IEnergyStorage

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

    void modifyEnergyStored(int energy) {
        this.energy = energy;
        if(energy > this.capacity) {
            this.energy = this.capacity;
        } else if(this.energy < 0) {
            this.energy = 0;
        }
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
