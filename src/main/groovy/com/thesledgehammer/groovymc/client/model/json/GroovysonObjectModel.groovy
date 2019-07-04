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
import com.thesledgehammer.groovymc.utils.ListTools

abstract class GroovysonObjectModel extends GroovysonObject {

    //private GroovysonObject jsonObject;
    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();

    GroovysonObjectModel(String resourceObject, String fileName) {
        super(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
    }

    GroovysonObjectModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, "models", resourceObject, fileName);
    }

    //Texture Name & Location
    void setRawModelTextures(String textureName) {
        String texLocation = getTexturesByName(textureName);
        for(int i = 0; i < ListTools.StringToList(texLocation).size(); i++) {
            rawModelTexturesMap.put(textureName, ListTools.StringToList(texLocation).get(i));
        }
        ListTools.StringToList(texLocation).clear();
    }

    void setRawModelTextures(String textureName, int textureLayer) {
        String texLocation = getItemTextureLayer(textureLayer);
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
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }
}
