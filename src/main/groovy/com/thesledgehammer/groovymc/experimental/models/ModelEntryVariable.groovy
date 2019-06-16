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

package com.thesledgehammer.groovymc.experimental.models

import com.google.common.collect.HashBasedTable
import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableCuboid
import com.thesledgehammer.groovymc.experimental.jsons.ITextureGetter
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

//Work In Progress
class ModelEntryVariable extends ModelEntryHolder  {

    private GroovyVariableModel groovyVariableModel;
    private HashBasedTable<EnumFacing, Integer, JsonTexture> JSON_TEXTABLE = HashBasedTable.create();
    private JsonRule[] rules; //Could be in GroovyVariableModel
    private boolean unseen = true;

    ModelEntryVariable(GroovyVariableModel groovyVariableModel) {
        this.groovyVariableModel = groovyVariableModel;
        groovyVariableModel.setRenderKeysDefintion(groovyVariableModel.getGroovysonModel());
    }

    @Override
    boolean hasBakedQuads() {
        return groovyVariableModel != null;
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {

    }

    @Override
    void onModelBake() {

    }

    private ModelUtil.TexturedFace TexturedFaceLookup(EnumFacing facing, int index, String lookup) {
        JsonTextureMapping();
        int attempts = 0;
        JsonTexture texture = new JsonTexture(lookup);
        TextureAtlasSprite sprite;
        while (texture.location.startsWith("#") && attempts < 10) {
            JsonTexture tex = JSON_TEXTABLE.get(facing, index);
            if(tex == null) {
                break;
            } else {
                texture = texture.inParent(tex);
            }
            attempts++;
        }

        lookup = texture.location;
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        face.sprite = sprite;
        face.faceData = texture.faceData;
        return face;
    }

    private void JsonTextureMapping() {
        ArrayList<GroovysonObjectPart> modelPartTexture = new ArrayList<>();
        ArrayList<String> textureName = groovyVariableModel.getModelTextures().keySet().toArray() as ArrayList<String>;
        String textureLocation = "";

        //Model Elements (Model Parts)
        for (int i = 0; i < groovyVariableModel.getModelElements().size(); i++) {
            modelPartTexture.add(groovyVariableModel.getModelElements().get(i));
        }

        //JsonTexTable: Defines JsonTextures by face and model part
        for (EnumFacing face : EnumFacing.VALUES) {
            for (int j = 0; j < modelPartTexture.size(); j++) {
                if (modelPartTexture.get(j).Facing(face) != null) {
                    for (int k = 0; k < textureName.size(); k++) {
                        textureLocation = groovyVariableModel.getModelTextures(textureName.get(k));
                    }
                    JsonTexture texture = new JsonTexture(modelPartTexture.get(j), textureLocation, face);
                    JSON_TEXTABLE.put(face, j, texture);
                }
            }
        }
    }

    //Incomplete
    private MutableQuad[] bakePart(ArrayList<GroovysonObjectPart> modelParts, ITextureGetter spriteLookup) {
        List<MutableQuad> list = new ArrayList<>();
        GroovysonVariableCuboid gVariableCuboid = new GroovysonVariableCuboid(modelParts);
        for (GroovysonObjectPart part : modelParts) {
            gVariableCuboid.addQuad(part, list, spriteLookup);
        }
        for (JsonRule rule : rules) {
            if(rule.getWhen().getValue()) {
                rule.apply(list);
            }
        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    MutableQuad[] getCutoutQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return bakePart(GroovyDefinitionContext.Instance().getCutoutKey().getCutoutModelElements(), this.&TexturedFaceLookup);
    }

    MutableQuad[] getTranslucentQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return bakePart(GroovyDefinitionContext.Instance().getTranslucentKey().getTranslucentModelElements(), this.&TexturedFaceLookup);
    }

    MutableQuad[] getSolidQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return bakePart(GroovyDefinitionContext.Instance().getSolidKey().getSolidModelElements(), this.&TexturedFaceLookup);
    }

    MutableQuad[] getCutoutMippedQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return bakePart(GroovyDefinitionContext.Instance().getCutoutMippedKey().getCutoutMippedModelElements(), this.&TexturedFaceLookup);
    }
}
