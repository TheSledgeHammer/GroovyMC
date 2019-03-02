package com.thesledgehammer.groovymc.proxy

import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

class ServerProxy extends CommonProxy {

    	@Override
	void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	void init(FMLInitializationEvent event) {
		super.init(event);

	}


	void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
