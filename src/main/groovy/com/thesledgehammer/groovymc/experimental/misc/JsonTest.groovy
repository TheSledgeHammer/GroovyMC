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
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolderManager
import com.thesledgehammer.groovymc.config.Constants

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

        //ModelEntryHolderVariable entry = new ModelEntryHolderVariable("groovymc", "models/block/engine_base");
        //GroovyVariableModel blockModel = entry.getGroovyVariableModel();
        //GroovysonObject obj = new GroovysonObject("src/main/resources/assets/", "groovymc", "models/block/engine_base");
        //GroovysonObjectPart part = new GroovysonObjectPart(obj, 0);

        println ModelEntryHolderManager.Instance().TEXTURE_ENTRIES
    }
}