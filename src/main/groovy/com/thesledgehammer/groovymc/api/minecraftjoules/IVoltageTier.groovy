package com.thesledgehammer.groovymc.api.minecraftjoules

interface IVoltageTier {

    void setVoltageTier(EnumVoltage voltage);

    EnumVoltage getVoltageTier();

    long getVoltage();
}