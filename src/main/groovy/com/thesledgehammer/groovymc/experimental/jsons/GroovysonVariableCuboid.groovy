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

    protected Map<EnumFacing, GroovysonVariableFaceUV> facesGUV = new HashMap<>();

    GroovysonVariableCuboid(List<GroovysonObjectPart> objectParts) {
        super(objectParts);

        for(GroovysonObjectPart parts : objectParts) {
            String invert = null;
            if(GVC.getInvert(parts) != null) {
                invert = GVC.getInvert(parts);
            }

            String bothSides = null;
            if(GVC.getBothSides(parts) != null) {
                bothSides = GVC.getBothSides(parts);
            }

            for(EnumFacing face : EnumFacing.VALUES) {
                if(parts.Facing(face) != null) {
                    if(GVC.getInvert(parts) != null) {
                        GVC.setInvert(parts, invert);
                    }
                    if(GVC.getBothSides(parts) != null) {
                        GVC.setBothSides(parts, bothSides);
                    }
                    facesGUV.put(face, new GroovysonVariableFaceUV(objectParts));
                }
            }
            if(facesGUV.size() == 0) {
                throw new Exception("Expected between 1 and 6 faces, got an empty object ${parts.Faces().toString()}");
            }
        }
    }

    @Override
    protected VariableFaceData getFaceData(GroovysonObjectPart objectPart, EnumFacing side, ITextureGetter spriteLookup) {
        GroovysonVariableFaceUV var = facesGUV.get(side);
        if(var == null || !var.GVC.getVisible(objectPart).getValue()) {
            return null;
        }
        return var.evaluateFace(objectPart, side, spriteLookup);
    }
}