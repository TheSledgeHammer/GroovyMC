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
package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import ic2.api.energy.tile.IEnergyTile
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing

class EnergyUnitTile extends GroovyTileBasic implements IEnergyUnitStorage, IEnergyTile {

    protected EnergyUnits eu;
    private double energy;
    private String tileName; //Needed For Tile NBT Only

    EnergyUnitTile(String tileName, double capacity, double maxTransfer, int sourceTier, int sinkTier) {
        eu = new EnergyUnits(capacity, maxTransfer, sourceTier, sinkTier);
        this.tileName = tileName;
    }

    EnergyUnitTile(String tileName, double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier) {
        eu = new EnergyUnits(capacity, maxReceive, maxExtract, sourceTier, sinkTier);
        this.tileName = tileName;
    }

    @Override
    double getDemandedEnergy() {
        return eu.getDemandedEnergy();
    }

    @Override
    int getSinkTier() {
        return eu.getSinkTier();
    }

    @Override
    double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        return eu.injectEnergy(enumFacing, amount, voltage);
    }

    @Override
    boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return eu.acceptsEnergyFrom(iEnergyEmitter, enumFacing);
    }

    @Override
    double getOfferedEnergy() {
        return eu.getOfferedEnergy();
    }

    @Override
    double getMaxEnergyStored() {
        return eu.getMaxEnergyStored();
    }

    @Override
    void drawEnergy(double amount) {
        eu.drawEnergy(amount);
    }

    @Override
    int getSourceTier() {
        return eu.getSourceTier();
    }

    @Override
    boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return eu.emitsEnergyTo(iEnergyAcceptor, enumFacing);
    }

    @Override
    void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        if (tagCompound.hasKey(tileName)) {
            energy = tagCompound.getCompoundTag(tileName).getDouble("energy");
        }
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if (energy > 0) {
            NBTTagCompound data = new NBTTagCompound();
            data.setDouble("energy", getOfferedEnergy());
            tagCompound.setTag(tileName, data);
        }
        return tagCompound;
    }
}
