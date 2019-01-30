/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.utils.GroovyLoader
import com.thesledgehammer.groovymc.utils.Log
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import static com.thesledgehammer.groovymc.client.model.ModelUtil.TexturedFace

//AbstractModels does not utilise IBakedModel, it reads .jsons directly from a GroovysonObject and GroovysonObjectPart
class AbstractModel extends GroovysonObject {

    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();
    private String resourceObject;

    public final Map<String, TextureAtlasSprite> customSprites = new HashMap<>();

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

    //Copied from BC ModelHolderVariable, Doesn't work correctly
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
}
/*
    //TODO: JsonTextures Per FaceUV: can use RawModelTextures to match names to texture location
    void setRawModelPartTextures() {
        for(int i = 0; i < groovysonObjectParts.size(); i++) {
            for(int j = 0; j < EnumFacing.VALUES.size(); j++) {
                rawModelPartTextures.add(groovysonObjectParts.get(i).TextureFace(EnumFacing.VALUES[j]));
            }
        }
    }

    ArrayList<String> getRawModelPartTextures() {
        return rawModelPartTextures;
    }*/