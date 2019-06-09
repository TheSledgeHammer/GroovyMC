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
package com.thesledgehammer.groovymc.experimental.misc

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class BakeTools {

    //Irrelevant? Currently GroovyMC does not define a model part by cuboid in the model json
    private static JsonQuads[] readCuboid(GroovysonObject groovysonObject, int index) {
        GroovysonObjectPart objectPart = new GroovysonObjectPart(groovysonObject, index);
        float[] from = objectPart.From()
        float[] to = objectPart.To();
        boolean shade = groovysonObject.Shade(false);
        List<JsonQuads> quads = new ArrayList<>();

        for (EnumFacing face : EnumFacing.VALUES) {
            JsonQuads q = new JsonQuads(objectPart, from, to, face);
            //q.shade = shade;
            quads.add(q);
        }
        if (quads.size() == 0) {
            Log.logError("Expected between 1 and 6 faces, got an empty object");
        }
        return quads.toArray(new JsonQuads[quads.size()]);
    }

    ModelUtil.TexturedFace TexturedFaceLookup(GroovyVariableModel model, EnumFacing facing, int index) {
        TextureAtlasSprite sprite;
        String name = model.getGroovysonModel().getRawModelPart(index).TextureFace(facing);
        String lookup = model.getJsonTexture(facing, index).location;
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        face.sprite = sprite;
        face.faceData = model.getJsonTexture(facing, index).faceData;
        return face;
    }
}