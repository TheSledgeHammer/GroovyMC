package com.thesledgehammer.groovymc.experimental.integration.api

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

interface IModule {

    @Mod.EventHandler
    void preInit(FMLPreInitializationEvent event);

    @Mod.EventHandler
    void Init(FMLInitializationEvent event);

    @Mod.EventHandler
    void postInit(FMLPostInitializationEvent event);

}