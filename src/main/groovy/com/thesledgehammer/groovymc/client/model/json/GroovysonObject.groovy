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

import com.thesledgehammer.groovymc.api.client.json.GroovysonReader
import com.thesledgehammer.groovymc.client.model.ResourceLoader
import groovy.json.JsonException
import net.minecraft.util.ResourceLocation

class GroovysonObject {

    private def obj; //raw Json Model file
    private String name;
    private ResourceLocation resourceLocation;

    GroovysonObject() {

    }

    GroovysonObject(String path, ResourceLocation resourceLocation) {
        String fileName = GroovysonReader.ResourcePath(path, resourceLocation);
        this.resourceLocation = resourceLocation;

        ResourceLoader isr = new ResourceLoader();
        this.obj = GroovysonReader.JsonSlurpy(isr.startLoading(resourceLocation));
        isr.finishLoading();

       // this.obj = GroovysonReader.JsonSlurpy(fileName);
        this.name = GroovysonReader.getFileName();
    }

    GroovysonObject(String path, String resourceDomain, String resourcePath) {
        String fileName = GroovysonReader.ResourcePath(path, resourceDomain, resourcePath);
        this.resourceLocation = new ResourceLocation(resourceDomain, resourcePath);

       ResourceLoader isr = new ResourceLoader();
       this.obj = GroovysonReader.JsonSlurpy(isr.startLoading(resourceDomain, resourcePath));
       isr.finishLoading();

       // this.obj = GroovysonReader.JsonSlurpy(fileName);
        this.name = GroovysonReader.getFileName();
    }

    protected void deserializeParts(List<GroovysonObjectPart> objectParts) {
        for(String elem : obj.elements) {
            objectParts.add(new GroovysonObjectPart(this, elem));
        }
    }

    String getName() {
        return name;
    }

    ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    def getJsonObject() {
        return obj;
    }

    def getParent() {
        if(obj.parent == null) {
            return new JsonException("Parent isn't defined in ${getName()}")
        }
        return obj.parent;
    }

    def getTextures() {
        return obj.textures;
    }

    def getTexturesByName(String name) {
        return obj.textures.get(name);
    }

    def getItemTextureLayer(int index) {
        return obj.textures.getAt("layer${index}");
    }

    def getElements() {
        return obj.elements;
    }

    def getElementPart(int index) {
        return obj.elements.get(index);
    }

    boolean getShade() {
        return obj.shade;
    }

    def getVariables() {
        return obj.variables;
    }

    def getVariableByName(String variableName) {
        return getVariables().get(variableName);
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

    boolean AmbientOcclusion() {
        return obj.ambientocclusion;
    }

    //Translation Rotation Scale Rotation(TRSR)
    def Display() {
        return obj.display;
    }

    def DisplayName(String name) {
        if(obj.display.get(name) == null) {
            return new JsonException("${name} is incorrect...!");
        }
        return obj.display.get(name);
    }

    ArrayList<Float> Translation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new JsonException("${name} is incorrect...!")
            //return null;
        }
        if(obj.display.get(name).translation == null) {
            throw new JsonException("${name} does not contain Translation...!");
            //return null;
        }
        for(int i = 0; i < obj.display.get(name).translation.size; i++) {
            arrObj.add(i, obj.display.get(name).translation.get(i));
        }
        return arrObj;
    }

    ArrayList<Float> Rotation(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new JsonException("${name} is incorrect...!");
            //return null;
        }
        if(obj.display.get(name).rotation == null) {
            throw new JsonException("${name} does not contain Rotation...!");
            //return null;
        }
        for(int i = 0; i < obj.display.get(name).rotation.size; i++) {
            arrObj.add(i, obj.display.get(name).rotation.get(i));
        }
        return arrObj;
    }

    ArrayList<Float> Scale(String name) {
        ArrayList<Float> arrObj = new ArrayList<>();
        if(obj.display.get(name) == null) {
            throw new JsonException("${name} is incorrect...!");
            //return null;
        }
        if(obj.display.get(name).scale == null) {
            throw new JsonException("${name} does not contain Scale...!");
            //return null;
        }
        for(int i = 0; i < obj.display.get(name).scale.size; i++) {
            arrObj.add(i, obj.display.get(name).scale.get(i));
        }
        return arrObj;
    }

    //Used in Item Models Only
    def Overrides() {
        return obj.overrides;
    }

    def OverridesPredicate() {
        return obj.overrides.predicate;
    }

    def OverridesModel() {
        return obj.overrides.model;
    }
}
