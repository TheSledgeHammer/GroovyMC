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

package com.thesledgehammer.groovymc.experimental.models.vanilla

import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer

class GroovyModelBase extends ModelBase {

    private GroovyStaticModel model;
    private Map<GroovysonObjectPart, ModelRenderer> rendererMap = new HashMap<>();

    GroovyModelBase(GroovyStaticModel model) {
        this.model = model;
        addModelRenderParts();
    }

    GroovyStaticModel getGroovyModel() {
        return model;
    }

    private void addModelRenderParts() {
        for(GroovysonObjectPart groovysonObjectPart : model.getModelElements()) {
            rendererMap.put(groovysonObjectPart, new ModelRenderer(this));
        }
    }

    ModelRenderer getModelRendererByModelElement(int index) {
       return rendererMap.get(model.getModelElements(index));
    }

    void setTextureOffsetByModelElement(int index, int texWidth, int texHeight) {
        rendererMap.get(model.getModelElements(index)).setTextureOffset(texWidth, texHeight);
    }

    void addAllBoxes() {
        GroovyStaticModel model = model;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            //Axis: X = 0, Y = 1, Z = 2
            float offX = model.getGroovysonModel().From(i, 0);
            float offY = model.getGroovysonModel().From(i, 1);
            float offZ = model.getGroovysonModel().From(i, 2);

            int width = (model.getGroovysonModel().To(i, 0) - model.getGroovysonModel().From(i, 0)) as int;
            int height = (model.getGroovysonModel().To(i, 1) - model.getGroovysonModel().From(i, 1)) as int;
            int depth = (model.getGroovysonModel().To(i, 2) - model.getGroovysonModel().From(i, 2)) as int;

            rendererMap.get(model.getModelElements(i)).addBox(offX, offY, offZ, width, height, depth);
        }
    }

    void addAllRotationAxis() {
        GroovyStaticModel model = model;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            float rotAxisX = model.getGroovysonModel().RotationAxis(i, 0);
            float rotAxisY = model.getGroovysonModel().RotationAxis(i, 1);
            float rotAxisZ = model.getGroovysonModel().RotationAxis(i, 2);

            rendererMap.get(model.getModelElements(i)).setRotationPoint(rotAxisX, rotAxisY, rotAxisZ);
        }
    }

    void addAllRotationAngles() {
        GroovyStaticModel model = model;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            rendererMap.get(model.getModelElements(i)).rotateAngleX = model.getGroovysonModel().RotationAngle(i, 0);
            rendererMap.get(model.getModelElements(i)).rotateAngleY = model.getGroovysonModel().RotationAngle(i, 1);
            rendererMap.get(model.getModelElements(i)).rotateAngleZ = model.getGroovysonModel().RotationAngle(i, 2);
        }
    }
}
