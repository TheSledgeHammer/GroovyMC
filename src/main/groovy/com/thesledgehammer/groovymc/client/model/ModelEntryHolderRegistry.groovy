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
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge

class ModelEntryHolderRegistry {

    private static final List<ModelEntryHolder> HOLDERS = new ArrayList<>();

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(ModelEntryHolderRegistry.class);
    }

    static void init() {

    }

    static void postInit() {

    }

    static List<ModelEntryHolder> ENTRY_HOLDERS() {
        return HOLDERS;
    }

    static void onTextureStitchPre(TextureMap map) {
        Set<ResourceLocation> toStitch = new HashSet<>();
        for(ModelEntryHolder holder : ENTRY_HOLDERS()) {
            holder.onTextureStitchPre(toStitch);
            TextureEntry textureEntry = holder.TextureEntry();

            for(TextureAtlasSprite sprite : textureEntry.getTextureAtlasSprites()) {
                if(textureEntry.getTextureAtlasSprite(sprite) instanceof ISprite) {
                    textureEntry.GroovyDefinitionContext().onTextureStitchPre(map);
                }
                map.setTextureEntry(textureEntry.getTextureAtlasSprite(sprite));
            }
            for(ResourceLocation location : textureEntry.getResourceLocations()) {
                map.registerSprite(textureEntry.getResourceLocation(location));
            }
        }
    }

    static void onModelBake(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for(ModelEntryHolder holder : ENTRY_HOLDERS()) {
            holder.onModelBake();
            ModelEntry modelEntry = holder.ModelEntry();

            for(ModelResourceLocation modelResource : modelEntry.getModelResourceLocations()) {
                for(IBakedModel bakedModel : modelEntry.getIBakedModels()) {
                    registry.putObject(modelEntry.getModelResourceLocation(modelResource), modelEntry.getIBakedModel(bakedModel));
                }
            }
        }
    }
}
