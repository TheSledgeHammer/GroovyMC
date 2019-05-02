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

package com.thesledgehammer.groovymc.experimental.render

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart

class SolidKey {

    private String renderName;
    private LinkedList<String> solidList;
    private GroovysonObjectPart solidPart;

    SolidKey(GroovyBaseModel groovyBaseModel, int modelElement) {
        setSolidKey(groovyBaseModel, modelElement);
    }

    private void setSolidKey(GroovyBaseModel groovyBaseModel, int modelElement) {
        String renderType = groovyBaseModel.getModelElements(modelElement).BlockRenderTypes();
        if(renderType.contains("solid")) {
            this.renderName = "solid";
            this.solidList = groovyBaseModel.getModelElements(modelElement).BlockRenderTypeFace(renderName);
            this.solidPart = new GroovysonObjectPart(groovyBaseModel.getGroovyModel(), modelElement);
        }
    }

    List<GroovysonObjectPart> Solid(GroovyBaseModel groovyBaseModel) {
        List<GroovysonObjectPart> solidParts = new ArrayList<>();
        for(GroovysonObjectPart part : groovyBaseModel.getModelElements()) {
            if(part.BlockRenderType(renderName) == solidPart.BlockRenderType(renderName)) {
                solidParts.add(solidPart);
            }
        }
        return solidParts;
    }
}
