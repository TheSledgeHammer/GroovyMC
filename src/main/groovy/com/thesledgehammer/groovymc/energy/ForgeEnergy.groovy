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

import com.thesledgehammer.groovymc.energy.traits.ForgeEnergyTraits;
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
