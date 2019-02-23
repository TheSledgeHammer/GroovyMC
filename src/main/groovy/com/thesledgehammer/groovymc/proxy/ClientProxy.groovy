/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.proxy


import com.thesledgehammer.groovymc.experimental.patterns.ModelEntryRegistery
import com.thesledgehammer.groovymc.input.InputHandler
import com.thesledgehammer.groovymc.input.KeyBindings
import com.thesledgehammer.groovymc.utils.ObjectManager
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ClientProxy extends CommonProxy {
	
	@Override
	void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		//Typically initialization of models and such goes here:
	}
	
	@Override
	void init(FMLInitializationEvent event) {
		super.init(event);
		MinecraftForge.EVENT_BUS.register(new InputHandler());
		KeyBindings.init();
        ObjectManager.RegisterColors();
	}

	void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@Override
	void registerItem(Item item) {
		ObjectManager.registerItemClient(item);
	}

	@Override
	void registerBlock(Block block) {
		ObjectManager.registerBlockClient(block);
	}
}