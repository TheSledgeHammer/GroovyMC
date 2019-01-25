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
import net.minecraft.util.ITickable
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

abstract class ForgeEnergyTileTickable extends ForgeEnergyTile implements ITickable {

    ForgeEnergyTileTickable(String tileName, int capacity, int maxTransfer) {
        super(tileName, capacity, maxTransfer)
    }

    void EnergyToTransferOnTick() {
        if (world.isRemote) {
            return;
        }

        if (!canExtract()) {
            return;
        }

        for(EnumFacing face : EnumFacing.VALUES) {
            TileEntity tile = world.getTileEntity(pos.offset(face));
            if(tile == null) {
                continue;
            } else if(tile.hasCapability(CapabilityEnergy.ENERGY, face.getOpposite())) {
                IEnergyStorage tileFE = tile.getCapability(CapabilityEnergy.ENERGY, face.getOpposite());
                int toDraw = Math.min(fe.getCapacity(), fe.getMaxExtract());
                if(toDraw > 0 && tileFE.receiveEnergy(toDraw, true) > 0) {
                    int extract = tileFE.receiveEnergy(toDraw, false);
                    tileFE.extractEnergy(extract, false);
                }
            }
        }
    }
}
