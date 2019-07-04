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
package com.thesledgehammer.groovymc.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier
import ic2.api.energy.EnergyNet
import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import ic2.api.energy.tile.IEnergySink
import ic2.api.energy.tile.IEnergySource
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.common.Optional

@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "industrialcraft"),
                @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "industrialcraft")
        ]
)
class EnergyUnitStorage implements IEnergySource, IEnergySink, IVoltageTier {

    private double euEnergy;
    private double capacity;
    private double maxReceive;
    private double maxExtract;
    private int sourceTier;
    private int sinkTier;
    private EnumVoltage voltage;

    EnergyUnitStorage(double capacity, int sourceTier, int sinkTier) {
        this(capacity, capacity, capacity, sourceTier, sinkTier, 0);
    }

    EnergyUnitStorage(double capacity, double maxTransfer, int sourceTier, int sinkTier) {
        this(capacity, maxTransfer, maxTransfer, sourceTier, sinkTier, 0);
    }

    EnergyUnitStorage(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier) {
        this(capacity, maxReceive, maxExtract, sourceTier, sinkTier, 0);
    }

    EnergyUnitStorage(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier, double euEnergy) {
        setCapacity(capacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
        setSourceTier(sourceTier);
        setSinkTier(sinkTier);
        setEnergyStored(euEnergy);
    }

    void setEnergyStored(double euEnergy) {
        this.euEnergy = euEnergy;
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

    void modifyEnergyStored(double euEnergy) {
        this.euEnergy = euEnergy;
        if(euEnergy > this.capacity) {
            this.euEnergy = this.capacity;
        } else if(this.euEnergy < 0) {
            this.euEnergy = 0;
        }
    }

    @Override
    double getDemandedEnergy() {
        return Math.max(0.0, capacity - euEnergy);
    }

    @Override
    double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        double toInject = Math.min(euEnergy, Math.min(this.maxExtract, amount));
        euEnergy -= toInject;
        return toInject;
    }

    @Override
    void drawEnergy(double amount) {
        double toDraw = Math.min(capacity - euEnergy, Math.min(this.maxReceive, amount));
        euEnergy += toDraw;
        amount = toDraw;
    }

    @Override
    double getOfferedEnergy() {
        return euEnergy;
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
        if(iEnergyAcceptor != null) {
            return true
        }
        return false;
    }

    @Override
    boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        if(iEnergyEmitter != null) {
            return true
        }
        return false;
    }

    @Override
    void setVoltageTier(EnumVoltage voltage) {
        this.voltage = voltage;
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
