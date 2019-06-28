package com.thesledgehammer.groovymc.integration.modules.tesla

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage

class TieredTeslaTile extends TeslaTile {

    private EnumVoltage voltage;

    TieredTeslaTile(long capacity, EnumVoltage voltage) {
        this(capacity, capacity, capacity, 0, voltage)
    }

    TieredTeslaTile(long capacity, long maxTransfer, EnumVoltage voltage) {
        this(capacity, maxTransfer, maxTransfer, 0,  voltage)
    }

    TieredTeslaTile(long capacity, long maxReceive, long maxExtract, EnumVoltage voltage) {
        this(capacity, maxReceive, maxExtract, 0, voltage)
    }

    TieredTeslaTile(long capacity, long maxReceive, long maxExtract, long teslaEnergy, EnumVoltage voltage) {
        super(capacity, maxReceive, maxExtract, teslaEnergy)
        this.voltage = voltage;
    }

    EnumVoltage getVoltageTier() {
        return voltage;
    }

    long getVoltage() {
        return voltage.getVoltage() * 32;
    }

    @Override
    long givePower(long power, boolean simulated) {
        long give = tesla.givePower(power, simulated);
        if(give >= maxTeslaIO(voltage)) {
            return maxTeslaIO(voltage);
        }
        return give;
    }

    @Override
    long takePower(long power, boolean simulated) {
        long take = tesla.takePower(power, simulated)
        if(take >= maxTeslaIO(voltage)) {
            return maxTeslaIO(voltage);
        }
        return take;
    }

    private static long maxTeslaIO(EnumVoltage voltage) {
        long volts = voltage.getVoltage();
        return (volts * 32);
    }
}
