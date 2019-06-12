/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableCuboid. Converted to .groovy
 */

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.util.EnumFacing

class GroovysonVariableCuboid extends GroovysonVariableCuboidBase {

    protected Map<EnumFacing, GroovysonVariableFaceUV> faces = new HashMap<>();

    GroovysonVariableCuboid(GroovysonVariableModel model, int modelIndex) {
        super(model, modelIndex);

        String invert = null;
        if(model.getRawModelPart(modelIndex).Invert() != null) {
           invert = model.getRawModelPart(modelIndex).Invert()
        }

        String bothSides = null;
        if(model.getRawModelPart(modelIndex).BothSides() != null) {
            bothSides = model.getRawModelPart(modelIndex).BothSides();
        }

        for(EnumFacing face : EnumFacing.VALUES) {
            GroovysonObjectPart gFace = model.getRawModelPart(modelIndex);
            if(gFace.Facing(face) != null) {
                if(gFace.Invert() != null) {
                    setInvert(model.VariableInvert(modelIndex, Boolean.valueOf(invert)));
                }
                if(gFace.BothSides() != null) {
                    setBothSides(model.VariableBothSides(modelIndex, Boolean.valueOf(bothSides)));
                }
                faces.put(face, new GroovysonVariableFaceUV(model, modelIndex, face));
            }
        }
        if(faces.size() == 0) {
            throw new Exception("Expected between 1 and 6 faces, got an empty object ${getFaces().name()}");
        }
    }

    @Override
    protected VariableFaceData getFaceData(EnumFacing side, ITextureGetter spriteLookup) {
        GroovysonVariableFaceUV var = faces.get(side);
        if(var == null || !var.getVisible().getValue()) {
            return null;
        }
        return var.evaluate(spriteLookup);
    }
}