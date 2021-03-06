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
<<<<<<< HEAD
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolderManager
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.Level
=======
import com.thesledgehammer.groovymc.utils.Log
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.ModList
>>>>>>> 1.16.x

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
<<<<<<< HEAD

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
=======
/*
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
>>>>>>> 1.16.x
    static void onModelBake(ModelBakeEvent event) {
        ModelEntryHolderManager.Instance().onModelBake(event);
        for(BlankRenderEventModule module : EVENTS) {
            if(isRegistered(module)) {
                module.onModelBake(event);
                Log.log(Level.INFO, "${module.getModID()}'s ${module.getEventName()} started onModelBake()");
            }
        }
    }

    @SubscribeEvent
<<<<<<< HEAD
    @SideOnly(Side.CLIENT)
    static void onTextureStitchPre(TextureStitchEvent.Pre event) {
        TextureMap textureMap = event.getMap();
=======
    @OnlyIn(Dist.CLIENT)
    static void onTextureStitchPre(TextureStitchEvent.Pre event) {
        AtlasTexture textureMap = event.getMap();
>>>>>>> 1.16.x
        ModelEntryHolderManager.Instance().onTextureStitchPre(textureMap);
        for(BlankRenderEventModule module : EVENTS) {
            if(isRegistered(module)) {
                module.onTextureStitchPre(textureMap);
                Log.log(Level.INFO, "${module.getModID()}'s ${module.getEventName()} started onTextureStitchPre()");
            }
        }
    }
<<<<<<< HEAD

    //TODO
    @SideOnly(Side.CLIENT)
    static void onTextureStitchPost(TextureStitchEvent.Post event) {
        TextureMap textureMap = event.getMap();
    }

    private static boolean isRegistered(BlankRenderEventModule module) {
        if(module != null) {
            if(Loader.isModLoaded(module.getModID()) && module.getEventName() != null) {
=======
    */
    private static boolean isRegistered(BlankRenderEventModule module) {
        ModList modList = ModList.get();
        if(module != null) {
            if(modList.isLoaded(module.getModID())) {
>>>>>>> 1.16.x
                return true;
            }
        }
        return false;
    }
}
