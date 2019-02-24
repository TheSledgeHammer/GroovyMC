/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.client.model.GroovysonModel
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
/*
    static final List<ModelEntry> MODEL_ENTRIES = new ArrayList<>();
    static final List<TextureEntry> TEXTURE_ENTRIES = new ArrayList<>();

    static void registerTextures(TextureEntry index) {
        TEXTURE_ENTRIES.add(index);
    }

    static void registerModels(ModelEntry index) {
       MODEL_ENTRIES.add(index);
    }*/
}
