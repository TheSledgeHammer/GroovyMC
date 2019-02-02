/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model

import com.google.common.collect.HashBasedTable
import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.utils.GroovyLoader
import com.thesledgehammer.groovymc.utils.Log
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.EnumFacing

//AbstractModels does not utilise IBakedModel, it reads .jsons directly from a GroovysonObject and GroovysonObjectPart
class AbstractModel extends GroovysonObject {

    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();
    //private List<String> rawModelPartTexturesList = new ArrayList<>();
    private String resourceObject;

    /**
     * @param resourceObject: Item or Block
     * @param fileName: Name of Model json file
     */
    AbstractModel(String resourceObject, String fileName) {
        setRawModel(resourceObject, fileName);
    }

    private void setRawModel(String resourceObject, String fileName) {
        setResourceObject(resourceObject);
        setJsonObject(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
    }

    private void setResourceObject(String resourceObject) {
        this.resourceObject = resourceObject;
    }

    void setRawModelParts(String partName) {
        groovysonObjectParts.add(new GroovysonObjectPart(this, partName));
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }

    //Texture Name & Location
    void setRawModelTextures(String textureName) {
        String texLocation = this.getTexturesByName(textureName);
        for(int i = 0; i < StringTools.StringToList(texLocation).size(); i++) {
            rawModelTexturesMap.put(textureName, StringTools.StringToList(texLocation).get(i));
        }
        StringTools.StringToList(texLocation).clear();
    }

    HashMap<String, String> getRawModelTextures() {
        return rawModelTexturesMap;
    }

    String getRawModelTexturesLocation(String textureName) {
        return rawModelTexturesMap.get(textureName);
    }

    //Returns a Texture from x model element and face
    String getRawModelPartTexture(int index, EnumFacing face) {
        return getRawModelParts().get(index).TextureFace(face);
    }

    //JSON QUADS
    JsonQuads[] Quads(EnumFacing faces) {
        JsonQuads[] jQuads = new JsonQuads[groovysonObjectParts.size()];
        for(int i = 0; i < jQuads.size(); i++) {
            jQuads[i] = new JsonQuads(groovysonObjectParts.get(i), groovysonObjectParts.get(i).from(), groovysonObjectParts.get(i).to(), faces);
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

    //Based on BC8's JsonModel readCuboid
    JsonQuads[] readCuboid(GroovysonObject groovysonObject, int index) {
        GroovysonObjectPart objectPart = new GroovysonObjectPart(groovysonObject, index);
        ArrayList<Float> from = objectPart.From();
        ArrayList<Float> to = objectPart.To();
        boolean shade = groovysonObject.Shade(false);
        List<JsonQuads> quads = new ArrayList<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            JsonQuads q = new JsonQuads(objectPart, from, to, face);
            //q.shade = shade;
            quads.add(q);
        }
        if(quads.size() == 0) {
            //Add a Log Error or JsonSyntaxException
            println "Expected between 1 and 6 faces, got an empty object"
        }
        return quads.toArray(new JsonQuads[quads.size()]);
    }

    //JSON TEXTURES
    //TODO: JsonTexture:
    // Current Method is Based on BC8's JsonVariableModel lookupTexture()
    //Need to Map Texture in jTexTable to Texture's Location
    //To Apply that to a ModelUtil.TexturedFace
    //Solution #1: Check texture name matches JsonTexture name.
    private HashBasedTable<EnumFacing, Integer, JsonTexture> jTexTable = HashBasedTable.create();
    void JsonTextureMapping() {
        for (EnumFacing face : EnumFacing.VALUES) {
            for(int i = 0; i < getRawModelParts().size(); i++) {
                JsonTexture texture = new JsonTexture(getRawModelPart(i), face);
                jTexTable.put(face, i, texture);
            }
        }
    }

    HashBasedTable<EnumFacing, Integer, JsonTexture> getJsonTextureMappings() {
        return jTexTable;
    }

    JsonTexture getJsonTexture(EnumFacing face, int index) {
        return jTexTable.get(face, index);
    }
}

/*
 //Most Likely Un-needed
    //Returns all element textures per face from a list
    List<String> getRawModelPartTextures() {
        return rawModelPartTexturesList;
    }


    //Adds all element textures per face to a List
    private void ModelPartTextures() {
        ArrayList<GroovysonObjectPart> modelPartTexture = new ArrayList<>();
        if(getRawModelParts().size() == 0 || getRawModelParts().isEmpty() || getRawModelParts() == null) {
            println("No Raw Model Elements have been set");
            //Log.logWarn("No Raw Model Elements have been set");
        }
        for(int i = 0; i < getRawModelParts().size(); i++) {
            modelPartTexture.add(getRawModelParts().get(i));
        }
        for(int j = 0; j < modelPartTexture.size(); j++) {
            for (EnumFacing faces : EnumFacing.VALUES) {
                if (modelPartTexture.get(j).Facing(faces) != null) {
                    rawModelPartTexturesList.add(modelPartTexture.get(j).TextureFace(faces));
                }
            }
        }
    }
    */

/*
    //Copied from BC ModelHolderVariable, Doesn't work correctly
    //Own implementation currently in the works
    //Use at own Risk!!
    TexturedFace lookupTexture(String textureName) {
        int attempts = 0;
        JsonTexture texture = new JsonTexture(textureName);
        TextureAtlasSprite sprite;
        while (texture.location.startsWith("#") && attempts < 10) {
            JsonTexture tex = rawModelTexturesMap.get(texture);
            if(tex == null) {
                break;
            } else {
                texture = texture.inParent(tex);
            }
            attempts++;
        }

        textureName = texture.location;
        if (textureName.startsWith("~")) {
            sprite = customSprites.get(textureName.substring(1));
            if (sprite == null) {
                sprite = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
        } else {
            sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(textureName);
        }

        TexturedFace face = new TexturedFace();
        face.sprite = sprite;
        face.faceData = texture.faceData;
        return face;
    }
 */