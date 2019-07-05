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

package com.thesledgehammer.groovymc.client.definitions.model

import com.thesledgehammer.groovymc.api.ISprite
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelEntryHolderRegistry {

    private static final List<ModelEntryHolder> HOLDERS = new ArrayList<>();

    static void preInit() {
        if(!HOLDERS.isEmpty() || HOLDERS.size() != 0) {
            MinecraftForge.EVENT_BUS.register(ModelEntryHolderRegistry.class);
        }
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
            TextureEntry textureEntry = TextureEntry.Instance();

            for(TextureAtlasSprite sprite : textureEntry.getTextureAtlasSprites()) {
                if(textureEntry.getTextureAtlasSprite(sprite) instanceof ISprite) {
                    textureEntry.onTextureStitchPre(map);
                }
                map.setTextureEntry(textureEntry.getTextureAtlasSprite(sprite));
            }

            for(ResourceLocation location : textureEntry.getResourceLocations()) {
                map.registerSprite(textureEntry.getResourceLocation(location));
            }
        }
    }

    @SubscribeEvent
    static void onModelBake(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for(ModelEntryHolder holder : ENTRY_HOLDERS()) {
            holder.onModelBake();
            ModelEntry modelEntry = ModelEntry.Instance();

            for(ModelResourceLocation modelResource : modelEntry.getModelResourceLocations()) {
                for(IBakedModel bakedModel : modelEntry.getIBakedModels()) {
                    registry.putObject(modelEntry.getModelResourceLocation(modelResource), modelEntry.getIBakedModel(bakedModel));
                }
            }
        }
    }
}