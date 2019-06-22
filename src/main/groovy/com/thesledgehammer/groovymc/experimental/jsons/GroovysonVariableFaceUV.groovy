/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableFaceUV. Converted to .groovy
 */

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.util.EnumFacing

//Defaults elements containing "progress_size" to 0 + float value if it exists.
class GroovysonVariableFaceUV {

    protected GroovysonVariableContext GVC = new GroovysonVariableContext();

    GroovysonVariableFaceUV(List<GroovysonObjectPart> objectParts) {
        for(GroovysonObjectPart parts : objectParts) {
            GVC.setGroovysonVariableFaceUV(parts);
        }
    }

    VariableFaceData evaluateFace(GroovysonObjectPart parts, EnumFacing facing, ITextureGetter spriteLookup) {
        VariableFaceData data = new VariableFaceData();
        ModelUtil.TexturedFace face = spriteLookup.get(GVC.getTexture(parts, facing).getValue());
        data.sprite = face.sprite;
        data.rotations = (int) VB.getTextureRotation(parts, facing).getValue();
        data.uvs.minU = (float) (GVC.getFaceUV(parts, facing).get(0).getValue() / 16.0);
        data.uvs.minV = (float) (GVC.getFaceUV(parts, facing).get(1).getValue() / 16.0);
        data.uvs.maxU = (float) (GVC.getFaceUV(parts, facing).get(2).getValue() / 16.0);
        data.uvs.maxV = (float) (GVC.getFaceUV(parts, facing).get(3).getValue() / 16.0);
        data.uvs = data.uvs.inParent(face.faceData);
        data.invertNormal = GVC.getInvert(parts).getValue();
        data.bothSides = GVC.getBothSides(parts).getValue();
        return data;
    }
}
