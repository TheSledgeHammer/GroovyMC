package com.thesledgehammer.groovymc.api.integration

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

interface IModule {

    void init();

    /*
    @Mod.EventHandler
    void preInit(FMLPreInitializationEvent event);

    @Mod.EventHandler
    void init(FMLInitializationEvent event);

    @Mod.EventHandler
    void postInit(FMLPostInitializationEvent event);
*/
}