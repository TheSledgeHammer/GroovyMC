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

package com.thesledgehammer.groovymc.client.render

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer

class GroovyModelBase extends ModelBase {

    private GroovyStaticModel model;
    private GroovyDefinitionContext GDC = GroovyDefinitionContext.Instance();
    private Map<GroovysonObjectPart, ModelRenderer> rendererMap = new HashMap<>();

    GroovyModelBase(GroovyStaticModel model) {
        this.model = model;
        addModelRenderParts();
        for(GroovysonObjectPart parts : model.getModelElements()) {
            GDC.setStaticFrom(parts);
            GDC.setStaticTo(parts);
            GDC.setStaticUV(parts);
            GDC.setStaticTexture(parts);
            GDC.setStaticRotationAngle(parts);
            GDC.setStaticRotationAxis(parts);
        }
    }

    GroovyStaticModel getGroovyModel() {
        return model;
    }

    private void addModelRenderParts() {
        for(GroovysonObjectPart parts : model.getModelElements()) {
            rendererMap.put(parts, new ModelRenderer(this));
        }
    }

    ModelRenderer getModelRendererByModelElement(int index) {
       return rendererMap.get(model.getModelElements(index));
    }

    ModelRenderer getModelRendererByModelElement(GroovysonObjectPart part) {
        return rendererMap.get(part);
    }

    void setTextureOffsetByModelElement(int index, int texWidth, int texHeight) {
        rendererMap.get(model.getModelElements(index)).setTextureOffset(texWidth, texHeight);
    }

    void setTextureOffsetByModelElement(GroovysonObjectPart part, int texWidth, int texHeight) {
        rendererMap.get(part).setTextureOffset(texWidth, texHeight);
    }

    void addAllBoxes() {
        GroovyStaticModel model = model;
        for (GroovysonObjectPart part : model.getModelElements()) {
            //Axis: X = 0, Y = 1, Z = 2

            float offX = GDC.getStaticFrom(part).get(0) as float;
            float offY = GDC.getStaticFrom(part).get(1) as float;
            float offZ = GDC.getStaticFrom(part).get(2) as float;

            int width = (GDC.getStaticTo(part).get(0) - GDC.getStaticFrom(part).get(0)) as int;
            int height = (GDC.getStaticTo(part).get(1)  - GDC.getStaticFrom(part).get(1)) as int;
            int depth = (GDC.getStaticTo(part).get(2)  - GDC.getStaticFrom(part).get(2)) as int;

            rendererMap.get(part).addBox(offX, offY, offZ, width, height, depth);
        }
    }

    void addAllRotationAxis() {
        GroovyStaticModel model = model;
        for (GroovysonObjectPart part : model.getModelElements()) {
            float rotAxisX = GDC.getStaticRotationAxis(part).get(0) as float;
            float rotAxisY = GDC.getStaticRotationAxis(part).get(1) as float;
            float rotAxisZ = GDC.getStaticRotationAxis(part).get(2) as float;

            rendererMap.get(part).setRotationPoint(rotAxisX, rotAxisY, rotAxisZ);
        }
    }

    void addAllRotationAngles() {
        GroovyStaticModel model = model;
        for (GroovysonObjectPart part : model.getModelElements()) {
            rendererMap.get(part).rotateAngleX = GDC.getStaticRotationAngle(part).get(0) as float;
            rendererMap.get(part).rotateAngleY = GDC.getStaticRotationAngle(part).get(1) as float;
            rendererMap.get(part).rotateAngleZ = GDC.getStaticRotationAngle(part).get(2) as float;
        }
    }
}
