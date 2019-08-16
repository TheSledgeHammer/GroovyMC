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
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.ResourceLocation

class GroovysonObjectModel extends GroovysonObject {

    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private Map<String, String> rawModelTexturesMap = new HashMap<>();

    GroovysonObjectModel(ResourceLocation resourceLocation) {
        super(GroovyLoader.Instance().getModResourceDirectory(), resourceLocation);
        deserializeParts(this.groovysonObjectParts);
        deserializeTexturesToMap();
    }

    GroovysonObjectModel(String resourceDomain, String resourcePath) {
        super(GroovyLoader.Instance().getModResourceDirectory(), resourceDomain, resourcePath);
        deserializeParts(this.groovysonObjectParts);
        deserializeTexturesToMap();
    }

    //Texture Name & Location
    private void deserializeTexturesToMap() {
        List<String> modelTextures = new ArrayList<>();
        deserializeTextures(modelTextures);
        for(int i = 0; i < modelTextures.size(); i++) {
            if(StringTools.contains(modelTextures.get(i), '=')) {
                int idx = modelTextures.get(i).indexOf('=');
                String location = modelTextures.get(i).substring(idx + 1);
                String name = modelTextures.get(i).substring(0, idx);
                this.rawModelTexturesMap.put(name, location);
            }
        }
    }

    Map<String, String> getRawModelTextures() {
        return rawModelTexturesMap;
    }

    String getTexturesLocation(String textureName) {
        return rawModelTexturesMap.get(textureName);
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }

    /*
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
*/
}
