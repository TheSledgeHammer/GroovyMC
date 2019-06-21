package com.thesledgehammer.groovymc.api.integration

import com.thesledgehammer.groovymc.experimental.integration.ModuleContainer
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

abstract class BlankModule implements IModule {

    private String modID;

    BlankModule(String modID) {
        setModID(modID);
        ModuleContainer.MODULES_CONTAINER().add(this);
    }

    private void setModID(String modID) {
        this.modID = modID;
    }

    String getModID() {
        return modID;
    }

    @Override
    abstract void init();
}
