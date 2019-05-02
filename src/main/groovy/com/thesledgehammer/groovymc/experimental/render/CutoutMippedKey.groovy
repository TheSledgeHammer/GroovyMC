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

class CutoutMippedKey {

    private String renderName;
    private LinkedList<String> cutoutMippedList;
    private GroovysonObjectPart cutoutMippedPart;

    CutoutMippedKey(GroovyBaseModel groovyBaseModel, int modelElement) {
        setCutoutMippedKey(groovyBaseModel, modelElement);
    }

    private void setCutoutMippedKey(GroovyBaseModel groovyBaseModel, int modelElement) {
        String renderType = groovyBaseModel.getModelElements(modelElement).BlockRenderTypes();
        if(renderType.contains("cutout_mipped")) {
            this.renderName = "cutout_mipped";
            this.cutoutMippedList = groovyBaseModel.getModelElements(modelElement).BlockRenderTypeFace(renderName);
            this.cutoutMippedPart = new GroovysonObjectPart(groovyBaseModel.getGroovyModel(), modelElement);
        }
    }

    List<GroovysonObjectPart> CutoutMipped(GroovyBaseModel groovyBaseModel) {
        List<GroovysonObjectPart> cutoutMippedParts = new ArrayList<>();
        for(GroovysonObjectPart part : groovyBaseModel.getModelElements()) {
            if(part.BlockRenderType(renderName) == cutoutMippedPart.BlockRenderType(renderName)) {
                cutoutMippedParts.add(cutoutMippedPart);
            }
        }
        return cutoutMippedParts;
    }
}
