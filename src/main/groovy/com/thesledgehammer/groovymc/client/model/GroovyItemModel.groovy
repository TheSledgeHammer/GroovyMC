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


import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class GroovyItemModel extends GroovyBaseModel {

    GroovyItemModel(String fileName) {
        super("item", fileName);
    }

    List<BakedQuad> addBakedQuadsToItem(EnumFacing face, TextureAtlasSprite sprite) {
        int size = GROOVY_MODEL.getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            bakedQuads.add(QuadAFace(face, i).toQuad(sprite).toBakedItem());
        }
        return bakedQuads;
    }
}
