/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Several changes made for use in GroovyMC. Renamed from JsonVariableCuboid. Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import net.minecraft.util.Direction

class GroovysonVariableCuboid extends GroovysonVariableCuboidBase {

    protected Map<Direction, GroovysonVariableFaceUV> facesGUV = new HashMap<>();

    GroovysonVariableCuboid(List<GroovysonObjectPart> objectParts) {
        super(objectParts);

        for(GroovysonObjectPart parts : objectParts) {
            String invert = null;
            if(GDC.getVariableInvert(parts) != null) {
                invert = GDC.getVariableInvert(parts);
            }

            String bothSides = null;
            if(GDC.getVariableBothSides(parts) != null) {
                bothSides = GDC.getVariableBothSides(parts);
            }

            for(Direction face : Direction.values()) {
                if(parts.Facing(face) != null) {
                    if(GDC.getVariableInvert(parts) != null) {
                        GDC.setVariableInvert(parts, invert);
                    }
                    if(GDC.getVariableBothSides(parts) != null) {
                        GDC.setVariableBothSides(parts, bothSides);
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
    protected VariableFaceData getFaceData(GroovysonObjectPart objectPart, Direction side, ITextureGetter spriteLookup) {
        GroovysonVariableFaceUV var = facesGUV.get(side);
        if(var == null || !var.GDC.getVariableVisible(objectPart).getValue()) {
            return null;
        }
        return var.evaluateFace(objectPart, side, spriteLookup);
    }
}