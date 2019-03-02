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

package com.thesledgehammer.groovymc.client.model.json


import com.thesledgehammer.groovymc.utils.Log

class GroovysonObject {

    private def obj; //raw Json Model file
    private String name;

    GroovysonObject() {

    }

    GroovysonObject(String path, String modid, String resourceType, String fileName) {
        setJsonObject(path, modid, resourceType, fileName);
    }

    GroovysonObject(String path, String modid, String resource, String resourceObject, String fileName) {
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

    def getJsonObject() {
        return obj;
    }

    def getParent() {
        if(obj.parent == null) {
            //Log.logError(obj.parent + " Isn't defined in " + getName());
            return null;
        }
        return obj.parent;
    }

    def getTextures() {
        return obj.textures;
    }

    def getTexturesByName(String name) {
        return obj.textures.get(name);
    }

    def getTexturesLayer(int index) {
        return obj.textures.get("layer" + index);
    }

    def getElements() {
        return obj.elements;
    }

    def getElementPart(int index) {
        return obj.elements.get(index);
    }

    def getRenderLayer(int index, String render) {
        return obj.elements.get(index).getAt(render);
    }

    private boolean getShade() {
        return obj.shade;
    }

    boolean Shade(boolean fallback) {
        if(getShade() == null) {
            return fallback;
        }
        return getShade();
    }

    def getVariables() {
        return obj.variables;
    }

    def getVariableByName(String variableName) {
        return getVariables()[variableName];
    }

    def getRules() {
        return obj.rules;
    }

    def getRulesByName(String ruleName) {
        return getRules()[ruleName];
    }

    def getValues() {
        return obj.values;
    }

    def getValueByName(def valueName) {
        return obj.values.get(valueName);
    }

    //Translation Rotation Scale Rotation(TRSR)
    def Display() {
        return obj.display;
    }

    def DisplayName(String name) {
        if(obj.display.get(name) == null) {
            Log.logError(name + " is incorrect...!");
            return null;
        }
        return obj.display.get(name);
    }

    ArrayList<Float> Translation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            Log.logError(name + " is incorrect...!");
            return null;
        }
        if(obj.display.get(name).translation == null) {
            Log.logError(name + " does not contain Translation...!");
            return null;
        }
        for(int i = 0; i < obj.display.get(name).translation.size; i++) {
            arrObj.add(i, obj.display.get(name).translation.get(i));
        }
        return arrObj;
    }

    float[] translation(String name) {
        float[] translation = Translation(name);
        return translation;
    }

    ArrayList<Float> Rotation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            Log.logError(name + " is incorrect...!");
            return null;
        }
        if(obj.display.get(name).rotation == null) {
            Log.logError(name + " does not contain Rotation...!");
            return null;
        }
        for(int i = 0; i < obj.display.get(name).rotation.size; i++) {
            arrObj.add(i, obj.display.get(name).rotation.get(i));
        }
        return arrObj;
    }

    float[] rotation(String name) {
        float[] rotation = Rotation(name);
        return rotation;
    }

    ArrayList<Float> Scale(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            Log.logError(name + " is incorrect...!");
            return null;
        }
        if(obj.display.get(name).scale == null) {
            Log.logError(name + " does not contain Scale...!");
            return null;
        }
        for(int i = 0; i < obj.display.get(name).scale.size; i++) {
            arrObj.add(i, obj.display.get(name).scale.get(i));
        }
        return arrObj;
    }

    float[] scale(String name) {
        float[] scale = Scale(name);
        return scale;
    }
}
