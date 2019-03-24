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
package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.experimental.textures.GroovyTextureMap
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.vecmath.Vector3f

//Related to Variable Model Parts
//Testing & Work in Progress Model Implementations
class BakeTools {

    static double[] from;
    static double[] to;
    static boolean visible;
    static boolean shade;
    static long light;
    static long colour;

    //Works, Use scenario = Unsure
    static void addQuads(List<MutableQuad> addTo) {
        float[] f = bakePosition(from);
        float[] t = bakePosition(to);
        boolean s = shade;
        int l = (light & 15);
        int rgba = 0;
        for(EnumFacing face : EnumFacing.VALUES) {
            VariableFaceData data;
            if(data != null) {
                Vector3f radius = new Vector3f(t[0] - f[0] as float, t[1] - f[1] as float, t[2] - f[2] as float);
                radius.scale(0.5f);
                Vector3f center = new Vector3f(f);
                center.add(radius);
                MutableQuad quad = ModelUtil.createFace(face, center, radius, data.uvs);
                quad.rotateTextureUp(data.rotations);
                quad.lighti(l, 0);
                quad.colouri(rgba);
                quad.texFromSprite(data.sprite);
                quad.setSprite(data.sprite);
                quad.setShade(s);
                if (data.bothSides) {
                    addTo.add(quad.copyAndInvertNormal());
                } else if (data.invertNormal) {
                    quad = quad.copyAndInvertNormal();
                }
                addTo.add(quad);
            }
        }
    }

    //Used in Above addQuads() method
    static float[] bakePosition(double[] In) {
        float x = (float) In[0] / 16f;
        float y = (float) In[1] / 16f;
        float z = (float) In[2] / 16f;
        return [x, y, z];
    }

    //Non-Variable Methods
    static MutableQuad[] getCutoutQuads() {
//        MutableQuad[][] mq = new MutableQuad[MutableQuad.EMPTY_ARRAY][MutableQuad.EMPTY_ARRAY];
        return new MutableQuad[0];
    }

    static MutableQuad[] getTranslucentQuads() {
       // MutableQuad[][] mq = new MutableQuad[MutableQuad.EMPTY_ARRAY][MutableQuad.EMPTY_ARRAY];
        return new MutableQuad[1];
    }

    static abstract class VariableFaceData {
        public ModelUtil.UvFaceData uvs = new ModelUtil.UvFaceData();
        public TextureAtlasSprite sprite;
        public int rotations = 0;
        public boolean invertNormal = false;
        public boolean bothSides = false;

        abstract VariableFaceData getFaceData(EnumFacing side);
    }

    //TODO: Fix it so sprite (TextureAtlasSprite) returns matching facedata
    // Related to TexturedFaceLookup
    static MutableQuad[] bakePart(GroovysonModel groovysonModel, GroovyTextureMap textureMap) {
        TextureAtlasSprite missingSprite = textureMap.getMissingSprite();
        List<MutableQuad> list = new ArrayList<>();
        List<GroovysonObjectPart> a = groovysonModel.getRawModelParts();
        for(GroovysonObjectPart part : a) {
            //abstractModel.quads: references method setQuads and getQuads
            for(JsonQuads quads : groovysonModel.quads) {
                //addQuads(list);
                String lookup = quads.texture;
                TextureAtlasSprite sprite;

                if (lookup.startsWith("#") || lookup.startsWith("~")) {
                    sprite = missingSprite;
                } else {
                    sprite = textureMap.getAtlasSprite(lookup);
                    textureMap.setTextureEntry(sprite);
                }
                list.add(quads.toQuad(sprite));
            }
        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    //TexturedFace as Defined by a JsonTexture
    //Todo: TextureAtlasSprite sprite
    static ModelUtil.TexturedFace TexturedFaceLookup(GroovysonModel groovysonModel, EnumFacing facing, int index, GroovyTextureMap textureMap) {
        TextureAtlasSprite sprite;
        //TextureAtlas sprite = new TextureAtlas(abstractModel, facing, index);//Works just no info aside from name
        //String lookup = abstractModel.getJsonTexture(facing, index).location;
        String lookup = groovysonModel.getRawModelPart(index).TextureFace(facing);//Needs more work on using textureMap
        sprite = textureMap.getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        face.sprite = sprite;
        face.faceData = groovysonModel.getJsonTexture(facing, index).faceData;
        return face;
    }

    interface ITextureGetter {
        ModelUtil.TexturedFace get(String location);
    }
}
//JsonVariableModel(BC8) BakePart:
//New ArrayList of MutableQuads
//for each model part ; addQuads(mutableQuads ArrayList, sprites)
//Get cutout elements and translucent elements

//Look At:
//VariablePartCuboidBase
//VariablePartCuboid
