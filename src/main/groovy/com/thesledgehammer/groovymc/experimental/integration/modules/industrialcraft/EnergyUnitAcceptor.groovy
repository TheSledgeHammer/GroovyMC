package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import net.minecraft.util.EnumFacing

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
