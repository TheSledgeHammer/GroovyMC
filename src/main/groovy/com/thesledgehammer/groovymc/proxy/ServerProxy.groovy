/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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
