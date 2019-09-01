/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableFaceUV. Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.model.ModelUtil
import net.minecraft.util.EnumFacing

class GroovysonVariableFaceUV {

    protected GroovyDefinitionContext GDC = GroovyDefinitionContext.Instance();

    GroovysonVariableFaceUV(List<GroovysonObjectPart> objectParts) {
        for(GroovysonObjectPart parts : objectParts) {
            if(parts != null) {
                GDC.setVariableFrom(parts, "0");
                GDC.setVariableTo(parts, "0");
                GDC.setVariableUV(parts, "0");
                GDC.setVariableVisible(parts, "true");
                GDC.setVariableInvert(parts, "false");
                GDC.setVariableBothSides(parts, "false");
                GDC.setVariableTexture(parts);
                GDC.setVariableTextureRotation(parts, "0");
            }
        }
    }

    VariableFaceData evaluateFace(GroovysonObjectPart parts, EnumFacing facing, ITextureGetter spriteLookup) {
        VariableFaceData data = new VariableFaceData();
        ModelUtil.TexturedFace face = spriteLookup.get(GDC.getVariableTexture(parts, facing).getValue());
        data.sprite = face.sprite;
        data.rotations = (int) GDC.getVariableTextureRotation(parts, facing).getValue();
        data.uvs.minU = (float) (GDC.getVariableFaceUV(parts, facing).get(0).getValue() / 16.0);
        data.uvs.minV = (float) (GDC.getVariableFaceUV(parts, facing).get(1).getValue() / 16.0);
        data.uvs.maxU = (float) (GDC.getVariableFaceUV(parts, facing).get(2).getValue() / 16.0);
        data.uvs.maxV = (float) (GDC.getVariableFaceUV(parts, facing).get(3).getValue() / 16.0);
        data.uvs = data.uvs.inParent(face.faceData);
        data.invertNormal = GDC.getVariableInvert(parts).getValue();
        data.bothSides = GDC.getVariableBothSides(parts).getValue();
        return data;
    }
}
