package com.thesledgehammer.groovymc.api.integration

import com.thesledgehammer.groovymc.experimental.integration.ModuleContainer
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

abstract class BlankModule implements IModule {

    private String modID;

    BlankModule(String modID) {
        setModID(modID);
        ModuleContainer.CONTAINER.add(this);
    }

    private void setModID(String modID) {
        this.modID = modID;
    }

    String getModID() {
        return modID;
    }

    @Override
    abstract void preInit(FMLPreInitializationEvent event);

    @Override
    abstract void init(FMLInitializationEvent event);

    @Override
    abstract void postInit(FMLPostInitializationEvent event);
}
