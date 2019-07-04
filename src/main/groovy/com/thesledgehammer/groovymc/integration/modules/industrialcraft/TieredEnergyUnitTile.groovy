package com.thesledgehammer.groovymc.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier
import net.minecraft.util.EnumFacing

class TieredEnergyUnitTile extends EnergyUnitTile implements IVoltageTier {
    
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
        setVoltageTier(voltage);
    }

    @Override
    void setVoltageTier(EnumVoltage voltage) {
        eu.setVoltageTier(voltage);
    }

    @Override
    EnumVoltage getVoltageTier() {
        return eu.getVoltageTier();
    }

    @Override
    long getVoltage() {
        return eu.getVoltage();
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
