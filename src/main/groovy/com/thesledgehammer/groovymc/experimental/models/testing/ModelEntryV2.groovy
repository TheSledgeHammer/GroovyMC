package com.thesledgehammer.groovymc.experimental.models.testing

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

class ModelEntryV2 extends ModelEntryConsumer {

    private static ModelEntryV2 instance

    ModelEntryV2(Register register) {
        instance = this;
    }

    static ModelEntryV2 Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    List<ModelResourceLocation> getModelResourceLocations() {
        return GroovyDefinitionContext().getModelResourceLocations();
    }

    List<IBakedModel> getIBakedModels() {
        return GroovyDefinitionContext().getIBakedModels();
    }

    ModelResourceLocation getModelResourceLocation(ModelResourceLocation modelResourceLocation) {
        for(ModelResourceLocation modelLoc : getModelResourceLocations()) {
            if(modelResourceLocation.equals(modelLoc)) {
                return modelLoc
            }
        }
        Log.logDebug("No ModelResourceLocation was found at ${modelResourceLocation}")
        return null;
    }

    IBakedModel getIBakedModel(IBakedModel bakedModel) {
        for(IBakedModel baked : getIBakedModels()) {
            if(bakedModel.equals(baked)) {
                return baked
            }
        }
        Log.logDebug("No IBakedModel was found named ${bakedModel}")
        return null;
    }

    static class Register {

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
