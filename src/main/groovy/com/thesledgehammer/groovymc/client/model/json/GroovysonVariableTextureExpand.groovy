/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2019: Several changes made for use in GroovyMC. Renamed from VariablePartTextureExpand. Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.google.common.collect.ImmutableList
import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.utils.JsonTools
import com.thesledgehammer.groovymc.utils.MathTools
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.ModelBakery
import net.minecraft.client.renderer.model.ModelRotation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ItemLayerModel

import java.util.function.Function

/* TODO: Fix addQuads evaluateFace, IBakedModel & quads */
class GroovysonVariableTextureExpand {

    protected GroovyDefinitionContext GDC = GroovyDefinitionContext.Instance();

    GroovysonVariableTextureExpand(List<GroovysonObjectPart> objectParts) {
        for(GroovysonObjectPart parts : objectParts) {
            if(parts != null) {
                GDC.setVariableFrom(parts, "0");
                GDC.setVariableTo(parts, "0");
                GDC.setVariableShade(parts, "true");
                GDC.setVariableVisible(parts, "true");
                GDC.setVariableLight(parts, "0");
                GDC.setVariableColour(parts, "-1");
                GDC.setVariableUV(parts, "face");
            }
        }
    }

    void addQuads(GroovysonObjectPart parts, List<MutableQuad> addTo, ITextureGetter spriteLookup) {
        if (GDC.getVariableVisible(parts).getValue()) {
            float[] from = JsonTools.bakePosition(GDC.getVariableFrom(parts));
            float[] to = JsonTools.bakePosition(GDC.getVariableTo(parts));
            float[] size = [ to[0] - from[0], to[1] - from[1], to[2], from[2] ];
            boolean shade = GDC.getVariableShade(parts).getValue();
            int l = (int) (GDC.getVariableLight(parts).getValue() & 15);
            int rgba = MathTools.swapARGBforABGR((int) GDC.getVariableColour(parts).getValue());

            for (Direction face : Direction.values()) {
                VariableFaceData data = GDC.getVariableFaceUV(parts, face) as VariableFaceData;
                if (data != null) {
                    ItemLayerModel model = new ItemLayerModel(ImmutableList.of(new ResourceLocation(".")));
                    IBakedModel baked = model.bake(ModelRotation.X0_Y0 as ModelBakery, DefaultVertexFormats.ITEM as Function<ResourceLocation, TextureAtlasSprite>, { loc -> data.sprite });
                    List<BakedQuad> quads = baked.getQuads(null, null, 0 as Random);
                    for (BakedQuad q : quads) {
                        MutableQuad mut = new MutableQuad();
                        mut.fromBakedItem(q);
                        mut.translated(0, 0, -(7.5 / 16.0));
                        mut.scaled(1, 1, 16);
                        mut.rotate(Direction.SOUTH, evaluateFace(face), 0.5f, 0.5f, 0.5f);
                        mut.scalef(size[0], size[1], size[2]);
                        mut.translated(from[0], from[1], from[2]);
                        mut.setCalculatedNormal();
                        mut.setShade(shade);
                        mut.lighti(l, 0);
                        mut.colouri(rgba);
                        mut.setSprite(data.sprite);
                        addTo.add(mut);
                    }
                }
            }
        }
    }

    private static Direction evaluateFace(Direction side) {
        if(side == null) {
            return Direction.UP;
        } else {
            return side;
        }
    }
}
