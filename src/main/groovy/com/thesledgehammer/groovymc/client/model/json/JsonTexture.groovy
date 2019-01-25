/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Changed constructors to work with GroovysonObjectPart & Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.utils.MathTools
import com.thesledgehammer.groovymc.client.model.ModelUtil
import net.minecraft.util.EnumFacing

class JsonTexture {

    final String location;
    final ModelUtil.UvFaceData faceData;

    JsonTexture(String location, ModelUtil.UvFaceData faceData) {
        this.location = location;
        this.faceData = faceData;
    }

    JsonTexture(String location, double minU, double minV, double maxU, double maxV) {
        this.location = location;
        faceData = new ModelUtil.UvFaceData(minU, minV, maxU, maxV);
    }

    JsonTexture(String location) {
        this(location, 0, 0 , 1, 1);
    }

    JsonTexture(GroovysonObjectPart groovysonObjectPart, String location, EnumFacing face) {
        this.location = location;
        ArrayList<Float> uvs = groovysonObjectPart.FacingUv(face);
        double[] arr = new double[4];
        for(int i = 0; i < 4; i++) {
            def elem = uvs.get(i);
            if(MathTools.isPrimitive(elem) && MathTools.isNumber(elem)) {
                arr[i] = (double) elem;
            }
            //TODO: if its a String, refer to MathTools/ StringTools for method implementation
        }
        faceData = new ModelUtil.UvFaceData();
        faceData.minU = (float) MathTools.clamp(arr[0] / 16.0, 0, 1.0);
        faceData.minV = (float) MathTools.clamp(arr[1] / 16.0, 0, 1.0);
        faceData.maxU = (float) MathTools.clamp(arr[2] / 16.0, 0, 1.0);
        faceData.maxV = (float) MathTools.clamp(arr[3] / 16.0, 0, 1.0);
    }

    JsonTexture(GroovysonObjectPart groovysonObjectPart, EnumFacing face) {
        ArrayList<Float> uvs = groovysonObjectPart.FacingUv(face);
        double[] arr = new double[4];
        for(int i = 0; i < 4; i++) {
            def elem = uvs.get(i);
            if(MathTools.isPrimitive(elem) && MathTools.isNumber(elem)) {
                arr[i] = (double) elem;
            }
            //TODO: if its a String, refer to MathTools/ StringTools for method implementation
        }
        faceData = new ModelUtil.UvFaceData();
        faceData.minU = (float) MathTools.clamp(arr[0] / 16.0, 0, 1.0);
        faceData.minV = (float) MathTools.clamp(arr[1] / 16.0, 0, 1.0);
        faceData.maxU = (float) MathTools.clamp(arr[2] / 16.0, 0, 1.0);
        faceData.maxV = (float) MathTools.clamp(arr[3] / 16.0, 0, 1.0);
    }

    JsonTexture andSub(JsonTexture sub) {
        ModelUtil.UvFaceData data = faceData.andSub(sub.faceData);
        return new JsonTexture(location, data);
    }

    JsonTexture inParent(JsonTexture parent) {
        return parent.andSub(this);
    }
}
