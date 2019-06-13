package com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft

import com.thesledgehammer.groovymc.api.integration.BlankModule
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class IndustrialcraftModule extends BlankModule {

    private static IndustrialcraftModule instance;

    IndustrialcraftModule() {
        super("industrialcraft");
        instance = this;
    }

    static IndustrialcraftModule Instance() {
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
