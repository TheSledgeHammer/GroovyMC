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

import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.common.Optional

@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "ic2.api.energy.tile.IEnergyAcceptor", modid = "industrialcraft", striprefs = true)
        ]
)
class EnergyUnitAcceptor implements IEnergyAcceptor {

    private EnergyUnitEmitter emitter;

    EnergyUnitAcceptor() {
        emitter = new EnergyUnitEmitter(this);
    }

    EnergyUnitAcceptor(EnergyUnitEmitter emitter) {
        this.emitter = emitter;
    }

    EnergyUnitEmitter getEnergyUnitEmitter() {
        return emitter;
    }

    @Override
    boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing face) {
        if(emitter instanceof IEnergyEmitter) {
            iEnergyEmitter = emitter;
            return emitter.emitsEnergyTo(this, face);
        }
        return false
    }
}
