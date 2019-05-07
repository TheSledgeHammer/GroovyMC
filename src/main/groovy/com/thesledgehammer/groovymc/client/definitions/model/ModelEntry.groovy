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

import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation

@Deprecated //Being Replaced with ModelEntryV2
abstract class ModelEntry extends ModelEntryConsumer {

    ModelEntry(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
        GroovyDefinitionContext().setModelResourceLocation(modelLocation);
        GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    ModelEntry(String modelLocation, IBakedModel bakedModel) {
        GroovyDefinitionContext().setModelResourceLocation(modelLocation);
        GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    ModelEntry(String type, String modelLocation, IBakedModel bakedModel) {
        GroovyDefinitionContext().setCustomModelResourceLocation(type, modelLocation);
        GroovyDefinitionContext().setIBakedModel(bakedModel);
    }

    static class Register extends ModelEntry {

        private static List<ModelEntry> MODEL_ENTRIES = new LinkedList<>();

        private Register(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
            super(modelLocation, bakedModel)
        }

        private Register(String modelLocation, IBakedModel bakedModel) {
            super(modelLocation, bakedModel)
        }

        private Register(String type, String modelLocation, IBakedModel bakedModel) {
            super(type, modelLocation, bakedModel)
        }

        static void add(ModelResourceLocation modelLocation, IBakedModel bakedModel) {
            MODEL_ENTRIES.add(new Register(modelLocation, bakedModel));
        }

        static void add(String modelLocation, IBakedModel bakedModel) {
            MODEL_ENTRIES.add(new Register(modelLocation, bakedModel));
        }

        static void add(String type, String modelLocation, IBakedModel bakedModel) {
            MODEL_ENTRIES.add(new Register(type, modelLocation, bakedModel));
        }

        static List<ModelEntry> getModelEntries() {
            return MODEL_ENTRIES;
        }
    }
}
