package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

import ic2.api.energy.tile.IEnergyAcceptor
import ic2.api.energy.tile.IEnergyEmitter
import net.minecraft.util.EnumFacing

class EnergyUnitEmitter implements IEnergyEmitter {

    private EnergyUnitAcceptor acceptor;

    EnergyUnitEmitter() {
        this.acceptor = new EnergyUnitAcceptor(this);
    }

    EnergyUnitEmitter(EnergyUnitAcceptor acceptor) {
        this.acceptor = acceptor;
    }

    EnergyUnitAcceptor getEnergyUnitAcceptor() {
        return acceptor;
    }

    @Override
    boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing face) {
        if(acceptor instanceof IEnergyAcceptor) {
            iEnergyAcceptor = acceptor;
            return acceptor.acceptsEnergyFrom(this, face);
        }
        return false
    }
}
