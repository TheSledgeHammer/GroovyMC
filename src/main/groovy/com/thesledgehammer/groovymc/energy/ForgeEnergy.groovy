/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.energy;

import net.minecraftforge.energy.EnergyStorage

class ForgeEnergy extends EnergyStorage implements ForgeEnergyTraits {

    ForgeEnergy(int capacity) {
        super(capacity);
        setMaxCapacityFETrait(capacity);
    }

    ForgeEnergy(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
        setMaxCapacityFETrait(capacity);
        setMaxReceiveFETrait(maxTransfer);
        setMaxExtractFETrait(maxTransfer);
    }

    ForgeEnergy(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
        setMaxCapacityFETrait(capacity);
        setMaxReceiveFETrait(maxReceive);
        setMaxExtractFETrait(maxExtract);
    }
}
