/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.input

import net.java.games.input.Keyboard
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry

//@SideOnly(Side.CLIENT)
class KeyBindings {
	static KeyBinding masterKey;
	
	static void init() {
		masterKey = new KeyBinding("key.master", Keyboard.KEY_T, "key.categories.groovymc");
		ClientRegistry.registerKeyBinding(masterKey);
	}
}
