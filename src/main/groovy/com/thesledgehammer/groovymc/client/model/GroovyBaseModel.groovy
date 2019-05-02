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
import com.thesledgehammer.groovymc.client.render.keys.GroovyRenderKeysDefinition
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class GroovyBaseModel {

    GroovysonModel GROOVY_MODEL;
    private HashBasedTable<EnumFacing, Integer, JsonTexture> JSON_TEXTABLE = HashBasedTable.create();
    private GroovyDefinitionContext GDC;
    private GroovyRenderKeysDefinition GRKD;
    private JsonRule jsonRules;
    //MutableQuads

    GroovyBaseModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonModel(resourceObject, fileName);
        GRKD = new GroovyRenderKeysDefinition(this);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition());
        setRules();
    }

    GroovyBaseModel(GroovysonModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
        GRKD = new GroovyRenderKeysDefinition(this);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition());
        setRules();
    }

    GroovyDefinitionContext GroovyDefinitionContext() {
        return GDC;
    }

    GroovysonModel getGroovyModel() {
        return GROOVY_MODEL;
    }

    GroovysonObjectCache getObjectCache() {
        return GROOVY_MODEL.getObjectCache()
    }

    void setModelElements(String name) {
        GROOVY_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
        //TextureEntry.Register.add(name);
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

    JsonQuads[] Quads(EnumFacing faces) {
        JsonQuads[] jQuads = new JsonQuads[GROOVY_MODEL.getRawModelParts().size()];
        for(int i = 0; i < jQuads.size(); i++) {
            GROOVY_MODEL.setObjectCacheByModelPart(i);
            jQuads[i] = new JsonQuads(GROOVY_MODEL.getRawModelPart(i), getObjectCache().From(), getObjectCache().To(), faces);
        }
        return jQuads;
    }

    JsonQuads QuadAFace(EnumFacing face, int rawModelTexture) {
        if(rawModelTexture > Quads(face).size()) {
            Log.logError("This ModelTexture does not contain " + face);
            Log.logError("Or this Model does not contain a ModelTexture at " + rawModelTexture);
            return null;
        }
        return Quads(face)[rawModelTexture];
    }

    MutableQuad[] getMutableQuads(EnumFacing face, TextureAtlasSprite sprite) {
        int size = GROOVY_MODEL.getRawModelTextures().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for(int i = 0; i < size; i++) {
            mutableQuads[i] = QuadAFace(face, i).toQuad(sprite);
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