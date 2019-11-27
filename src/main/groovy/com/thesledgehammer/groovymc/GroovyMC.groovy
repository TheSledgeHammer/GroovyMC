package com.thesledgehammer.groovymc

import com.thesledgehammer.groovyforge.FMLGroovyModLoadingContext
import com.thesledgehammer.groovymc.modules.ModuleContainerManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(GroovyMC.MOD_ID)
@Mod.EventBusSubscriber(modid = GroovyMC.MOD_ID)
class GroovyMC {

	static final String MOD_ID = "groovymc";

	static GroovyMC instance;
	static ModContainer MOD_CONTAINER;

	static final Logger LOGGER = LogManager.getLogger();

	GroovyMC() {
		instance = this;
		MOD_CONTAINER = ModLoadingContext.get().getActiveContainer();

		ModuleContainerManager.preInit();
		ModuleContainerManager.Init();
		ModuleContainerManager.postInit();
		Registry.init();
		MinecraftForge.EVENT_BUS.register(this);
	}

	/**
	 * ModEventBusRegister: For each class you wish to register. Add this function into GroovyMC().
	 * @param aClass: The class being registered.
	 */
	private static void ModEventBusRegister(Class aClass) {
		FMLGroovyModLoadingContext.get().getModEventBus().register(aClass);
	}

	//GroovyLoader Example:
	//private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
}
