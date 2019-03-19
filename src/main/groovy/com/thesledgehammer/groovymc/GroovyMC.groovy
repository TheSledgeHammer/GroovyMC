package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.config.MCMod
import com.thesledgehammer.groovymc.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = GroovyMC.MOD_ID, name = GroovyMC.MOD_NAME, version = GroovyMC.VERSION, acceptedMinecraftVersions = GroovyMC.MCVERSION, modLanguageAdapter = GroovyMC.GROOVY_LANGUAGE_ADAPTER)
class GroovyMC {

	static final String MOD_ID = "groovymc";
	static final String MOD_NAME = "GroovyMC";
	static final String VERSION = "1.0.5";
	static final String MCVERSION = "1.12.2";
	static final String GROOVY_LANGUAGE_ADAPTER = "com.thesledgehammer.groovymc.api.GroovyLanguageAdapter";

	@SidedProxy(clientSide = "com.thesledgehammer.groovymc.proxy.ClientProxy", serverSide = "com.thesledgehammer.groovymc.proxy.CommonProxy")
	static CommonProxy proxy;

	@Mod.Instance("groovymc")
	static GroovyMC instance;

	//GroovyLoader Example:
	//private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
	static Logger logger;

	@Mod.EventHandler
	static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
		MCMod.Info(event);
	}

	@Mod.EventHandler
	static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
