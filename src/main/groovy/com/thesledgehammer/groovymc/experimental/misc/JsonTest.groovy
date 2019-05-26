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
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableModel
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.ListTools

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        GroovyVariableModel blockModel = new GroovyVariableModel("block", "engine_base");

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
        List<String> var = getVariableTo(blockModel.getGroovysonModel(), 2);

        println setVariableFrom(blockModel.getGroovysonModel(), 2, "3", "progress_size")

        //println blockModel.getGroovysonModel().AssignVariableDouble("1", var, 1, "progress_size")
        //println getVariableTo(blockModel.getGroovysonModel(), 2);
    }

    static List<String> getVariableFrom(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).From());
        return var;
    }

    static List<String> getVariableTo(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).To());
        return var;
    }

    static VariableDouble[] setVariableFrom(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableFrom(groovysonModel, modelIndex);
        VariableDouble[] from = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                from[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                from[i] = groovysonModel.AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return from;
    }

    static VariableDouble[] setVariableTo(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableTo(groovysonModel, modelIndex);
        VariableDouble[] to = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                to[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                to[i] = groovysonModel.AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return to;
    }
}