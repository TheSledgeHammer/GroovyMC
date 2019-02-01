/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.proxy

import com.thesledgehammer.groovymc.GroovyMC
import com.thesledgehammer.groovymc.config.GroovyConfig
import com.thesledgehammer.groovymc.experimental.blocks.ModBlocks
import com.thesledgehammer.groovymc.network.PacketHandler
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class CommonProxy {
	
	static Configuration config;

	void preInit(FMLPreInitializationEvent event) {
		File directory = event.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), GroovyMC.MOD_ID + ".cfg"));
		GroovyConfig.readConfig();
		PacketHandler.registerMessages("groovymc");
		// Initialization of blocks and items typically goes here:
	}
	
	 void init(FMLInitializationEvent event) {
		 
	 }
	 
	 void postInit(FMLPostInitializationEvent event) {
		 if(config.hasChanged()) {
			 config.save();
		 }
	 }

	void registerItem(Item item) {

	}

	void registerBlock(Block block) {

	}
}