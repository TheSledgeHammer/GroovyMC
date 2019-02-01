/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc

import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = GroovyMC.MOD_ID, name = GroovyMC.MOD_NAME, version = GroovyMC.VERSION, acceptedMinecraftVersions = GroovyMC.MCVERSION, dependencies = "")
class GroovyMC {

	static final String MOD_ID = "groovymc";
	static final String MOD_NAME = "GroovyMC";
	static final String VERSION = "1.0.2";
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
		Constants.setMCModInfo(event);
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
