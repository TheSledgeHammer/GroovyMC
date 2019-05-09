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

package com.thesledgehammer.groovymc.utils

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import groovy.transform.ToString
import net.minecraft.util.EnumFacing

class JsonTools {

    static JsonQuads[] Quads(ArrayList<GroovysonObjectPart > modelParts, EnumFacing face) {
        JsonQuads[] jQuads = new JsonQuads[modelParts.size()];
        for(int i = 0; i < modelParts.size(); i++) {
            jQuads[i] = new JsonQuads(modelParts.get(i), modelParts.get(i).From() as float[], modelParts.get(i).To() as float[], face);
           // println "ModelPart " + modelParts.get(i).getPartName() + " Face " + face + " JsonQuads " + jQuads[i].face;
        }
        return jQuads;
    }

    static JsonQuads QuadAFace(ArrayList<GroovysonObjectPart > modelParts, EnumFacing face, int rawModelTexture) {
        if(rawModelTexture > Quads(modelParts, face).size()) {
            Log.logError("This ModelTexture does not contain " + face);
            Log.logError("Or this Model does not contain a ModelTexture at " + rawModelTexture);
            return null;
        }
        return Quads(modelParts, face)[rawModelTexture];
    }
}
