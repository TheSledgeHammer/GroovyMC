package com.thesledgehammer.groovymc.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import net.minecraft.util.EnumFacing

class TieredEnergyUnitTile extends EnergyUnitTile {

    private EnumVoltage voltage;

    TieredEnergyUnitTile(String tileName, double capacity, int sourceTier, int sinkTier, EnumVoltage voltage) {
        this(tileName, capacity, capacity, capacity, sourceTier, sinkTier, 0.0, voltage);
    }

    TieredEnergyUnitTile(String tileName, double capacity, double maxTransfer, int sourceTier, int sinkTier, EnumVoltage voltage) {
        this(tileName, capacity, maxTransfer, maxTransfer, sourceTier, sinkTier, 0.0, voltage);
    }

    TieredEnergyUnitTile(String tileName, double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier, EnumVoltage voltage) {
        this(tileName, capacity, maxReceive, maxExtract, sourceTier, sinkTier, 0.0, voltage);
    }

    TieredEnergyUnitTile(String tileName, double capacity, double maxReceive, double maxExtract, int sourceTier, int sinkTier, double euEnergy, EnumVoltage voltage) {
        super(tileName, capacity, maxReceive, maxExtract, sourceTier, sinkTier, euEnergy)
        this.voltage = voltage
    }

    EnumVoltage getVoltageTier() {
        return voltage;
    }

    long getVoltage() {
        return voltage.getVoltage();
    }

    @Override
    double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        return eu.injectEnergy(enumFacing, amount, voltage);
    }

    @Override
    void drawEnergy(double amount) {
        eu.drawEnergy(amount);
    }
}
