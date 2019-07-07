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

package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.api.json.GroovysonReader
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.ResourceLocation

/**
 * A ResourceLocation conversion class. Which Allows for converting files read from GroovysonReader to Minecraft's ResourceLocation file.
 * It Borrows from the Minecraft ResourceLocation class, but maintains GroovysonReader compatability. Providing methods relavent to GroovysonObject & GroovysonReader
 * Is initalized from within a GroovysonObject.
 */
class GroovyResourceLocation {

    private List<String> groovyResource;
    private def file;
    private def obj;

    GroovyResourceLocation(String path, String modid, String resourceType, String fileName) {
        String assetsPath = GroovysonReader.ResourcePath(path, modid, resourceType, fileName);
        String subPath = StringTools.SubString(assetsPath, modid);
        String GRP = StringTools.regexFirst(subPath, "/", ":");
        this.groovyResource = Split(modid, GRP);

        def file = GroovysonReader.JsonFile(path, modid, resourceType, fileName);
        obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    GroovyResourceLocation(String path, String modid, String resource, String resourceObject, String fileName) {
        String assetsPath = GroovysonReader.ResourcePath(path, modid, resource, resourceObject, fileName);
        String subPath = StringTools.SubString(assetsPath, modid);
        String GRP = StringTools.regexFirst(subPath, "/", ":");
        this.groovyResource = Split(modid, GRP);

        def file = GroovysonReader.JsonFile(path, modid, resource, resourceObject, fileName);
        obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    String getResourceDomain() {
        return groovyResource[0];
    }

    String getResourcePath() {
        return groovyResource[1];
    }

    ResourceLocation getResourceLocation() {
        return new ResourceLocation(getResourceDomain(), getResourcePath());
    }

    void setJsonObject(def file, def obj) {
        this.file = file;
        this.obj = obj;
    }

    def getJsonObject() {
        return obj;
    }

    static String[] Split(String modID, String toSplit) {
        String[] astring = [modID, toSplit];
        int i = toSplit.indexOf(58);
        if (i >= 0) {

            astring[1] = toSplit.substring(i + 1, toSplit.length());

            if (i > 1) {
                astring[0] = toSplit.substring(0, i);
            }
        }
        return astring;
    }

    @Override
    String toString() {
        return getResourceDomain() + ':' + getResourcePath();
    }
}
