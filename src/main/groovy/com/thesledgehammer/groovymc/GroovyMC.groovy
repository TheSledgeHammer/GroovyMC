package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.client.model.ModelEntryRegistery
import com.thesledgehammer.groovymc.experimental.blocks.ModBlocks
import com.thesledgehammer.groovymc.input.InputHandler
import com.thesledgehammer.groovymc.input.KeyBindings
import com.thesledgehammer.groovymc.proxy.CommonProxy
import com.thesledgehammer.groovymc.utils.ObjectManager
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = GroovyMC.MOD_ID, name = GroovyMC.MOD_NAME, version = GroovyMC.VERSION, acceptedMinecraftVersions = GroovyMC.MCVERSION, modLanguageAdapter = "com.thesledgehammer.groovymc.api.GroovyLanguageAdapter", dependencies = "")
class GroovyMC {

	static final String MOD_ID = "groovymc";
	static final String MOD_NAME = "GroovyMC";
	static final String VERSION = "1.0.4";
	static final String MCVERSION = "1.12.2";

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

		ModelEntryRegistery.preInit();
	}

	@Mod.EventHandler
	static void init(FMLInitializationEvent event) {
		proxy.init(event);

		MinecraftForge.EVENT_BUS.register(new InputHandler());
		KeyBindings.init();
		ObjectManager.RegisterColors();
	}

	@Mod.EventHandler
	static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
