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
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.client.model.GroovysonModel
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

class GroovyDefinitionContext {

    private GroovyResourceDefinition resources;
    private GroovyModelDefinition models;

    GroovyDefinitionContext(GroovyResourceDefinition resources, GroovyModelDefinition models) {
        this.resources = resources
        this.models = models;
    }

    IBakedModel getIBakedModel() {
        return models.getIBakedModel();
    }

    GroovysonModel getGroovysonModel() {
        return models.getGroovysonModel();
    }

    ResourceLocation getResourceLocation() {
        return resources.getResourceLocation();
    }

    ModelResourceLocation getModelResourceLocation() {
        return resources.getModelResourceLocation();
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return resources.getTextureAtlasSprite();
    }

    void setIBakedModel(IBakedModel bakedModel) {
        models.setIBakedModel(bakedModel);
    }

    void setGroovysonModel(GroovysonModel groovyModel) {
        models.setGroovysonModel(groovyModel);
    }

    void setResourceLocation(ResourceLocation resourceLocation) {
        resources.setResourceLocation(resourceLocation);
    }

    void setResourceLocation(String resource) {
        resources.setResourceLocation(resource);
    }

    void setResourceLocation(String modID, String resource) {
        resources.setResourceLocation(modID, resource);
    }

    void setModelResourceLocation(ModelResourceLocation modelResourceLocation) {
        resources.setModelResourceLocation(modelResourceLocation);
    }

    void setModelResourceLocation(String modelLocation) {
        resources.setModelResourceLocation(modelLocation);
    }

    void setTextureAtlasSprite(ResourceLocation spriteLocation) {
        resources.setTextureAtlasSprite(spriteLocation);
    }

    void setTextureAtlasSprite(String sprite) {
        resources.setTextureAtlasSprite(sprite);
    }

    void setTextureAtlasSprite(String modID, String baseName) {
        resources.setTextureAtlasSprite(modID, baseName);
    }

    void setCustomResourceLocation(String type, String fileName) {
        resources.setCustomResourceLocation(type, fileName);
    }

    void setCustomResourceLocation(String modID, String type, String fileName) {
        resources.setCustomResourceLocation(modID, type, fileName);
    }

    void setCustomModelResourceLocation(String type, String fileName) {
        resources.setCustomModelResourceLocation(type, fileName);
    }
}
