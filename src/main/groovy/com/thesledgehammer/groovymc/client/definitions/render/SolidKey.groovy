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

package com.thesledgehammer.groovymc.client.definitions.render

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart

class SolidKey {

    private List<GroovysonObjectPart> solidParts = new ArrayList<>();

    SolidKey(GroovysonModel groovysonModel) {
        for(GroovysonObjectPart parts : groovysonModel.getRawModelParts()) {
            if(parts.BlockRenderType() == "solid") {
                solidParts.add(parts);
            }
        }
    }

    String getRenderType() {
        return "solid";
    }

    ArrayList<GroovysonObjectPart> getSolidModelElements() {
        return solidParts;
    }
}
