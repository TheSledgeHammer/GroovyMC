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
import com.thesledgehammer.groovymc.utils.JsonTools
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

class ModelEntryStatic extends ModelEntryBakery<ModelEntry, TextureEntry> {

    private GroovyBaseModel groovyBaseModel;
    private MutableQuad[][] quads;

    ModelEntryStatic(GroovyBaseModel groovyBaseModel) {
        this.groovyBaseModel = groovyBaseModel;
        groovyBaseModel.setRenderKeysDefintion(groovyBaseModel.getGroovyModel());
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        //get Textures from GROOVY_MODEL
    }

    @Override
    protected void onModelBake() {
        if(groovyBaseModel == null) {
            quads = null;
        } else {
            MutableQuad[] cut = bakePart(groovyBaseModel.GroovyDefinitionContext().getCutoutKey().getCutoutModelElements());
            MutableQuad[] trans = bakePart(groovyBaseModel.GroovyDefinitionContext().getTranslucentKey().getTranslucentModelElements());
            MutableQuad[] solid = bakePart(groovyBaseModel.GroovyDefinitionContext().getSolidKey().getSolidModelElements());
            MutableQuad[] cut_mip = bakePart(groovyBaseModel.GroovyDefinitionContext().getCutoutMippedKey().getCutoutMippedModelElements());
            quads = [cut, trans, solid, cut_mip];
            groovyBaseModel = null;
        }
    }

    @Override
    boolean hasBakedQuads() {
        return quads != null;
    }

    private MutableQuad[] bakePart(ArrayList<GroovysonObjectPart > modelParts) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                return bakePartFace(modelParts, face);
            }
        }
        return null;
    }

    private MutableQuad[] bakePartFace(ArrayList<GroovysonObjectPart > modelParts, EnumFacing face) {
        TextureAtlasSprite missingSprite = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        List<MutableQuad> list = new ArrayList<>();
        for (GroovysonObjectPart part : modelParts) {
            for (JsonQuads quad : JsonTools.Quads(modelParts, face)) {
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
