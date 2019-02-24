package com.thesledgehammer.groovymc.client.definitions

import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

abstract class ModelEntry extends ModelEntryConsumer {

    ModelEntry(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
        this.GroovyDefinitionContext().setModelResourceLocation(modelLocation);
        this.GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    ModelEntry(String modelLocation, IBakedModel bakedModel) {
        this.GroovyDefinitionContext().setModelResourceLocation(modelLocation);
        this.GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    ModelEntry(String type, String modelLocation, IBakedModel bakedModel) {
        this.GroovyDefinitionContext().setCustomModelResourceLocation(type, modelLocation);
        this.GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    ModelResourceLocation getModelResourceLocation() {
        return this.GroovyDefinitionContext().getModelResourceLocation();
    }

    IBakedModel getIBakedModel() {
        return this.GroovyDefinitionContext().getIBakedModel();
    }
}
