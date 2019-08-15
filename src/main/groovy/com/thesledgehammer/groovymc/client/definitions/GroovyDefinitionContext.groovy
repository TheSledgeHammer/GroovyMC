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


import com.thesledgehammer.groovymc.api.client.definitions.IGroovysonModelDefinition
import com.thesledgehammer.groovymc.api.client.definitions.IRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.variables.VariableBoolean
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.variables.VariableLong
import com.thesledgehammer.groovymc.utils.variables.VariableObject
import net.minecraft.util.EnumFacing

class GroovyDefinitionContext implements IGroovysonModelDefinition, IRenderDefinition {

    private static GroovyDefinitionContext instance;
    //private GroovyResourceDefinition resources;
    //private GroovyModelDefinition models;
    private GroovyRenderDefinition renders;
    private GroovysonModelDefinition jsonModels;

    GroovyDefinitionContext(GroovyRenderDefinition renders, GroovysonModelDefinition jsonModels) {
        //this.resources = resources
        //this.models = models;
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

    //IGroovyModelDefinition
    /*
    IBakedModel getIBakedModel() {
        return models.getIBakedModel();
    }

    @Override
    IModel getIModel() {
        return models.getIModel();
    }

    @Override
    IModelState getIModelState() {
        return models.getIModelState();
    }

    @Override
    IModelPart getIModelPart() {
        return models.getIModelPart();
    }

    @Override
    List<IBakedModel> getIBakedModels() {
        return models.getIBakedModels();
    }

    @Override
    List<IModel> getIModels() {
        return models.getIModels();
    }

    @Override
    List<IModelState> getIModelStates() {
        return models.getIModelStates();
    }

    @Override
    List<IModelPart> getIModelParts() {
        return models.getIModelParts();
    }

    @Override
    void setIBakedModel(IBakedModel bakedModel) {
        models.setIBakedModel(bakedModel);
    }

    @Override
    void setIModel(IModel iModel) {
        models.setIModel(iModel);
    }

    @Override
    void setIModelState(IModelState iModelState) {
        models.setIModelState(iModelState);
    }

    @Override
    void setIModelPart(IModelPart iModelPart) {
        models.setIModelPart(iModelPart);
    }

    //IResourceDefinition
    @Override
    ResourceLocation getResourceLocation() {
        return resources.getResourceLocation();
    }

    @Override
    ModelResourceLocation getModelResourceLocation() {
        return resources.getModelResourceLocation();
    }

    @Override
    TextureAtlasSprite getTextureAtlasSprite() {
        return resources.getTextureAtlasSprite();
    }

    @Override
    List<ResourceLocation> getResourceLocations() {
        return resources.getResourceLocations();
    }

    @Override
    List<ModelResourceLocation> getModelResourceLocations() {
        return resources.getModelResourceLocations();
    }

    @Override
    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return resources.getTextureAtlasSprites();
    }

    @Override
    void setResourceLocation(ResourceLocation resourceLocation) {
        resources.setResourceLocation(resourceLocation);
    }

    @Override
    void setResourceLocation(String resource) {
        resources.setResourceLocation(resource);
    }

    @Override
    void setResourceLocation(String modID, String resource) {
        resources.setResourceLocation(modID, resource);
    }

    @Override
    void setModelResourceLocation(ModelResourceLocation modelResourceLocation) {
        resources.setModelResourceLocation(modelResourceLocation);
    }

    @Override
    void setModelResourceLocation(String modelLocation) {
        resources.setModelResourceLocation(modelLocation);
    }

    @Override
    void setTextureAtlasSprite(TextureAtlasSprite textureAtlasSprite) {
        resources.setTextureAtlasSprite(textureAtlasSprite);
    }

    @Override
    void setTextureAtlasSprite(ResourceLocation spriteLocation) {
        resources.setTextureAtlasSprite(spriteLocation);
    }

    @Override
    void setTextureAtlasSprite(String sprite) {
        resources.setTextureAtlasSprite(sprite);
    }

    @Override
    void setTextureAtlasSprite(String modID, String baseName) {
        resources.setTextureAtlasSprite(modID, baseName);
    }

    @Override
    void onTextureStitchPre(TextureMap map) {
        resources.onTextureStitchPre(map);
    }

    @Override
    void setCustomResourceLocation(String type, String fileName) {
        resources.setCustomResourceLocation(type, fileName);
    }

    @Override
    void setCustomResourceLocation(String modID, String type, String fileName) {
        resources.setCustomResourceLocation(modID, type, fileName);
    }

    @Override
    void setCustomModelResourceLocation(String type, String fileName) {
        resources.setCustomModelResourceLocation(type, fileName);
    }
    */
    //IRenderDefinition
    @Override
    CutoutKey getCutoutKey() {
        return renders.getCutoutKey();
    }

    @Override
    CutoutMippedKey getCutoutMippedKey() {
        return renders.getCutoutMippedKey();
    }

    @Override
    SolidKey getSolidKey() {
        return renders.getSolidKey();
    }

    @Override
    TranslucentKey getTranslucentKey() {
        return renders.getTranslucentKey();
    }

    @Override
    void setCutoutKey(CutoutKey cutoutKey) {
        renders.setCutoutKey(cutoutKey);
    }

    @Override
    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        renders.setCutoutMippedKey(cutoutMippedKey)
    }

    @Override
    void setSolidKey(SolidKey solidKey) {
        renders.setSolidKey(solidKey);
    }

    @Override
    void setTranslucentKey(TranslucentKey translucentKey) {
        renders.setTranslucentKey(translucentKey);
    }

    //IGroovysonModelDefinition
    @Override
    void setStaticFrom(GroovysonObjectPart part) {
        jsonModels.setStaticFrom(part)
    }

    @Override
    void setStaticTo(GroovysonObjectPart part) {
        jsonModels.setStaticTo(part)
    }

    @Override
    void setStaticUV(GroovysonObjectPart part) {
        jsonModels.setStaticUV(part)
    }

    @Override
    void setStaticShade(GroovysonObjectPart part) {
        jsonModels.setStaticShade(part)
    }

    @Override
    void setStaticVisible(GroovysonObjectPart part) {
        jsonModels.setStaticVisible(part)
    }

    @Override
    void setStaticColour(GroovysonObjectPart part) {
        jsonModels.setStaticColour(part)
    }

    @Override
    void setStaticLight(GroovysonObjectPart part) {
        jsonModels.setStaticLight(part)
    }

    @Override
    void setStaticTexture(GroovysonObjectPart part) {
        jsonModels.setStaticTexture(part)
    }

    @Override
    void setStaticRotationAngle(GroovysonObjectPart part) {
        jsonModels.setStaticRotationAngle(part)
    }

    @Override
    void setStaticRotationAxis(GroovysonObjectPart part) {
        jsonModels.setStaticRotationAxis(part)
    }

    @Override
    void setStaticRotationOrigin(GroovysonObjectPart part) {
        jsonModels.setStaticRotationOrigin(part)
    }

    @Override
    List<Double> getStaticFrom(GroovysonObjectPart part) {
        return jsonModels.getStaticFrom(part)
    }

    @Override
    List<Double> getStaticTo(GroovysonObjectPart part) {
        return jsonModels.getStaticTo(part)
    }

    @Override
    List<Double> getStaticFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getStaticFaceUV(part, face)
    }

    @Override
    Boolean getStaticShade(GroovysonObjectPart part) {
        return jsonModels.getStaticShade(part)
    }

    @Override
    Boolean getStaticVisible(GroovysonObjectPart part) {
        return jsonModels.getStaticVisible(part)
    }

    @Override
    Long getStaticColour(GroovysonObjectPart part) {
        return jsonModels.getStaticColour(part)
    }

    @Override
    Long getStaticLight(GroovysonObjectPart part) {
        return jsonModels.getStaticLight(part)
    }

    @Override
    Boolean getStaticInvert(GroovysonObjectPart part) {
        return jsonModels.getStaticInvert(part)
    }

    @Override
    Boolean getStaticBothSides(GroovysonObjectPart part) {
        return jsonModels.getStaticBothSides(part)
    }

    @Override
    String getStaticTexture(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getStaticTexture(part, face)
    }

    @Override
    List<Double> getStaticRotationAngle(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationAngle(part)
    }

    @Override
    List<Double> getStaticRotationAxis(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationAxis(part)
    }

    @Override
    List<Double> getStaticRotationOrigin(GroovysonObjectPart part) {
        return jsonModels.getStaticRotationOrigin(part)
    }

    //Variable Models
    @Override
    void setVariableFrom(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableFrom(part, newValue)
    }

    @Override
    void setVariableTo(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableTo(part, newValue)
    }

    @Override
    void setVariableUV(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableUV(part, newValue)
    }

    @Override
    void setVariableShade(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableShade(part, newValue)
    }

    @Override
    void setVariableVisible(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableVisible(part, newValue)
    }

    @Override
    void setVariableColour(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableColour(part, newValue)
    }

    @Override
    void setVariableLight(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableLight(part, newValue)
    }

    @Override
    void setVariableInvert(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableInvert(part, newValue)
    }

    @Override
    void setVariableBothSides(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableBothSides(part, newValue)
    }

    @Override
    void setVariableTexture(GroovysonObjectPart part) {
        jsonModels.setVariableTexture(part)
    }

    @Override
    void setVariableTextureRotation(GroovysonObjectPart part, String newValue) {
        jsonModels.setVariableTextureRotation(part, newValue)
    }

    @Override
    List<VariableDouble> getVariableFrom(GroovysonObjectPart part) {
        return jsonModels.getVariableFrom(part)
    }

    @Override
    List<VariableDouble> getVariableTo(GroovysonObjectPart part) {
        return jsonModels.getVariableTo(part)
    }

    @Override
    List<VariableDouble> getVariableFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableFaceUV(part, face)
    }

    @Override
    VariableBoolean getVariableShade(GroovysonObjectPart part) {
        return jsonModels.getVariableShade(part)
    }

    @Override
    VariableBoolean getVariableVisible(GroovysonObjectPart part) {
        return jsonModels.getVariableVisible(part)
    }

    @Override
    VariableLong getVariableColour(GroovysonObjectPart part) {
        return jsonModels.getVariableColour(part)
    }

    @Override
    VariableLong getVariableLight(GroovysonObjectPart part) {
        return jsonModels.getVariableLight(part)
    }

    @Override
    VariableBoolean getVariableInvert(GroovysonObjectPart part) {
        return jsonModels.getVariableInvert(part)
    }

    @Override
    VariableBoolean getVariableBothSides(GroovysonObjectPart part) {
        return jsonModels.getVariableBothSides(part)
    }

    @Override
    VariableObject<String> getVariableTexture(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableTexture(part, face)
    }

    @Override
    VariableLong getVariableTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return jsonModels.getVariableTextureRotation(part, face)
    }
}
