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

//Item & Block Models
//GroovysonModel does not utilise IBakedModel, it reads .jsons directly from a GroovysonObject and GroovysonObjectPart
class GroovysonModel extends GroovysonObject {

    private String resourceObject;
    private GroovysonObjectCache groovysonObjectCache;
    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();

    GroovysonModel(String resourceObject, String fileName) {
        setRawModel(resourceObject, fileName);
    }

    private void setRawModel(String resourceObject, String fileName) {
        setResourceObject(resourceObject);
        setJsonObject(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
    }

    private void setResourceObject(String resourceObject) {
        this.resourceObject = resourceObject;
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
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }

    void setObjectCacheByModelPart(int index) {
        groovysonObjectCache = new GroovysonObjectCache(this, getRawModelPart(index));
    }

    GroovysonObjectCache getObjectCache() {
        return groovysonObjectCache;
    }

    //Returns All BlockRenderLayer types and all the faces for a given Model Part
    String getRawModelPartRenderLayerTypes(int index, String renderLayer) {
        getRawModelPart(index).BlockRenderType(renderLayer)
        return getRawModelPart(index).BlockRenderType(renderLayer);
    }

    //Returns a BlockRenderLayer type and the faces it applies too
    ArrayList<String> getRawModelPartRenderLayerOfFaces(int index, String renderLayer) {
        return getRawModelPart(index).BlockRenderTypeFace(renderLayer);
    }
}
