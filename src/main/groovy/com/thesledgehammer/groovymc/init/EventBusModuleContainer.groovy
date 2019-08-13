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
import com.thesledgehammer.groovymc.test.render.RenderEngine
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.Level

class EventBusModuleContainer {

    private static List<BlankEventBusModule> EVENTS = new LinkedList<>();

    static List<BlankEventBusModule> EVENT_CONTAINER() {
        return EVENTS;
    }

    static void preInit() {
        registerModules();
        MinecraftForge.EVENT_BUS.register(EventBusModuleContainer.class);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    static void onModelBake(ModelBakeEvent event) {

        for(BlankEventBusModule module : EVENTS) {
            if(isRegistered(module)) {
                module.onModelBake(event);
                Log.log(Level.INFO, "${module.getModID()} onModelBake ${module.getEventName()}  has been loaded");
            }
        }
//        ModelEntryHolderManager.Instance().onModelBake(event);
    }

    @SideOnly(Side.CLIENT)
    static void onTextureStitchPre(TextureMap textureMap) {
        ModelEntryHolderManager.Instance().onTextureStitchPre(textureMap);
    }

    private static boolean isRegistered(BlankEventBusModule module) {
        if(module != null) {
            if(Loader.isModLoaded(module.getModID()) && module.getEventName() != null) {
                return true;
            }
        }
        return false;
    }

    private static void registerModules() {
       RenderEngine.Instance();// engine = new RenderEngine(RenderMK1.MODEL);
    }
}
