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
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

//Temporary Placeholder for Working Model Tools
class ModelTools {
    /*TODO: Create Following:
- BlankItemModel: Uses ModelTools.addBakedQuadsToItem
- BlankBlockModel: Uses ModelTools.addBakedQuadsToBlock
 */
    static List<BakedQuad> addBakedQuadsToBlock(AbstractModel abstractModel, EnumFacing face, TextureAtlasSprite sprite) {
        int size = abstractModel.getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            bakedQuads.add(abstractModel.QuadAFace(face, i).toQuad(sprite).toBakedBlock());
        }
        return bakedQuads;
    }

    static List<BakedQuad> addBakedQuadsToItem(AbstractModel abstractModel, EnumFacing face, TextureAtlasSprite sprite) {
        int size = abstractModel.getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            bakedQuads.add(abstractModel.QuadAFace(face, i).toQuad(sprite).toBakedItem());
        }
        return bakedQuads;
    }

    static MutableQuad[] getMutableQuads(AbstractModel abstractModel, EnumFacing face, TextureAtlasSprite sprite) {
        int size = abstractModel.getRawModelTextures().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for(int i = 0; i < size; i++) {
            mutableQuads[i] = abstractModel.QuadAFace(face, i).toQuad(sprite);
        }
        return mutableQuads;
    }

    static JsonQuads[] readCuboid(GroovysonObject groovysonObject, int index) {
        GroovysonObjectPart objectPart = new GroovysonObjectPart(groovysonObject, index);
        float[] from = objectPart.from()
        float[] to = objectPart.to();
        boolean shade = groovysonObject.Shade(false);
        List<JsonQuads> quads = new ArrayList<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            JsonQuads q = new JsonQuads(objectPart, from, to, face);
            //q.shade = shade;
            quads.add(q);
        }
        if(quads.size() == 0) {
            //Add a Log Error or JsonSyntaxException
            //Log.logError("Expected between 1 and 6 faces, got an empty object");
            println "Expected between 1 and 6 faces, got an empty object"
        }
        return quads.toArray(new JsonQuads[quads.size()]);
    }
}
