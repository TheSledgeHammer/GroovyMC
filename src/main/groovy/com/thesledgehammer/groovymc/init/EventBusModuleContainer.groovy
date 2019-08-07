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

package com.thesledgehammer.groovymc.init

import com.thesledgehammer.groovymc.api.modules.BlankEventBusModule

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolderManager
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class EventBusModuleContainer {

    private static List<BlankEventBusModule> EVENTS = new ArrayList<>();

    static List<BlankEventBusModule> EVENT_REGISTER() {
        return EVENTS;
    }

    static void preInit() {
        if(ModelEntryHolderManager.Instance().ENTRY_HOLDERS().isEmpty() || ModelEntryHolderManager.Instance().ENTRY_HOLDERS().size() != 0) {
            MinecraftForge.EVENT_BUS.register(EventBusModuleContainer.class);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    static void onModelBake(ModelBakeEvent event) {
        ModelEntryHolderManager.Instance().onModelBake(event);
        for(BlankEventBusModule events : EVENTS) {
            events.onModelBake(event);
            Log.logInfo("OnModelBake() ${events} has been loaded");
        }
    }

   // @SubscribeEvent
    @SideOnly(Side.CLIENT)
    static void registerTextureAtlasSprites(TextureMap textureMap) {
        ModelEntryHolderManager.Instance().onTextureStitchPre(textureMap);
        for(BlankEventBusModule events : EVENTS) {
            events.registerTextureAtlasSprites(textureMap);
            Log.logInfo("registerTextureAtlasSprites() ${events} has been loaded");
        }
    }
}
