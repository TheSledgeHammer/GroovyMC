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

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryBakery
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.utils.JsonTools
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

class ModelEntryStatic extends ModelEntryBakery<ModelEntry, TextureEntry> {

    private GroovyStaticModel groovyStaticModel;
    private MutableQuad[][] quads;
    private boolean unseen = true;

    ModelEntryStatic(GroovyStaticModel groovyStaticModel) {
        this.groovyStaticModel = groovyStaticModel;
        groovyStaticModel.setRenderKeysDefintion(groovyStaticModel.getGroovysonModel());
    }

    @Override
    boolean hasBakedQuads() {
        return quads != null;
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        //toRegisterSprites.add(groovyBaseModel.GroovyDefinitionContext().getResourceLocation())
    }

    @Override
    protected void onModelBake() {
        if(groovyStaticModel == null) {
            quads = null;
        } else {
            MutableQuad[] cut = bakePart(groovyStaticModel.GroovyDefinitionContext().getCutoutKey().getCutoutModelElements());
            MutableQuad[] trans = bakePart(groovyStaticModel.GroovyDefinitionContext().getTranslucentKey().getTranslucentModelElements());
            MutableQuad[] solid = bakePart(groovyStaticModel.GroovyDefinitionContext().getSolidKey().getSolidModelElements());
            MutableQuad[] cut_mip = bakePart(groovyStaticModel.GroovyDefinitionContext().getCutoutMippedKey().getCutoutMippedModelElements());
            quads = [cut, trans, solid, cut_mip];
            groovyStaticModel = null;
        }
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

    private MutableQuad[][] getQuadsChecking() {
        if(quads == null) {
            if(unseen) {
                unseen = false;
                Log.logWarn("Tried to use the model ${groovyStaticModel.getGroovysonModel().getName()} before it was baked")
            }
            return [MutableQuad.EMPTY_ARRAY, MutableQuad.EMPTY_ARRAY, MutableQuad.EMPTY_ARRAY, MutableQuad.EMPTY_ARRAY];
        }
        return quads;
    }

    MutableQuad[] getCutoutQuads() {
        return getQuadsChecking()[0];
    }

    MutableQuad[] getTranslucentQuads() {
        return getQuadsChecking()[1];
    }

    MutableQuad[] getSolidQuads() {
        return getQuadsChecking()[2];
    }

    MutableQuad[] getCutoutMippedQuads() {
        return getQuadsChecking()[3];
    }
}
