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

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.json.GroovysonReader
import com.thesledgehammer.groovymc.utils.Log

//Work In Progress
//turns blockstate .jsons into readable objects
class GroovysonObjectState {

    private def obj; //raw Json Blockstate file
    private String name;

    GroovysonObjectState() {

    }

    GroovysonObjectState(String path, String modid, String resourceType, String fileName) {
        setJsonObject(path, modid, resourceType, fileName);
    }

    GroovysonObjectState(String path, String modid, String resource, String resourceObject, String fileName) {
        setJsonObject(path, modid, resource, resourceObject, fileName);
    }

    void setJsonObject(String path, String modid, String resourceType, String fileName) {
        setName(fileName)
        def file = GroovysonReader.JsonFile(path, modid, resourceType, name);
        def obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    void setJsonObject(String path, String modid, String resource, String resourceObject, String fileName) {
        setName(fileName);
        def file = GroovysonReader.JsonFile(path, modid, resource, resourceObject, name);
        def obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    def getJsonObjectState() {
        return obj;
    }

    def getVariant() {
        if(obj.variant == null) {
            Log.logError("${obj.variant} Isn't defined in ${getName()}");
            return null;
        }
        return obj.variant;
    }
}
