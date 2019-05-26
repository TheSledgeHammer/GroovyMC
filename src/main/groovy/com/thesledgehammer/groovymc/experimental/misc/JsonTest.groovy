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

import com.thesledgehammer.groovymc.config.Constants

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        /*GroovyVariableModel blockModel = new GroovyVariableModel("block", "engine_base");

        //Model Elements
        blockModel.setModelElements("base");
        //blockModel.setModelElements("base_moving");
        blockModel.setModelElements("trunk");
        blockModel.setModelElements("chamber");


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
/*
         TextureEntry.Register
                 .add("#trunk_blue")
                 .add("blocks/engine/trunk_green")
                 .build();

         */
        //println VariableContext.AssignVariable("10.0", var, 1, "progress_size").getValue();
    }
}