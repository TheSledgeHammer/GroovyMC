package com.thesledgehammer.groovymc.experimental.models.vanilla

import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity

class GroovyModelBase extends ModelBase {

    private GroovyStaticModel model;
    private Map<GroovysonObjectPart, ModelRenderer> rendererMap = new HashMap<>();

    GroovyModelBase(GroovyStaticModel model) {
        this.model = model;
        addModelRenderParts();
    }

    GroovyStaticModel getGroovyStaticModel() {
        return model;
    }

    private void addModelRenderParts() {
        for(GroovysonObjectPart groovysonObjectPart : model.getModelElements()) {
            rendererMap.put(groovysonObjectPart, new ModelRenderer(this));
        }
    }

    ModelRenderer getModelRendererFromGroovyStaticModel(int index) {
       return rendererMap.get(model.getModelElements(index));
    }



    ModelRenderer addAllBoxes() {
        GroovyStaticModel model = model;
        ModelRenderer renderer = null;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            //Axis: X = 0, Y = 1, Z = 2
            float offX = model.getGroovysonModel().From(i, 0);
            float offY = model.getGroovysonModel().From(i, 1);
            float offZ = model.getGroovysonModel().From(i, 2);

            int width = (model.getGroovysonModel().To(i, 0) - model.getGroovysonModel().From(i, 0)) as int;
            int height = (model.getGroovysonModel().To(i, 1) - model.getGroovysonModel().From(i, 1)) as int;
            int depth = (model.getGroovysonModel().To(i, 2) - model.getGroovysonModel().From(i, 2)) as int;

            int texWidth = 0;
            int texHeight = 0;
            renderer = getModelRendererFromGroovyStaticModel(i)
                    .addBox(offX, offY, offZ, width, height, depth)
                    .setTextureOffset(texWidth, texHeight);
        }
        return renderer;
    }

    ModelRenderer addAllRotationAxis() {
        GroovyStaticModel model = model;
        ModelRenderer renderer = null;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            float rotAxisX = model.getGroovysonModel().RotationAxis(i, 0);
            float rotAxisY = model.getGroovysonModel().RotationAxis(i, 1);
            float rotAxisZ = model.getGroovysonModel().RotationAxis(i, 2);

            renderer = getModelRendererFromGroovyStaticModel(i)
                    .setRotationPoint(rotAxisX, rotAxisY, rotAxisZ);
        }
        return renderer
    }

    ModelRenderer addAllRotationAngles() {
        GroovyStaticModel model = model;
        ModelRenderer renderer = null;
        for (int i = 0; i < model.getModelElements().size(); i++) {
            renderer = getModelRendererFromGroovyStaticModel(i);

            renderer.rotateAngleX = model.getGroovysonModel().RotationAngle(i, 0);
            renderer.rotateAngleY = model.getGroovysonModel().RotationAngle(i, 1);
            renderer.rotateAngleZ = model.getGroovysonModel().RotationAngle(i, 2);
        }
        return renderer;
    }
}
