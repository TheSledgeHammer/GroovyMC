/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableFaceUV. Converted to .groovy
 */

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import net.minecraft.util.EnumFacing
//Defaults elements containing "progress_size" to 0 + float value if it exists.
class GroovysonVariableFaceUV extends GroovysonVariables {
    
    GroovysonVariableFaceUV(GroovysonVariableModel model, int modelIndex, EnumFacing face) {
        super(model);
        setFaces(face);
        setFrom(model.VariableFrom(modelIndex, "0"));
        setTo(model.VariableTo(modelIndex, "0"));
        setUV(model.VariableUV(modelIndex, face, "0"));
        setVisible(model.VariableVisible(modelIndex, true));
        setInvert(model.VariableInvert(modelIndex, false));
        setBothSides(model.VariableBothSides(modelIndex, false));
        setTexture(model.VariableTexture(modelIndex, face));
        setTextureRotation(model.VariableTextureRotation(modelIndex, face, "0"));
    }

    VariableFaceData evaluate(ITextureGetter spriteLookup) {
        VariableFaceData data = new VariableFaceData();
        ModelUtil.TexturedFace face = spriteLookup.get(getTexture().getValue());
        data.sprite = face.sprite;
        data.rotations = (int) getTextureRotation().getValue();
        data.uvs.minU = (float) (getFaceUV()[0].getValue() / 16.0);
        data.uvs.minV = (float) (getFaceUV()[1].getValue() / 16.0);
        data.uvs.maxU = (float) (getFaceUV()[2].getValue() / 16.0);
        data.uvs.maxV = (float) (getFaceUV()[3].getValue() / 16.0);
        data.uvs = data.uvs.inParent(face.faceData);
        data.invertNormal = getInvert().getValue();
        data.bothSides = getBothSides().getValue();
        return data;
    }
}
