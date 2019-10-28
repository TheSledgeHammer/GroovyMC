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

package com.thesledgehammer.groovymc.modules


import com.thesledgehammer.groovymc.api.modules.BlankRenderEventModule
import com.thesledgehammer.groovymc.utils.Log
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent
import org.apache.logging.log4j.Level

//@Mod.EventBusSubscriber(modid = GroovyMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
class RenderEventModuleContainer {

    private static List<BlankRenderEventModule> EVENTS = new LinkedList<>();

    static List<BlankRenderEventModule> EVENT_CONTAINER() {
        return EVENTS;
    }

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(RenderEventModuleContainer.class);
        for(BlankRenderEventModule module : EVENTS) {
            if (isRegistered(module)) {
                Log.logInfo("preInit() ${module.getModID()} ${module.getEventName()}...")
                module.preInit();
                Log.logInfo("${module.getModID()}'s ${module.getEventName()} has been loaded")
            }
        }
    }

    static void Init() {
        for(BlankRenderEventModule module : EVENTS) {
            if (isRegistered(module)) {
                Log.logInfo("Init() ${module.getModID()} ${module.getEventName()}...");
                module.Init();
                Log.logInfo("${module.getModID()}'s ${module.getEventName()} has been loaded");
            }
        }
    }

    static void postInit() {
        for(BlankRenderEventModule module : EVENTS) {
            if (isRegistered(module)) {
                Log.logInfo("postInit() ${module.getModID()} ${module.getEventName()}...");
                module.postInit()
                Log.logInfo("${module.getModID()}'s ${module.getEventName()} has been loaded");
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    static void onModelBake(ModelBakeEvent event) {
       // ModelEntryHolderManager.Instance().onModelBake(event);
        for(BlankRenderEventModule module : EVENTS) {
            if(isRegistered(module)) {
                module.onModelBake(event);
                Log.log(Level.INFO, "${module.getModID()}'s ${module.getEventName()} started onModelBake()");
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    static void onTextureStitchPre(TextureStitchEvent.Pre event) {
       // TextureMap textureMap = event.getMap();
        //ModelEntryHolderManager.Instance().onTextureStitchPre(textureMap);
        for(BlankRenderEventModule module : EVENTS) {
            if(isRegistered(module)) {
           //     module.onTextureStitchPre(textureMap);
                Log.log(Level.INFO, "${module.getModID()}'s ${module.getEventName()} started onTextureStitchPre()");
            }
        }
    }

    private static boolean isRegistered(BlankRenderEventModule module) {
        if(module != null) {
           // if(Loader.isModLoaded(module.getModID()) && module.getEventName() != null) {
                return true;
           // }
        }
        return false;
    }
}
