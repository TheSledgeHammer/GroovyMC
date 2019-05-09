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

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.utils.JsonTools
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class GroovyBlockModel extends GroovyStaticModel {

    GroovyBlockModel(String fileName) {
        super("block", fileName)
    }

    GroovyBlockModel(String resourceDirectory, String modID, String fileName) {
        super(resourceDirectory, modID, "block", fileName)
    }

    GroovyBlockModel(GroovysonModel GROOVY_MODEL) {
        super(GROOVY_MODEL)
    }

    List<BakedQuad> addBakedQuadsToBlock(EnumFacing face, TextureAtlasSprite sprite) {
        int size = getGroovysonModel().getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            bakedQuads.add(JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite).toBakedBlock());
        }
        return bakedQuads;
    }
}
