package com.thesledgehammer.groovymc.experimental.integration

import com.thesledgehammer.groovymc.experimental.integration.api.BlankModule
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ModuleContainer {

    static List<BlankModule> CONTAINER = new LinkedList<>();

    static void preInit(FMLPreInitializationEvent event) {
        for(BlankModule module : CONTAINER) {
            if(Loader.isModLoaded(module.getModID())) {
                ModulePreInit(module, event);
            }
        }
    }

    static void init(FMLInitializationEvent event) {
        for(BlankModule module : CONTAINER) {
            if(Loader.isModLoaded(module.getModID())) {
                ModuleInit(module, event);
            }
        }
    }

    static void postInit(FMLPostInitializationEvent event) {
        for(BlankModule module : CONTAINER) {
            if(Loader.isModLoaded(module.getModID())) {
                ModulePostInit(module, event);
            }
        }
    }

    private static void ModulePreInit(BlankModule module, FMLPreInitializationEvent event) {
        module.preInit(event);
    }

    private static void ModuleInit(BlankModule module, FMLInitializationEvent event) {
        module.Init(event);
    }

    private static void ModulePostInit(BlankModule module, FMLPostInitializationEvent event) {
        module.postInit(event);
    }
}
