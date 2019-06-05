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

import buildcraft.api.enums.EnumPowerStage
import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.client.model.json.GroovysonAbstractModel
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableFaceUV
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel
import com.thesledgehammer.groovymc.experimental.multiblocks.IBlockHolder
import com.thesledgehammer.groovymc.experimental.multiblocks.MultiBlock
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.block.Block
import net.minecraft.util.EnumFacing

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        GroovyVariableModel blockModel = new GroovyVariableModel("block", "engine_base");

        //Model Elements
        blockModel.setModelElements("base");
        blockModel.setModelElements("base_moving");
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

        //println blockModel.getGroovysonModel().AssignVariableDouble("1", var, 1, "progress_size")
        //GroovysonVariableFaceUV g = new GroovysonVariableFaceUV(blockModel.getGroovysonModel(), 0, "2.0", "progress_size");

        Map<EnumPowerStage, Long> map = blockModel.getGroovysonModel().LightFromVariable("stage_light", EnumPowerStage);
        for(EnumPowerStage stage : EnumPowerStage.VALUES) {
            if(map.get(stage.name()).equals(null)) {
                map.putAt(stage.name(), 0);

            }
            //println StringValueToEnumValue(EnumPowerStage, map.get(stage.name()).toString(), map.values().toString())
        }

        println blockModel.getGroovysonModel().Light(2)
    }

    static String StringValueToEnumValue(Class<Enum> enumClass, String value, String enumValue) {
        for(int i = 0; i < enumClass.enumConstants.length; i++) {
            if(enumValue.equalsIgnoreCase(enumClass.enumConstants[i].name())) {
                return enumValue.toUpperCase();
            }
        }
        return null;
    }
}