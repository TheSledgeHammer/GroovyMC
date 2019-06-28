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

import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState

class GroovyDefinitionContext {

    private static GroovyDefinitionContext instance;
    private GroovyResourceDefinition resources;
    private GroovyModelDefinition models;
    private GroovyRenderDefinition renders;

    GroovyDefinitionContext(GroovyResourceDefinition resources, GroovyModelDefinition models, GroovyRenderDefinition renders) {
        this.resources = resources
        this.models = models;
        this.renders = renders;
        instance = this;
    }

    static GroovyDefinitionContext Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    /**GroovyModelDefinition**/
    IBakedModel getIBakedModel() {
        return models.getIBakedModel();
    }

    IModel getIModel() {
        return models.getIModel();
    }

    IModelState getIModelState() {
        return models.getIModelState();
    }

    IModelPart getIModelPart() {
        return models.getIModelPart();
    }

    List<IBakedModel> getIBakedModels() {
        return models.getIBakedModels();
    }

    List<IModel> getIModels() {
        return models.getIModels();
    }

    List<IModelState> getIModelStates() {
        return models.getIModelStates();
    }

    List<IModelPart> getIModelParts() {
        return models.getIModelParts();
    }

    void setIBakedModel(IBakedModel bakedModel) {
        models.setIBakedModel(bakedModel);
    }

    void setIModel(IModel iModel) {
        models.setIModel(iModel);
    }

    void setIModelState(IModelState iModelState) {
        models.setIModelState(iModelState);
    }

    void setIModelPart(IModelPart iModelPart) {
        models.setIModelPart(iModelPart);
    }

    /**GroovyResourceDefinition**/
    ResourceLocation getResourceLocation() {
        return resources.getResourceLocation();
    }

    ModelResourceLocation getModelResourceLocation() {
        return resources.getModelResourceLocation();
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return resources.getTextureAtlasSprite();
    }

    List<ResourceLocation> getResourceLocations() {
        return resources.getResourceLocations();
    }

    List<ModelResourceLocation> getModelResourceLocations() {
        return resources.getModelResourceLocations();
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return resources.getTextureAtlasSprites();
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

    void onTextureStitchPre(TextureMap map) {
        resources.onTextureStitchPre(map);
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

    /**GroovyRenderDefinition**/

    CutoutKey getCutoutKey() {
        return renders.getCutoutKey();
    }

    CutoutMippedKey getCutoutMippedKey() {
        return renders.getCutoutMippedKey();
    }

    SolidKey getSolidKey() {
        return renders.getSolidKey();
    }

    TranslucentKey getTranslucentKey() {
        return renders.getTranslucentKey();
    }

    void setCutoutKey(CutoutKey cutoutKey) {
        renders.setCutoutKey(cutoutKey);
    }

    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        renders.setCutoutMippedKey(cutoutMippedKey)
    }

    void setSolidKey(SolidKey solidKey) {
        renders.setSolidKey(solidKey);
    }

    void setTranslucentKey(TranslucentKey translucentKey) {
        renders.setTranslucentKey(translucentKey);
    }
}
