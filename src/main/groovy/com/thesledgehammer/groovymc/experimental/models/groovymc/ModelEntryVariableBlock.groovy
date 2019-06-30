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
package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.model.GroovyVariableModel
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

import javax.annotation.Nullable

class ModelEntryVariableBlock extends AbstractModelEntryVariable {

    private GroovyVariableModel groovyVariableItemModel;

    ModelEntryVariableBlock(String modelName, @Nullable ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation) {
        this.groovyVariableModel = new GroovyVariableModel("block", modelName);
        this.groovyVariableItemModel = new GroovyVariableModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).build();
    }

    ModelEntryVariableBlock(String modelName, @Nullable String blockModelResourceLocation, String itemModelResourceLocation) {
        this.groovyVariableModel = new GroovyVariableModel("block", modelName);
        this.groovyVariableItemModel = new GroovyVariableModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation).build();
    }

    ModelEntryVariableBlock(String modelName, @Nullable String blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation, IBakedModel bakedModel) {
        this.groovyVariableModel = new GroovyVariableModel("block", modelName);
        this.groovyVariableItemModel = new GroovyVariableModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation, bakedModel).add(itemModelResourceLocation).build();
    }

    ModelEntryVariableBlock(String modelName, @Nullable ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation, IBakedModel bakedModel) {
        this.groovyVariableModel = new GroovyVariableModel("block", modelName);
        this.groovyVariableItemModel = new GroovyVariableModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation, bakedModel).add(itemModelResourceLocation).build();
    }
}
