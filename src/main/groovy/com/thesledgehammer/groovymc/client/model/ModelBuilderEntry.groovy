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
package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import com.thesledgehammer.groovymc.client.model.GroovyVariableModel
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

import javax.annotation.Nullable

/**
 * Todo: If block model is null, use item model. Allow nullable models
 * A Model Helper class for creating Item & Block Models in both Static & Variable formats.
 * Doesn't work properly in practice
 */
class ModelBuilderEntry {

    static class Static {

        private GroovyStaticModel itemModel;
        private GroovyStaticModel blockModel;

        Static(String fileName) {
           // this.itemModel = new GroovyStaticModel("item", fileName);
            this.blockModel = new GroovyStaticModel("block", fileName);
        }

        GroovyStaticModel ItemModel() {
            return itemModel;
        }

        GroovyStaticModel BlockModel() {
            return blockModel;
        }
    }

    static class Variable {

        private GroovyVariableModel itemModel;
        private GroovyVariableModel blockModel;

        Variable(String fileName) {
           // this.itemModel = new GroovyVariableModel("item", fileName);
            this.blockModel = new GroovyVariableModel("block", fileName);
        }

        GroovyVariableModel ItemModel() {
            return itemModel;
        }

        GroovyVariableModel BlockModel() {
            return blockModel;
        }
    }

    static void setModelResourceLocation(@Nullable String blockModelResourceLocation, String itemModelResourceLocation, IBakedModel bakedModel) {
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).add(bakedModel).build();
    }

    static void setModelResourceLocation(@Nullable ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation, IBakedModel bakedModel) {
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).add(bakedModel).build();
    }

    static void setModelResourceLocation(@Nullable ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation) {
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).build();
    }

    static void setModelResourceLocation(@Nullable String blockModelResourceLocation, String itemModelResourceLocation) {
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).build();
    }
}
