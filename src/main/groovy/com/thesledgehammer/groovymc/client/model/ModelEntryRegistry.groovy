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

package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.api.ISprite
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Deprecated
class ModelEntryRegistry {

    private static final List<ModelEntry> MODEL_ENTRIES = ModelEntry.Register.getModelEntries();
    private static final List<TextureEntry> TEXTURE_ENTRIES = TextureEntry.Register.getTextureEntries();

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(ModelEntryRegistry.class);
    }

    static void init() {

    }

    static void postInit() {

    }

    static void onTextureStitchPre(TextureMap map) {
        for(TextureEntry entry : TEXTURE_ENTRIES) {
            if(entry.getTextureAtlasSprite() instanceof ISprite) {
                entry.GroovyDefinitionContext().onTextureStitchPre(map);
            }
            map.registerSprite(entry.getResourceLocation());
            map.setTextureEntry(entry.getTextureAtlasSprite());
        }
    }

    @SubscribeEvent
    static void onBakeModels(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for(ModelEntry entry : MODEL_ENTRIES) {
            registry.putObject(entry.getModelResourceLocation(), entry.getIBakedModel());
        }
    }
}
