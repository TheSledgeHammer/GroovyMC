package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

class ModelEntryV2 extends ModelEntryConsumer {

    private final ModelResourceLocation modelLocation
    private final IBakedModel bakedModel

    ModelEntryV2(Register register) {
        this.modelLocation = register.modelLocation;
        this.bakedModel = register.bakedModel;
    }

    List<ModelResourceLocation> getModelResourceLocations() {
        return GroovyDefinitionContext().getModelResourceLocations();
    }

    List<IBakedModel> getIBakedModels() {
        return GroovyDefinitionContext().getIBakedModels();
    }

    static class Register {

        private ModelResourceLocation modelLocation
        private IBakedModel bakedModel

        Register() {

        }

        static Register add(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
            GroovyDefinitionContext.Instance().setModelResourceLocation(modelLocation);
            GroovyDefinitionContext.Instance().setIBakedModel(bakedModel);
            return new Register();
        }

        static Register add(String modelLocation, IBakedModel bakedModel) {
            GroovyDefinitionContext.Instance().setModelResourceLocation(modelLocation);
            GroovyDefinitionContext.Instance().setIBakedModel(bakedModel);
            return new Register();
        }

        static Register add(String type, String modelLocation, IBakedModel bakedModel) {
            GroovyDefinitionContext.Instance().setCustomModelResourceLocation(type, modelLocation);
            GroovyDefinitionContext.Instance().setIBakedModel(bakedModel);
            return new Register();
        }

        ModelEntryV2 build() {
            return new ModelEntryV2(this);
        }
    }
}
