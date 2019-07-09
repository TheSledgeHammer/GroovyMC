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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.json.GroovysonReader
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.ResourceLocation

/**
 * GroovyResourceLocation Extends the Minecraft ResourceLocation class, by adding constructors to hook into GroovysonReader and by extension GroovysonObject.
 * Is initalized from within a GroovysonObject.
 */
class GroovyResourceLocation extends ResourceLocation {

    //private List<String> groovyResource;
    private def file;
    private def obj;

    protected GroovyResourceLocation(int unused, String... resourceName) {
        super(unused, resourceName);
    }

    GroovyResourceLocation(String resourceObject, String fileName) {
        this(0, Split(GroovyLoader.Instance().getModID(),
                StringTools.regexFirst(StringTools.SubString(GroovysonReader.ResourcePath(GroovyLoader.Instance().getModResourceDirectory(),
                        GroovyLoader.Instance().getModID(), "models", resourceObject, fileName),
                        GroovyLoader.Instance().getModID()), "/", ":")));

        /*
        String assetsPath = GroovysonReader.ResourcePath(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
        String subPath = StringTools.SubString(assetsPath, GroovyLoader.Instance().getModID());
        String GRP = StringTools.regexFirst(subPath, "/", ":");
        this.groovyResource = Split(GroovyLoader.Instance().getModID(), GRP);
        */

        def file = GroovysonReader.JsonFile(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
        def obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    GroovyResourceLocation(String path, String modid, String resourceType, String fileName) {
        this(0, Split(modid, StringTools.regexFirst(StringTools.SubString(GroovysonReader.ResourcePath(path, modid, resourceType, fileName), modid), "/", ":")));

        /*
        String assetsPath = GroovysonReader.ResourcePath(path, modid, resourceType, fileName);
        String subPath = StringTools.SubString(assetsPath, modid);
        String GRP = StringTools.regexFirst(subPath, "/", ":");
        this.groovyResource = Split(modid, GRP);
        */

        def file = GroovysonReader.JsonFile(path, modid, resourceType, fileName);
        def obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }

    GroovyResourceLocation(String path, String modid, String resource, String resourceObject, String fileName) {
        this(0, Split(modid, StringTools.regexFirst(StringTools.SubString(GroovysonReader.ResourcePath(path, modid, resource, resourceObject, fileName), modid), "/", ":")));

        /*
        String assetsPath = GroovysonReader.ResourcePath(path, modid, resource, resourceObject, fileName);
        String subPath = StringTools.SubString(assetsPath, modid);
        String GRP = StringTools.regexFirst(subPath, "/", ":");
        this.groovyResource = Split(modid, GRP);
        */

        def file = GroovysonReader.JsonFile(path, modid, resource, resourceObject, fileName);
        def obj = GroovysonReader.JsonSlurpy(file);
        this.obj = obj;
    }
/*
    String getResourceDomain() {
        return groovyResource[0];
    }

    String getResourcePath() {
        return groovyResource[1];
    }
    */

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
