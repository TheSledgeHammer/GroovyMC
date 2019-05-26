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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectCache
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing

abstract class GroovysonAbstractModel extends GroovysonObject {

    private String resourceObject;
    private GroovysonObjectCache groovysonObjectCache;
    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();

    GroovysonAbstractModel(String resourceObject, String fileName) {
        setRawModel(resourceObject, fileName);
    }

    GroovysonAbstractModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        setRawModel(resourceDirectory, modID, resourceObject, fileName);
    }

    private void setRawModel(String resourceObject, String fileName) {
        setResourceObject(resourceObject);
        setJsonObject(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
    }

    private void setRawModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        setResourceObject(resourceObject);
        setJsonObject(resourceDirectory, modID, "models", resourceObject, fileName);
    }

    private void setResourceObject(String resourceObject) {
        this.resourceObject = resourceObject;
    }

    String getResourceObject() {
        return resourceObject
    }

    //Texture Name & Location
    void setRawModelTextures(String textureName) {
        String texLocation = super.getTexturesByName(textureName);
        for(int i = 0; i < ListTools.StringToList(texLocation).size(); i++) {
            rawModelTexturesMap.put(textureName, ListTools.StringToList(texLocation).get(i));
        }
        ListTools.StringToList(texLocation).clear();
    }

    HashMap<String, String> getRawModelTextures() {
        return rawModelTexturesMap;
    }

    String getTexturesLocation(String textureName) {
        return rawModelTexturesMap.get(textureName);
    }

    void setRawModelParts(String partName) {
        groovysonObjectParts.add(new GroovysonObjectPart(this, partName));
        for(GroovysonObjectPart part : groovysonObjectParts) {
            setObjectCache(part);
        }
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }

    private void setObjectCache(GroovysonObjectPart partName) {
        groovysonObjectCache = new GroovysonObjectCache(this, partName);
    }

    GroovysonObjectCache getObjectCache() {
        return groovysonObjectCache;
    }

    //Get Element variables at index by face name
    HashMap<String, EnumFacing> FaceMap(int index) {
        HashMap<String, EnumFacing> arrMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            arrMap.put(face.getName(), getRawModelPart(index).Facing(face));
        }
        return arrMap;
    }
}
