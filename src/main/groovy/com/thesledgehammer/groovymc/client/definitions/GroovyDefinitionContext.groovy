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
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.variables.VariableBoolean
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.variables.VariableLong
import com.thesledgehammer.groovymc.utils.variables.VariableObject
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState

class GroovyDefinitionContext {

    private static GroovyDefinitionContext instance;
    private GroovyResourceDefinition resources;
    private GroovyModelDefinition models;
    private GroovyRenderDefinition renders;
    private GroovysonModelDefinition jsonModels;

    GroovyDefinitionContext(GroovyResourceDefinition resources, GroovyModelDefinition models, GroovyRenderDefinition renders, GroovysonModelDefinition jsonModels) {
        this.resources = resources
        this.models = models;
        this.renders = renders;
        this.jsonModels = jsonModels;
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

    /**GroovysonModelDefinition**/

    void setStaticFrom(GroovysonObjectPart part) {
        jsonModels.setStaticFrom(part)
    }

    void setStaticTo(GroovysonObjectPart part) {
        jsonModels.setStaticTo(part)
    }
    void setStaticUV(GroovysonObjectPart part) {
        jsonModels.setStaticUV(part)
    }

    void setStaticShade(GroovysonObjectPart part) {
        jsonModels.setStaticShade(part)
    }

    void setStaticVisible(GroovysonObjectPart part) {
        jsonModels.setStaticVisible(part)
    }

    void setStaticColour(GroovysonObjectPart part) {
        jsonModels.setStaticColour(part)
    }

    void setStaticLight(GroovysonObjectPart part) {
        jsonModels.setStaticLight(part)
    }

    void setStaticTexture(GroovysonObjectPart part) {
        jsonModels.setStaticTexture(part)
    }

    void setStaticRotationAngle(GroovysonObjectPart part) {
        jsonModels.setStaticRotationAngle(part)
    }

    void setStaticRotationAxis(GroovysonObjectPart part) {
        jsonModels.setStaticRotationAxis(part)
    }

    void setStaticRotationOrigin(GroovysonObjectPart part) {
        jsonModels.setStaticRotationOrigin(part)
    }

    List<Double> getStaticFrom(GroovysonObjectPart part) {
        return jsonModels.getStaticFrom(part)
    }

    List<Double> getStaticTo(GroovysonObjectPart part) {
        return jsonModels.getStaticTo(part)
    }

    List<Double> getStaticFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getStaticFaceUV(part, face)
    }

    Boolean getStaticShade(GroovysonObjectPart part) {
        return jsonModels.getStaticShade(part)
    }

    Boolean getStaticVisible(GroovysonObjectPart part) {
        return jsonModels.getStaticVisible(part)
    }

    Long getStaticColour(GroovysonObjectPart part) {
        return jsonModels.getStaticColour(part)
    }

    Long getStaticLight(GroovysonObjectPart part) {
        return jsonModels.getStaticLight(part)
    }

    Boolean getStaticInvert(GroovysonObjectPart part) {
        return jsonModels.getStaticInvert(part)
    }

    Boolean getStaticBothSides(GroovysonObjectPart part) {
        return jsonModels.getStaticBothSides(part)
    }

    String getStaticTexture(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getStaticTexture(part, face)
    }

    List<Double> getStaticRotationAngle(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationAngle(part)
    }

    List<Double> getStaticRotationAxis(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationAxis(part)
    }

    List<Double> getStaticRotationOrigin(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationOrigin(part)
    }

    //Variable Models

    void setVariableFrom(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableFrom(part, newValue)
    }

    void setVariableTo(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableTo(part, newValue)
    }

    void setVariableUV(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableUV(part, newValue)
    }

    void setVariableShade(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableShade(part, newValue)
    }

    void setVariableVisible(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableVisible(part, newValue)
    }

    void setVariableColour(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableColour(part, newValue)
    }

    void setVariableLight(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableLight(part, newValue)
    }

    void setVariableInvert(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableInvert(part, newValue)
    }

    void setVariableBothSides(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableBothSides(part, newValue)
    }

    void setVariableTexture(GroovysonObjectPart part) {
        jsonModels.setVariableTexture(part)
    }

    void setVariableTextureRotation(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableTextureRotation(part, newValue)
    }

    List<VariableDouble> getVariableFrom(GroovysonObjectPart part) {
        return jsonModels.getVariableFrom(part)
    }

    List<VariableDouble> getVariableTo(GroovysonObjectPart part) {
        return jsonModels.getVariableTo(part)
    }

    List<VariableDouble> getVariableFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableFaceUV(part, face)
    }

    VariableBoolean getVariableShade(GroovysonObjectPart part) {
        return jsonModels.getVariableShade(part)
    }

    VariableBoolean getVariableVisible(GroovysonObjectPart part) {
        return jsonModels.getVariableVisible(part)
    }

    VariableLong getVariableColour(GroovysonObjectPart part) {
        return jsonModels.getVariableColour(part)
    }

    VariableLong getVariableLight(GroovysonObjectPart part) {
        return jsonModels.getVariableLight(part)
    }

    VariableBoolean getVariableInvert(GroovysonObjectPart part) {
        return jsonModels.getVariableInvert(part)
    }

    VariableBoolean getVariableBothSides(GroovysonObjectPart part) {
        return jsonModels.getVariableBothSides(part)
    }

    VariableObject<String> getVariableTexture(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableTexture(part, face)
    }

    VariableLong getVariableTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableTextureRotation(part, face)
    }
}
