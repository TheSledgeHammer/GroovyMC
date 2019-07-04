package com.thesledgehammer.groovymc.integration.modules.tesla

import com.thesledgehammer.groovymc.api.minecraftjoules.EnumVoltage
import com.thesledgehammer.groovymc.api.minecraftjoules.IVoltageTier

class TieredTeslaTile extends TeslaTile implements IVoltageTier {

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
        setVoltageTier(voltage);
    }

    @Override
    void setVoltageTier(EnumVoltage voltage) {
        tesla.setVoltageTier(voltage);
    }

    @Override
    EnumVoltage getVoltageTier() {
        return tesla.getVoltageTier();
    }

    @Override
    long getVoltage() {
        return tesla.getVoltage();
    }

    @Override
    long givePower(long power, boolean simulated) {
        long give = tesla.givePower(power, simulated);
        if(give >= getVoltage() * 32) {
            return getVoltage() * 32;
        }
        return give;
    }

    @Override
    long takePower(long power, boolean simulated) {
        long take = tesla.takePower(power, simulated)
        if(take >= getVoltage() * 32) {
            return getVoltage() * 32;
        }
        return take;
    }
}
