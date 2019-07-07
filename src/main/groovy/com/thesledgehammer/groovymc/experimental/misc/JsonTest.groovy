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
import com.thesledgehammer.groovymc.client.model.GroovyVariableModel
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.models.GroovyResourceLocation
import com.thesledgehammer.groovymc.utils.StringTools

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
        GroovyResourceLocation path = new GroovyResourceLocation(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", "block", "engine_base");
        println path.getResourceLocation()
    }

    static String ResourceLocation(String path, String modid, String resourceType, String fileName) {
        String assetsPath = path + "/" + modid + "/" + resourceType + "/" + fileName;
        String subPath = StringTools.SubString(assetsPath, modid);
        String resourceLoc = StringTools.regexFirst(subPath, "/", ":");
        return resourceLoc;
    }

    static String ResourceLocation(String path, String modid, String resourceType, String resourceObject, String fileName) {
        String assetsPath = path + "/" + modid + "/" + resourceType + "/" + resourceObject + "/" + fileName;
        String subPath = StringTools.SubString(assetsPath, modid);
        String resourceLoc = StringTools.regexFirst(subPath, "/", ":");
        return resourceLoc;
    }
}

//Needs getTextureMapBlocks