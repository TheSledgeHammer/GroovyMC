package com.thesledgehammer.groovymc.experimental.integration

import com.thesledgehammer.groovymc.api.integration.BlankModule
import com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft.BuildcraftModule
import com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft.IndustrialcraftModule
import com.thesledgehammer.groovymc.experimental.integration.modules.theoneprobe.TheOneProbeCompatibilityModule
import mcjty.theoneprobe.TheOneProbe
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
        module.init(event);
    }

    private static void ModulePostInit(BlankModule module, FMLPostInitializationEvent event) {
        module.postInit(event);
    }

    private static void preRegisteredModules() {
        BuildcraftModule BC = new BuildcraftModule();
        IndustrialcraftModule IC2 = new IndustrialcraftModule();
        TheOneProbeCompatibilityModule TOP = new TheOneProbeCompatibilityModule(TheOneProbe.theOneProbeImp);
    }
}
