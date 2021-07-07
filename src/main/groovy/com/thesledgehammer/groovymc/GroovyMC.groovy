package com.thesledgehammer.groovymc

<<<<<<< HEAD
import com.thesledgehammer.groovymc.modules.ModuleContainers
=======
import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.modules.ModuleContainerManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.ModContainer
import net.minecraftforge.fml.ModLoadingContext
>>>>>>> 1.16.x
import net.minecraftforge.fml.common.Mod
import ooo.thesledgehammer.groovyforge.FMLGroovyModLoadingContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

<<<<<<< HEAD
@Mod(
		modid = GroovyMC.MOD_ID,
		name = GroovyMC.MOD_NAME,
		version = GroovyMC.VERSION,
		acceptedMinecraftVersions = GroovyMC.MCVERSION,
		updateJSON = GroovyMC.UPDATE_JSON,
		modLanguageAdapter = GroovyMC.GROOVY_LANGUAGE_ADAPTER,
		dependencies = GroovyMC.DEPENDENCIES,
		certificateFingerprint = GroovyMC.CERTIFICATE_FINGERPRINT
)
class GroovyMC {

	static final String MOD_ID = "groovymc";
	static final String MOD_NAME = "GroovyMC";
	static final String VERSION = "@VERSION@";
	static final String MCVERSION = "1.12.2";
	static final String UPDATE_JSON = "@UPDATE@"
	static final String GROOVY_LANGUAGE_ADAPTER = "com.thesledgehammer.groovyforge.GroovyLanguageAdapter";
	static final String DEPENDENCIES = "required-after:groovyforge;"
	static final String CERTIFICATE_FINGERPRINT = "616437EBB587FE5A83EE547EA1D2E1C403B074CF"
=======
@Mod(Constants.MOD_ID)
class GroovyMC {

	static GroovyMC INSTANCE;
	static ModContainer MOD_CONTAINER;
>>>>>>> 1.16.x

	static final Logger LOGGER = LogManager.getLogger();

<<<<<<< HEAD
	static Logger logger;

	@Mod.EventHandler
	static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		ModuleContainers.preInit();
	}

	@Mod.EventHandler
	static void init(FMLInitializationEvent event) {
		ModuleContainers.Init();
	}

	@Mod.EventHandler
	static void postInit(FMLPostInitializationEvent event) {
		ModuleContainers.postInit();
	}

	//GroovyLoader Example:
	//private static final GroovyLoader groovyLoader = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID);
}
=======
	GroovyMC() {
		INSTANCE = this;
//		MOD_CONTAINER = ModLoadingContext.get().getActiveContainer();
		//IEventBus eventBus = FMLGroovyModLoadingContext.get().getModEventBus();
		PreInit();
		Init();
		PostInit();
		MinecraftForge.EVENT_BUS.register(this);
		//Register.Init(FMLGroovyModLoadingContext.get().getModEventBus());
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
>>>>>>> 1.16.x
