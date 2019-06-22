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

package com.thesledgehammer.groovymc.client.model.json

import net.minecraft.util.EnumFacing

//Methods will be moved to GroovysonStaticContext
class GroovysonStaticModel extends GroovysonAbstractModel {

    GroovysonStaticModel(String resourceObject, String fileName) {
        super(resourceObject, fileName);
    }

    GroovysonStaticModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, resourceObject, fileName);
    }

    Float From(int index, int axis) {
        return getRawModelPart(index).From().get(axis)
    }

    Float To(int index, int axis) {
        return getRawModelPart(index).To().get(axis)
    }

    ArrayList<Float> FaceUV(int index, EnumFacing face) {
        return getRawModelPart(index).FacingUv(face);
    }

    Float RotationAngle(int index, int axis) {
        if(getRawModelPart(index).RotationAxis() != null) {
            return getRawModelPart(index).RotationAxis().get(axis);
        }
        return null;
    }

    Float RotationAxis(int index, int axis) {
        if(getRawModelPart(index).RotationAxis() != null) {
            return getRawModelPart(index).RotationAxis().get(axis);
        }
        return null;
    }

    Float RotationOrigin(int index, int axis) {
        if(getRawModelPart(index).RotationOrigin() != null) {
            return getRawModelPart(index).RotationOrigin().get(axis);
        }
        return null;
    }

    float FaceTint(int index, EnumFacing face, int fallback) {
        return getRawModelPart(index).FacingTint(face, fallback);
    }

    float FaceRotation(int index, EnumFacing face, int fallback) {
        return getRawModelPart(index).FacingRotation(face, fallback);
    }

    String TextureFace(int index, EnumFacing face) {
        return getRawModelPart(index).TextureFace(face);
    }

    boolean Shade(int index) {
        return getRawModelPart(index).Shade()
    }

    boolean RotationRescale(int index) {
        return getRawModelPart(index).RotationRescale();
    }

    String CullFace(int index, EnumFacing face) {
        return getRawModelPart(index).CullFaceFace(face);
    }

    String Light(int index) {
        return getRawModelPart(index).Light()
    }

    String getVisible(int index) {
        return getRawModelPart(index).Visible();
    }

    String getColour(int index) {
        return getRawModelPart(index).Colour();
    }
}
