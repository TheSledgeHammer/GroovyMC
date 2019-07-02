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

package com.thesledgehammer.groovymc.client.definitions.model

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

class ModelEntry extends ModelEntryConsumer {

    private static ModelEntry instance

    ModelEntry(Register register) {
        instance = this;
    }

    static ModelEntry Instance() {
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
        throw new NullPointerException("No ModelResourceLocation was found at ${modelResourceLocation}");
        //return null;
    }

    IBakedModel getIBakedModel(IBakedModel bakedModel) {
        for(IBakedModel baked : getIBakedModels()) {
            if(bakedModel.equals(baked)) {
                return baked
            }
        }
        throw new NullPointerException("No IBakedModel was found named ${bakedModel}");
        //return null;
    }

    static class Register {

        Register() {

        }

        static Register add(String modelLocation) {
            GroovyDefinitionContext.Instance().setModelResourceLocation(modelLocation);
            return new Register();
        }

        static Register add(ModelResourceLocation modelLocation) {
            GroovyDefinitionContext.Instance().setModelResourceLocation(modelLocation);
            return new Register();
        }

        static Register add(IBakedModel bakedModel) {
            GroovyDefinitionContext.Instance().setIBakedModel(bakedModel);
            return new Register();
        }

        static Register add(String type, String modelLocation) {
            GroovyDefinitionContext.Instance().setCustomModelResourceLocation(type, modelLocation);
            return new Register();
        }

        ModelEntry build() {
            return new ModelEntry(this);
        }
    }
}
