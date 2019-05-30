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

import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing


/**Unused (Outside of VariableBase), May be rewritten or removed
 * Not really needed with change to how static and variable models read model files
 **/
@Deprecated //A GroovysonModel Cache used for both static and variable models. Especially models that have dynamic model elements (i.e. animation)
class GroovysonObjectCache {

    private GroovysonObject groovysonObject;
    private GroovysonObjectPart groovysonObjectPart;

    GroovysonObjectCache() {
        setGroovysonObject(groovysonObject);
        setGroovysonObjectPart(groovysonObjectPart);
    }

    GroovysonObjectCache(GroovysonObject groovysonObject, GroovysonObjectPart groovysonObjectPart) {
        setGroovysonObject(groovysonObject);
        setGroovysonObjectPart(groovysonObjectPart);
    }

    private void setGroovysonObject(GroovysonObject groovysonObject) {
        this.groovysonObject = groovysonObject;
    }

    private void setGroovysonObjectPart(GroovysonObjectPart groovysonObjectPart) {
        this.groovysonObjectPart = groovysonObjectPart;
    }

    GroovysonObject GroovysonObject() {
        return groovysonObject;
    }

    GroovysonObjectPart GroovysonObjectPart() {
        return groovysonObjectPart;
    }

    boolean Shade(boolean fallback) {
        if(GroovysonObject().getShade() == null) {
            return fallback;
        }
        return GroovysonObject().getShade();
    }

    boolean AmbientOcclusion() {
        return GroovysonObject().AmbientOcclusion();
    }

    boolean Visible() {
        return false;
    }

    long Light() {
        return 0;
    }

    long Colour() {
        return 0;
    }

    float[] Translation(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject().Translation(name));
    }

    float[] Rotation(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject().Rotation(name));
    }

    float[] Scale(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject().Scale(name));
    }

    float[] RotationOrigin() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().RotationOrigin());
    }

    float[] RotationAxis() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().RotationAxis());
    }

    float[] RotationAngle() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().RotationAngle());
    }

    boolean RotationRescale() {
        boolean rescale = GroovysonObjectPart().RotationRescale();
        return rescale;
    }

    String[] BlockRenderTypeFace(String renderType) {
        return ListTools.StringListToStringArray(GroovysonObjectPart().BlockRenderTypeFace(renderType));
    }

    float[] From() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().From());
    }

    float[] To() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().To());
    }

    float[] FacingUV(EnumFacing face) {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart().FacingUv(face));
    }

    boolean ShadePart() {
        boolean shade = GroovysonObjectPart().Shade();
        return shade;
    }

    String TextureFace(EnumFacing face) {
        String texture = GroovysonObjectPart().TextureFace(face);
        return texture;
    }

    String CullFace(EnumFacing face) {
        String cullface = GroovysonObjectPart().CullFaceFace(face);
        return cullface;
    }

    int TintFace(EnumFacing face, int fallback) {
        int tint = GroovysonObjectPart().FacingTint(face, fallback);
        return tint;
    }

    int RotationFace(EnumFacing face, int fallback) {
        int rotation = GroovysonObjectPart().FacingRotation(face, fallback);
        return rotation;
    }

    def ItemOverride() {
        return GroovysonObject().Overrides();
    }

    def ItemOverrideModel() {
        return GroovysonObject().OverridesModel();
    }

    def ItemOverridePredicate() {
        return GroovysonObject().OverridesPredicate();
    }
}
