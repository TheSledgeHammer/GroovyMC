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

import com.thesledgehammer.groovymc.api.json.GroovysonReader
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

    def getVariants() {
        if(obj.variants == null) {
            Log.logError("${obj.variants} Isn't defined in ${getName()}");
            return null;
        }
        return obj.variants;
    }

    def getVariantName(String name) {
        if(obj.variants.get(name) == null) {
            Log.logError("${name} is incorrect...!");
            return null;
        }
        return obj.variants.get(name);
    }

    ArrayList<String> getVariantsFromName(String name) {
        ArrayList<String> arrObj = new ArrayList<>();
        if(obj.variants.get(name) == null) {
            Log.logError("${name} is incorrect...!");
            return null;
        }
        for(int i = 0; i < obj.variants.get(name).size; i++) {
            arrObj.add(obj.variants.get(name).get(i));
        }
        return arrObj;
    }

    def getVariantModelProperties(String name, int index) {
        return getVariantsFromName(name).get(index).model;
    }

    def getVariantModelXRotationProperties(String name, int index) {
        return getVariantsFromName(name).get(index).x;
    }

    def getVariantModelYRotationProperties(String name, int index) {
        return getVariantsFromName(name).get(index).y;
    }

    def getVariantModelUVLockProperties(String name, int index) {
        return getVariantsFromName(name).get(index).uvlock;
    }

    def getVariantModelWeightProperties(String name, int index) {
        return getVariantsFromName(name).get(index).weight;
    }

    def getVariantModelBlock(String name, int index) {
        return getVariantsFromName(name).get(index).model;
    }

    def getVariantModelXRotationOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).x;
    }

    def getVariantModelYRotationOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).y;
    }

    def getVariantModelUVLockOnBlock(String name, int index) {
        return getVariantsFromName(name).get(index).uvlock;
    }

    def getMultipart() {
        if(obj.multipart == null) {
            Log.logError("${obj.multipart} Isn't defined in ${getName()}");
            return null;
        }
        return obj.multipart;
    }

    ArrayList<String> getMultipartWhen() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < obj.multipart.size; i++) {
            arrObj.add(obj.multipart.get(i).when);
        }
        return arrObj;
    }

    ArrayList<String> getMultipartWhenOR() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < obj.multipart.size; i++) {
            arrObj.add(obj.multipart.get(i).when.OR);
        }
        return arrObj;
    }

    def getMultipartWhenORFace(int index, String face) {
        return getMultipartWhenOR().get(index).getAt(face)
    }

    ArrayList<String> getMultipartApply() {
        ArrayList<String> arrObj = new ArrayList<>();
        for(int i = 0; i < obj.multipart.size; i++) {
            arrObj.add(obj.multipart.get(i).apply);
        }
        return arrObj;
    }
}
