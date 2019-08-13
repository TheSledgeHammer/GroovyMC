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
package com.thesledgehammer.groovymc.api.client.definitions

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

interface IResourceDefinition {

    ResourceLocation getResourceLocation();

    ModelResourceLocation getModelResourceLocation();

    TextureAtlasSprite getTextureAtlasSprite();

    List<ResourceLocation> getResourceLocations();

    List<ModelResourceLocation> getModelResourceLocations();

    List<TextureAtlasSprite> getTextureAtlasSprites();

    void setResourceLocation(ResourceLocation resourceLocation);

    void setModelResourceLocation(ModelResourceLocation modelResourceLocation);

    void setTextureAtlasSprite(TextureAtlasSprite textureAtlasSprite);

    void setResourceLocation(String resource);

    void setResourceLocation(String modID, String resource);

    void setModelResourceLocation(String modelLocation);

    void setTextureAtlasSprite(String sprite);

    void setTextureAtlasSprite(ResourceLocation spriteLocation);

    void setTextureAtlasSprite(String modID, String baseName);

    void onTextureStitchPre(TextureMap map);

    void setCustomResourceLocation(String type, String fileName);

    void setCustomResourceLocation(String modID, String type, String fileName);

    void setCustomModelResourceLocation(String type, String fileName);
}