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

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing

class GroovysonObjectCache {

    //GroovyBaseModel: could revise to use this for components that use arrays and not lists
    //This could provide a cache for variable models as toArray is a deepCopy of that List
    //As well as a List of non strings can contain a string. Thus can assign the string to a known variable before it becomes an array

    private GroovysonObject GroovysonObject;
    private GroovysonObjectPart GroovysonObjectPart;

    GroovysonObjectCache(GroovysonObject GroovysonObject) {
        this.GroovysonObject = GroovysonObject;
    }

    GroovysonObjectCache(GroovysonObjectPart GroovysonObjectPart) {
        this.GroovysonObjectPart = GroovysonObjectPart;
    }

    GroovysonObjectCache(GroovysonObject GroovysonObject, GroovysonObjectPart GroovysonObjectPart) {
        this.GroovysonObject = GroovysonObject;
        this.GroovysonObjectPart = GroovysonObjectPart;
    }

    float[] Translation(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject.Translation(name));
    }

    float[] Rotation(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject.Rotation(name));
    }

    float[] Scale(String name) {
        return ListTools.FloatListToFloatArray(GroovysonObject.Scale(name));
    }

    float[] From() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart.From());
    }

    String[] BlockRenderTypeFace(String renderType) {
        return ListTools.StringListToStringArray(GroovysonObjectPart.BlockRenderTypeFace(renderType));
    }

    float[] To() {
        return ListTools.FloatListToFloatArray(GroovysonObjectPart.To());
    }

    float[] FacingUV(EnumFacing face) {
        return  ListTools.FloatListToFloatArray(GroovysonObjectPart.FacingUv(face));
    }
}
