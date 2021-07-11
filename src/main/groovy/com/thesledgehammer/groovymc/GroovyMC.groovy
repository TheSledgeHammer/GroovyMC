package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.modules.ModuleContainerManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import ooo.thesledgehammer.groovyforge.FMLGroovyModLoadingContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(Constants.MOD_ID)
class GroovyMC {

	static GroovyMC INSTANCE;

	static final Logger LOGGER = LogManager.getLogger();

	GroovyMC() {
		INSTANCE = this;
		MinecraftForge.EVENT_BUS.register(INSTANCE);
		PreInit();
		Init();
		PostInit();
	}

	static void PreInit() {
		ModuleContainerManager.preInit();
		GroovyLoaderInit();
	}

	static void Init() {
		ModuleContainerManager.Init();
	}

	static void PostInit() {
		ModuleContainerManager.postInit();
	}

	static void GroovyLoaderInit() {
		final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
	}
}
