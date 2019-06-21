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
package com.thesledgehammer.groovymc.experimental.models.groovymc.variant1

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

class ModelEntryStaticBlock extends AbstractModelEntryStatic {

    private GroovyStaticModel groovyStaticItemModel;

    ModelEntryStaticBlock(String modelName, ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation) {
        this.groovyStaticModel = new GroovyStaticModel("block", modelName);
        this.groovyStaticItemModel = new GroovyStaticModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation);
    }

    ModelEntryStaticBlock(String modelName, String blockModelResourceLocation, String itemModelResourceLocation) {
        this.groovyStaticModel = new GroovyStaticModel("block", modelName);
        this.groovyStaticItemModel = new GroovyStaticModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation).add(itemModelResourceLocation);
    }

    ModelEntryStaticBlock(String modelName, String blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation, IBakedModel bakedModel) {
        this.groovyStaticModel = new GroovyStaticModel("block", modelName);
        this.groovyStaticItemModel = new GroovyStaticModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation, bakedModel).add(itemModelResourceLocation);
    }

    ModelEntryStaticBlock(String modelName, ModelResourceLocation blockModelResourceLocation, ModelResourceLocation itemModelResourceLocation, IBakedModel bakedModel) {
        this.groovyStaticModel = new GroovyStaticModel("block", modelName);
        this.groovyStaticItemModel = new GroovyStaticModel("item", modelName);
        ModelEntry.Register.add(blockModelResourceLocation, bakedModel).add(itemModelResourceLocation);
    }

    GroovyStaticModel getGroovyStaticBlockModel() {
        return groovyStaticModel;
    }

    GroovyStaticModel getGroovyStaticItemModel() {
        return groovyStaticItemModel;
    }
}
