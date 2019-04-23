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

//A GroovysonModel Cache used for both static and variable models. Especially models that have dynamic model elements (i.e. animation)
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

    float[] Translation(String name) {
        return ListTools.FloatListToFloatArray(groovysonObject.Translation(name));
    }

    float[] Rotation(String name) {
        return ListTools.FloatListToFloatArray(groovysonObject.Rotation(name));
    }

    float[] Scale(String name) {
        return ListTools.FloatListToFloatArray(groovysonObject.Scale(name));
    }

    float[] From() {
        return ListTools.FloatListToFloatArray(groovysonObjectPart.From());
    }

    String[] BlockRenderTypeFace(String renderType) {
        return ListTools.StringListToStringArray(groovysonObjectPart.BlockRenderTypeFace(renderType));
    }

    float[] To() {
        return ListTools.FloatListToFloatArray(groovysonObjectPart.To());
    }

    float[] FacingUV(EnumFacing face) {
        return  ListTools.FloatListToFloatArray(groovysonObjectPart.FacingUv(face));
    }
}
