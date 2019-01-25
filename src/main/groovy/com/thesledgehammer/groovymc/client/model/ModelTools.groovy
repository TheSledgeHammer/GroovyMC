/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model

import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

//Temporary Placeholder
class ModelTools {
    /*TODO: Create Following:
- BlankItemModel: Uses ModelTools.addBakedQuadsToItem
- BlankBlockModel: Uses ModelTools.addBakedQuadsToBlock
-
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
}
