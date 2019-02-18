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
/*
    static int insertIntoEnergyReceiver(TileEntity tile, EnumFacing face, int energy, boolean simulate) {
        if(tile instanceof IEnergyStorage) {
            return tile.receiveEnergy(energy, simulate);
        } else if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            return tile.getCapability(CapabilityEnergy.ENERGY, face).receiveEnergy(energy, simulate);
        }
        return 0;
    }

    static int extractFromEnergyProvider(TileEntity tile, EnumFacing face, int energy, boolean simulate) {
        if(tile instanceof IEnergyStorage) {
            return tile.extractEnergy(energy, simulate);
        } else if(tile != null && tile.hasCapability(CapabilityEnergy.ENERGY, face)) {
            return tile.getCapability(CapabilityEnergy.ENERGY, face).extractEnergy(energy, simulate);
        }
        return 0;
    }*/
}
