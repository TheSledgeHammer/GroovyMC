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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.config.Constants

import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableCuboid
import com.thesledgehammer.groovymc.experimental.jsons.ITextureGetter
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        GroovyVariableModel blockModel = new GroovyVariableModel("block", "engine_base");
        GroovyVariableModel itemModel = new GroovyVariableModel("item", "engine_base");
        //itemEngineBase
        //blockEngineBase

        //Model Elements
        blockModel.setModelElements("base");
        //blockModel.setModelElements("base_moving");
        //blockModel.setModelElements("trunk");
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
        TextureEntry.Register.add("#back").add("#side").build()


        GroovysonVariableCuboid vb = new GroovysonVariableCuboid(blockModel.getGroovysonModel().getRawModelParts());

        //VB.setFrom(blockModel.getGroovysonModel().getRawModelPart(0), "0");
        //println blockModel.getGroovysonModel().VariableTexture(blockModel.getGroovysonModel().getRawModelPart(0), EnumFacing.DOWN)
        println vb.toString()
    }

    MutableQuad[] bakePart(ArrayList<GroovysonObjectPart> modelParts, ITextureGetter spriteLookup) {
        List<MutableQuad> list = new ArrayList<>();
        GroovysonVariableCuboid gVariableCuboid = new GroovysonVariableCuboid(modelParts);
        for (GroovysonObjectPart part : modelParts) {
            gVariableCuboid.addQuad(part, list, spriteLookup);
        }
        for (JsonRule rule : rules) {
            if(rule.getWhen().getValue()) {
                rule.apply(list);
            }
        }
        return list.toArray(new MutableQuad[list.size()]);
    }
}