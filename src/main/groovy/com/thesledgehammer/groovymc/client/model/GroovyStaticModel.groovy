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
import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectCache
import com.thesledgehammer.groovymc.client.render.keys.CutoutKey
import com.thesledgehammer.groovymc.client.render.keys.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.render.keys.SolidKey
import com.thesledgehammer.groovymc.client.render.keys.TranslucentKey
import com.thesledgehammer.groovymc.utils.JsonTools
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class GroovyStaticModel {

    private GroovysonModel GROOVY_MODEL;
    private GroovyDefinitionContext GDC;
    private HashBasedTable<EnumFacing, Integer, JsonTexture> JSON_TEXTABLE = HashBasedTable.create();
    private JsonRule jsonRules;
    //MutableQuads

    GroovyStaticModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonModel(resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
        setRules();
    }

    GroovyStaticModel(GroovysonModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
        setRules();
    }

    GroovyDefinitionContext GroovyDefinitionContext() {
        return GDC;
    }

    GroovysonModel getGroovysonModel() {
        return GROOVY_MODEL;
    }

    void setRenderKeysDefintion(GroovysonModel GROOVY_MODEL) {
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
    }

    GroovysonObjectCache getObjectCache() {
        return GROOVY_MODEL.getObjectCache()
    }

    void setModelElements(String name) {
        GROOVY_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
        JsonTextureMapping();
    }

    GroovysonObjectPart getModelElements(int index) {
        return GROOVY_MODEL.getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return GROOVY_MODEL.getRawModelParts();
    }


    String getModelTextures(String textureName) {
        return GROOVY_MODEL.getRawModelTextures().get(textureName);
    }

    //Returns a Texture from x model element and face
    String getModelElementTextures(int index, EnumFacing face) {
        return GROOVY_MODEL.getRawModelParts().get(index).TextureFace(face);
    }

    private void setRules() {
        this.jsonRules = new JsonRule(GROOVY_MODEL);
    }

    JsonRule getRules() {
        return jsonRules;
    }

    //Is Very Likely to be Refactored
    MutableQuad[] getMutableQuads(EnumFacing face, TextureAtlasSprite sprite) {
        int size = getModelElements().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for(int i = 0; i < size; i++) {
            mutableQuads[i] = JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite);
        }
        return mutableQuads;
    }

    HashBasedTable<EnumFacing, Integer, JsonTexture> getJsonTextureMappings() {
        return JSON_TEXTABLE;
    }

    JsonTexture getJsonTexture(EnumFacing face, int index) {
        return JSON_TEXTABLE.get(face, index);
    }

    private void JsonTextureMapping() {
        ArrayList<GroovysonObjectPart> modelPartTexture = new ArrayList<>();
        ArrayList<String> textureName = GROOVY_MODEL.getRawModelTextures().keySet().toArray() as ArrayList<String>;
        String textureLocation = "";

        //Model Elements (Model Parts)
        for(int i = 0; i < GROOVY_MODEL.getRawModelParts().size(); i++) {
            modelPartTexture.add(GROOVY_MODEL.getRawModelParts().get(i));
        }

        //JsonTexTable: Defines JsonTextures by face and model part
        for (EnumFacing face : EnumFacing.VALUES) {
            for(int j = 0; j < modelPartTexture.size(); j++) {
                if(modelPartTexture.get(j).Facing(face) != null) {
                    for(int k = 0; k < textureName.size(); k++) {
                        textureLocation = GROOVY_MODEL.getRawModelTextures().get(textureName.get(k));
                    }
                    JsonTexture texture = new JsonTexture(modelPartTexture.get(j), textureLocation, face);
                    JSON_TEXTABLE.put(face, j, texture);
                }
            }
        }
    }

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

    //Irrelevant? Currently GroovyMC does not define a model part by cuboid in the model json
    private static JsonQuads[] readCuboid(GroovysonObject groovysonObject, int index) {
        GroovysonObjectPart objectPart = new GroovysonObjectPart(groovysonObject, index);
        float[] from = objectPart.From()
        float[] to = objectPart.To();
        boolean shade = groovysonObject.Shade(false);
        List<JsonQuads> quads = new ArrayList<>();

        for(EnumFacing face : EnumFacing.VALUES) {
            JsonQuads q = new JsonQuads(objectPart, from, to, face);
            //q.shade = shade;
            quads.add(q);
        }
        if(quads.size() == 0) {
            Log.logError("Expected between 1 and 6 faces, got an empty object");
            //println "Expected between 1 and 6 faces, got an empty object"
        }
        return quads.toArray(new JsonQuads[quads.size()]);
    }
}

//TODO: JsonQuads & MutableQuads for Render Keys