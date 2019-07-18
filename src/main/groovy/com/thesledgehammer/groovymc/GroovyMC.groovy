package com.thesledgehammer.groovymc

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(GroovyMC.MOD_ID)
class GroovyMC {

	static final String MOD_ID = "groovymc";

	static GroovyMC instance;

	private static final Logger LOGGER = LogManager.getLogger();

	GroovyMC() {
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	//	CapabilityMj.register();
		Registry.init();
	}

	/*
	GroovyLoader Example:
	private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
	*/
}
