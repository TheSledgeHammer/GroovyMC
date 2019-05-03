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

import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.ModelEntryBakery
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

class ModelEntryStatic extends ModelEntryBakery<ModelEntry, TextureEntry> {

    GroovyBaseModel GROOVY_MODEL;
    private MutableQuad[][] quads;

    ModelEntryStatic(GroovyBaseModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        //get Textures from GROOVY_MODEL
    }

    @Override
    protected void onModelBake() {
        if(GROOVY_MODEL == null) {
            quads = null;
        } else {
            //Does not work correctly for different Renders
            MutableQuad[] cut = bakePart(GROOVY_MODEL);
            MutableQuad[] trans = bakePart(GROOVY_MODEL);
            quads = [cut, trans];
            GROOVY_MODEL = null;
        }
    }

    @Override
    boolean hasBakedQuads() {
        return quads != null;
    }

    MutableQuad[] bakePart(GroovyBaseModel groovyBaseModel) {
        List<MutableQuad[]> mutableQuads = new ArrayList<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                mutableQuads.add(bakePartFace(groovyBaseModel, face))
            }
        }
        MutableQuad[] mutable = mutableQuads.toArray() as MutableQuad[];
        return mutable;
    }

    private MutableQuad[] bakePartFace(GroovyBaseModel groovyBaseModel, EnumFacing face) {
        TextureAtlasSprite missingSprite = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        List<MutableQuad> list = new ArrayList<>();
        for (GroovysonObjectPart part : groovyBaseModel.getModelElements()) {
            for (JsonQuads quad : groovyBaseModel.Quads(face)) {
                String lookup = quad.texture;
                int attempts = 0;
                while (lookup.startsWith("#") && part.TextureFace(face).contains(lookup) && attempts < 10) {
                    lookup = part.TextureFace(face);
                    attempts++;
                }
                if(lookup.startsWith("~") && part.TextureFace(face).contains(lookup)) {
                    //BC8 makes this an immutable map
                    //lookup = part.TextureFace(face);
                }
                TextureAtlasSprite sprite;
                /*if (lookup.startsWith("#") || lookup.startsWith("~")) {
                    if (allowTextureFallthrough) {
                        sprite = null;
                    } else {
                        sprite = missingSprite;
                    }
                } else {
                    sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
                }*/
                sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
                list.add(quad.toQuad(sprite));
            }
        }
        MutableQuad[] mutableQuads = new MutableQuad[list.size()];
        return list.toArray(mutableQuads);
    }
}
