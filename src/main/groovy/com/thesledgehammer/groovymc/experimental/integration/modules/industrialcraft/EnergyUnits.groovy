package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

class EnergyUnits extends EnergyUnitStorage {

    EnergyUnits(double capacity, int sourceTier, int sinkTier) {
        super(capacity, sourceTier, sinkTier)
    }

    EnergyUnits(double capacity, double maxTransfer, int sourceTier, int sinkTier) {
        super(capacity, maxTransfer, sourceTier, sinkTier)
    }

    EnergyUnits(double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier) {
        super(capacity, maxReceive, maxExtract, sourceTier, sinkTier)
    }
}
