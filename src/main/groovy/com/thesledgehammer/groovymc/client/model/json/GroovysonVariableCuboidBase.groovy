/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableCuboidBase. Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.utils.MathTools
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import net.minecraft.util.EnumFacing

import javax.vecmath.Vector3f
//Defaults elements containing "progress_size" to 0 + float value if it exists.
abstract class GroovysonVariableCuboidBase {

    protected GroovyDefinitionContext GDC = GroovyDefinitionContext.Instance();

    GroovysonVariableCuboidBase(List<GroovysonObjectPart> objectParts) {
        for(GroovysonObjectPart parts : objectParts) {
            if(parts != null) {
                GDC.setVariableFrom(parts, "0");
                GDC.setVariableTo(parts, "0");
                GDC.setVariableShade(parts, "true");
                GDC.setVariableVisible(parts, "true");
                GDC.setVariableLight(parts, "0");
                GDC.setVariableColour(parts, "-1");
            }
        }
    }

    void addQuad(GroovysonObjectPart parts, List<MutableQuad> addTo, ITextureGetter spriteLookup) {
        if (GDC.getVariableVisible(parts).getValue()) {
            float[] from = bakePosition(GDC.getVariableFrom(parts));
            float[] to = bakePosition(GDC.getVariableTo(parts));
            boolean shade = GDC.getVariableShade(parts).getValue();
            int l = (int) (GDC.getVariableLight(parts).getValue() & 15);
            int rgba = MathTools.swapARGBforABGR((int) GDC.getVariableColour(parts).getValue());
            for (EnumFacing face : EnumFacing.VALUES) {
                VariableFaceData data = getFaceData(parts, face, spriteLookup);
                if (data != null) {
                    Vector3f radius = new Vector3f(to[0] - from[0] as float, to[1] - from[1] as float, to[2] - from[2] as float);
                    radius.scale(0.5f);
                    Vector3f center = new Vector3f(from);
                    center.add(radius);
                    MutableQuad quad = ModelUtil.createFace(face, center, radius, data.uvs);
                    quad.rotateTextureUp(data.rotations);
                    quad.lighti(l, 0);
                    quad.colouri(rgba);
                    quad.texFromSprite(data.sprite);
                    quad.setSprite(data.sprite);
                    quad.setShade(shade);
                    if (data.bothSides) {
                        addTo.add(quad.copyAndInvertNormal());
                    } else if (data.invertNormal) {
                        quad = quad.copyAndInvertNormal();
                    }
                    addTo.add(quad);
                }
            }
        }
    }

    private static float[] bakePosition(List<VariableDouble> vIn) {
        float x = (float) (vIn.get(0).getValue() / 16f);
        float y = (float) (vIn.get(1).getValue() / 16f);
        float z = (float) (vIn.get(2).getValue() / 16f);
        return [x, y, z];
    }

    protected abstract VariableFaceData getFaceData(GroovysonObjectPart objectPart, EnumFacing side, ITextureGetter spriteLookup);
}

