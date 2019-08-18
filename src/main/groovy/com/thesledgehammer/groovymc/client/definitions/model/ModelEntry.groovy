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

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation

class ModelEntry extends ModelEntryConsumer {

    private ModelResourceLocation modelLocation;
    private IBakedModel bakedModel;

    ModelEntry(String resourceDomain, String resourcePath, String variantIn, IBakedModel bakedModel) {
        setModelResourceLocation(new ModelResourceLocation(new ResourceLocation(resourceDomain, resourcePath), variantIn));
        setBakedModel(bakedModel);
    }

    ModelEntry(ResourceLocation resourceLocation, String variantIn, IBakedModel bakedModel) {
        setModelResourceLocation(new ModelResourceLocation(resourceLocation, variantIn));
        setBakedModel(bakedModel);
    }

    ModelEntry(String resourceDomain, String resourcePath, IBakedModel bakedModel) {
        setModelResourceLocation(new ModelResourceLocation(new ResourceLocation(resourceDomain, resourcePath), "inventory"));
        setBakedModel(bakedModel);
    }

    ModelEntry(ResourceLocation resourceLocation, IBakedModel bakedModel) {
        setModelResourceLocation(new ModelResourceLocation(resourceLocation, "inventory"));
        setBakedModel(bakedModel);
    }

    ModelEntry(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
        setModelResourceLocation(modelLocation);
        setBakedModel(bakedModel);
    }

    private void setModelResourceLocation(ModelResourceLocation modelLocation) {
        this.modelLocation = modelLocation;
       // this.mrlList.add(modelLocation);
    }

    private void setBakedModel(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
        //this.bakedList.add(bakedModel);
    }

    ModelResourceLocation getModelResourceLocation() {
        return modelLocation;
    }

    IBakedModel getBakedModel() {
        return bakedModel;
    }
}
