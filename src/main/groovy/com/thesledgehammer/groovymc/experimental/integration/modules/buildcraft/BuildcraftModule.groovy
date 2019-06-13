package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import com.thesledgehammer.groovymc.api.integration.BlankModule
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class BuildcraftModule extends BlankModule {

    private static BuildcraftModule instance;

    BuildcraftModule() {
        super("buildcraft");
        instance = this;
    }

    static BuildcraftModule Instance() {
        if(instance != null) {
            return instance;
        }
        return null;
    }

    @Override
    void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    void init(FMLInitializationEvent event) {

    }

    @Override
    void postInit(FMLPostInitializationEvent event) {

    }
}
