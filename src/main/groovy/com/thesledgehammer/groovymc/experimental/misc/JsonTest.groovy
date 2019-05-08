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

package com.thesledgehammer.groovymc.experimental.misc


import com.thesledgehammer.groovymc.client.model.GroovyBlockModel
import com.thesledgehammer.groovymc.client.model.ModelEntryStatic
import com.thesledgehammer.groovymc.config.Constants

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        GroovyBlockModel blockModel = new GroovyBlockModel("engine_base");

        //Model Elements
        blockModel.setModelElements("base");
        //blockModel.setModelElements("base_moving");
        blockModel.setModelElements("trunk");
        //blockModel.setModelElements("chamber");


        //Model Textures
        blockModel.setModelTextures("#trunk_blue");
        blockModel.setModelTextures("#trunk_green");
        blockModel.setModelTextures("#trunk_yellow");
        blockModel.setModelTextures("#trunk_red");
        blockModel.setModelTextures("#trunk_overheat");
        blockModel.setModelTextures("#trunk_black");
        blockModel.setModelTextures("#chamber");
        blockModel.setModelTextures("#back");
        blockModel.setModelTextures("#side");

        TextureEntry.Register.add("#trunk_blue")
        TextureEntry.Register.add("blocks/engine/trunk_green")

        //List<String> var = ListTools.FloatListToStringList(blockModel.getModelElements(1).To());
        //println VariableContext.AssignVariable("10.0", var, 1, "progress_size").getValue();

        TextureEntry texture = new TextureEntry.Register().build();
        ModelEntry model = new ModelEntry.Register().build();
        ModelEntry.Register.add(new ModelResourceLocation("models/block/engine_base"), null)
        ModelEntry.Register.add(new ModelResourceLocation("models/block/blockstat"), null)
        ModelEntryStatic MES = new ModelEntryStatic(blockModel);
    }


    static List<ResourceLocation> removeDuplicates(List<ResourceLocation> list) {
        List<ResourceLocation> newList = new ArrayList<>();

        for(ResourceLocation element : list) {
            if(!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    static def getValueFromList(List<Object> list, Object value) {
        int idx = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.contains(value)) {
                idx = list.indexOf(value);
            }
        }
        return list.get(idx);
    }
}