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

import com.google.common.collect.HashBasedTable
import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class GroovyBaseModel {

    GroovysonModel GROOVY_MODEL;
    private HashBasedTable<EnumFacing, Integer, JsonTexture> jsonTexTable = HashBasedTable.create();
    private GroovyDefinitionContext GDC;
    //MutableQuads

    GroovyBaseModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonModel(resourceObject, fileName);
    }

    GroovyBaseModel(GroovysonModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
    }

    //Add GroovyDefinitionContext to GroovyLoader? Initilized with GroovyLoader
    void setGroovyDefinitionContext(GroovyDefinitionContext GDC) {
        this.GDC = GDC;
    }

    void setGroovyDefinitionContext(GroovyResourceDefinition GRD, GroovyModelDefinition GMD) {
        this.GDC = new GroovyDefinitionContext(GRD, GMD);
    }

    void setModelElements(String name) {
        GROOVY_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
    }

    GroovyDefinitionContext GroovyDefinitionContext() {
        return GDC;
    }

    GroovysonObjectPart getModelElements(int index) {
        return GROOVY_MODEL.getRawModelPart(index);
    }

    String getModelTextures(String textureName) {
        return GROOVY_MODEL.getRawModelTextures().get(textureName);
    }

    JsonQuads[] Quads(EnumFacing faces) {
        JsonQuads[] jQuads = new JsonQuads[GROOVY_MODEL.getRawModelParts().size()];
        for(int i = 0; i < jQuads.size(); i++) {
            jQuads[i] = new JsonQuads(GROOVY_MODEL.getRawModelPart(i), GROOVY_MODEL.getRawModelPart(i).from(), GROOVY_MODEL.getRawModelPart(i).to(), faces);
        }
        return jQuads;
    }

    JsonQuads QuadAllFaces(int rawModelTexture) {
        for (EnumFacing face : EnumFacing.VALUES) {
            if(rawModelTexture > Quads(face).size()) {
                Log.logError("This ModelTexture does not contain " + face);
                Log.logError("Or this Model does not contain a ModelTexture at " + rawModelTexture);
                return null;
            }
            return Quads(face)[rawModelTexture];
        }
        return null;
    }

    JsonQuads QuadAFace(EnumFacing face, int rawModelTexture) {
        if(rawModelTexture > Quads(face).size()) {
            Log.logError("This ModelTexture does not contain " + face);
            Log.logError("Or this Model does not contain a ModelTexture at " + rawModelTexture);
            return null;
        }
        return Quads(face)[rawModelTexture];
    }

    HashBasedTable<EnumFacing, Integer, JsonTexture> getJsonTextureMappings() {
        return jsonTexTable;
    }

    JsonTexture getJsonTexture(EnumFacing face, int index) {
        return jsonTexTable.get(face, index);
    }

    void JsonTextureMapping() {
        ArrayList<GroovysonObjectPart> modelPartTexture = new ArrayList<>();
        ArrayList<String> textureName = GROOVY_MODEL.getRawModelTextures().keySet().toArray() as ArrayList<String>;
        String textureLocation = "";
        String texturePartName = "";

        //Model Elements (Model Parts)
        for(int i = 0; i < GROOVY_MODEL.getRawModelParts().size(); i++) {
            modelPartTexture.add(GROOVY_MODEL.getRawModelParts().get(i));
        }

        //JsonTexTable: Defines JsonTextures by face and model part
        for (EnumFacing face : EnumFacing.VALUES) {
            for(int j = 0; j < modelPartTexture.size(); j++) {
                if(modelPartTexture.get(j).Facing(face) != null) {
                    texturePartName = modelPartTexture.get(j).TextureFace(face);
                    for(int k = 0; k < textureName.size(); k++) {
                        //TODO: Variable Textures
                        //Implements Non-Variable Texture Location: Doesn't account for json rules or variables
                        //if(texturePartName.contains(textureName.get(k))) {
                        textureLocation = GROOVY_MODEL.getRawModelTextures().get(textureName.get(k));
                        //textureLocation = getRawModelTexturesLocation(textureName.get(k));
                        //Variables & Rules go here if don't match
                        // }
                    }
                    JsonTexture texture = new JsonTexture(modelPartTexture.get(j), textureLocation, face);
                    jsonTexTable.put(face, j, texture);
                    //Could add TexturedFaceLookup Here...
                    //TexturedFaceLookup(face, j);

                }
            }
        }
    }

    //Returns a Texture from x model element and face
    String getRawModelPartTexture(int index, EnumFacing face) {
        return GROOVY_MODEL.getRawModelParts().get(index).TextureFace(face);
    }

    //TexturedFace as Defined by a JsonTexture
    //Todo: TextureAtlasSprite sprite
    ModelUtil.TexturedFace TexturedFaceLookup(EnumFacing facing, int index) {
        //TextureAtlasSprite sprite;
        String name = GROOVY_MODEL.getRawModelPart(index).TextureFace(facing);
        String lookup = getJsonTexture(facing, index).location;
        //sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        //face.sprite = sprite;
        face.faceData = getJsonTexture(facing, index).faceData;
        return face;
    }

    MutableQuad[] getMutableQuads(EnumFacing face, TextureAtlasSprite sprite) {
        int size = GROOVY_MODEL.getRawModelTextures().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for(int i = 0; i < size; i++) {
            mutableQuads[i] = QuadAFace(face, i).toQuad(sprite);
        }
        return mutableQuads;
    }

    //ModelEntries & TextureEntries
    void onModelBake() {

    }

    void onTextureStitchPre() {

    }
}
//NOTES:
//GroovyDefinitionContext to be assigned so that a ModelEntryStatic, ModelBase and/ or GDC all reference to the same thing
//Solution #1: Model extends ModelEntryStatic
//Solution #2: A Cache/Table/Map of GDC: would allow to set Models & Textures and only slight change in registering (Would need to cross check models/ model.json exists)