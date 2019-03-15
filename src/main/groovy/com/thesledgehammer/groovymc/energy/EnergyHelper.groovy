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
package com.thesledgehammer.groovymc.energy

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class EnergyHelper {

    static void TransferOnTick(ForgeEnergy fe, TileEntity tile, EnumFacing face) {
        if(tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            int toDraw = Math.min(fe.getCapacity(), fe.getMaxExtract());
            if(toDraw > 0 && tileFE.receiveEnergy(toDraw, true) > 0) {
                int extract = tileFE.receiveEnergy(toDraw, false);
                tileFE.extractEnergy(extract, false);
            }
        }
    }

    static void ExtractOnTick(TileEntity tile, EnumFacing face, int toExtract) {
        if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            if(toExtract > 0 && tileFE.canExtract()) {
                tileFE.extractEnergy(toExtract, false);
            }
        }
    }

    static void ReceiveOnTick(TileEntity tile, EnumFacing face, int toReceive) {
        if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            if(toReceive > 0 && tileFE.canReceive()) {
                tileFE.receiveEnergy(toReceive, false);
            }
        }
    }

    static int ExtractOnTick(TileEntity tile, EnumFacing face, int toExtract, boolean simulate) {
        if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            if(toExtract > 0 && tileFE.canExtract()) {
                return tileFE.extractEnergy(toExtract, simulate);
            }
        }
        return 0
    }

    static int ReceiveOnTick(TileEntity tile, EnumFacing face, int toReceive, boolean simulate) {
        if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            if(toReceive > 0 && tileFE.canReceive()) {
                return tileFE.receiveEnergy(toReceive, simulate);
            }
        }
        return 0;
    }
}
