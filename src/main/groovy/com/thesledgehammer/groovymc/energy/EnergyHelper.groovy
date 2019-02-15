/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
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

    static void ExtractOnTick(ForgeEnergy fe, TileEntity tile, EnumFacing face, int toExtract) {
        if(tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            //int toExtract;
            if(toExtract > 0 && tileFE.canExtract()) {
                tileFE.extractEnergy(toExtract, false);
            }
        }
    }

    static void ReceiveOnTick(ForgeEnergy fe, TileEntity tile, EnumFacing face, int toReceive) {
        if(tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face);
            //int toReceive
            if(toReceive > 0 && tileFE.canReceive()) {
                tileFE.receiveEnergy(toReceive, false);
            }
        }
    }
}
