/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.input

import com.thesledgehammer.groovymc.network.PacketHandler
import com.thesledgehammer.groovymc.network.PacketSendKey
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent

class InputHandler {
	
	@SubscribeEvent
	void onKeyInput(InputEvent.KeyInputEvent event) {
		if(KeyBindings.masterKey.isPressed()) {
			PacketHandler.INSTANCE.sendToServer(new PacketSendKey());
		}
	}
}
