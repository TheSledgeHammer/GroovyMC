package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.client.model.ModelEntryRegistery
import com.thesledgehammer.groovymc.config.MCMod
import com.thesledgehammer.groovymc.input.InputHandler
import com.thesledgehammer.groovymc.input.KeyBindings
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = GroovyMC.MOD_ID, name = GroovyMC.MOD_NAME, version = GroovyMC.VERSION, acceptedMinecraftVersions = GroovyMC.MCVERSION, modLanguageAdapter = GroovyMC.GROOVY_LANGUAGE_ADAPTER)
class GroovyMC {

	static final String MOD_ID = "groovymc";
	static final String MOD_NAME = "GroovyMC";
	static final String VERSION = "1.1.0";
	static final String MCVERSION = "1.12.2";
	static final String GROOVY_LANGUAGE_ADAPTER = "com.thesledgehammer.groovymc.api.GroovyLanguageAdapter";

	@Mod.Instance("groovymc")
	static GroovyMC instance;

	static Logger logger;

	@Mod.EventHandler
	static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MCMod.Info(event);
		ModelEntryRegistery.preInit();
	}

	@Mod.EventHandler
	static void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new InputHandler());
		KeyBindings.init();
	}

	@Mod.EventHandler
	static void postInit(FMLPostInitializationEvent event) {

	}

	/*
	GroovyLoader Example:
	private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
	*/
}
