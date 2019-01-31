package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.blocks.properties.GroovyMachineProperties
import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTESR
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits

enum EnumTestType implements IBlockTypeTESR {
    TEST(createTestProperties(TestTile.class,"testTile"));

    private final MachinePropertyTraits machineProperty;

    EnumTestType(MachinePropertyTraits machineProperty) {
        this.machineProperty = machineProperty;
    }

    protected static MachinePropertyTraits createTestProperties(Class<? extends TestTile> teClass, String name) {
        GroovyMachineProperties<? extends TestTile> machinePropertiesTest = new GroovyMachineProperties<>(teClass, name);
        return machinePropertiesTest;
    }

    @Override
    MachinePropertyTraits getGroovyMachineProperties() {
        return machineProperty;
    }

    @Override
    String getName() {
        return getGroovyMachineProperties().getName();
    }
}
