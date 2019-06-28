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

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableCuboid
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModelVariable
import com.thesledgehammer.groovymc.experimental.jsons.ITextureGetter
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

//Work In Progress: JsonRule[] rules To be completed
class GroovyVariableModel {

    private GroovysonObjectModelVariable GROOVY_MODEL;
    private GroovyDefinitionContext GDC;
    private Map<String, JsonTexture> textureMap = new HashMap<>();
    private JsonRule[] rules;

    GroovyVariableModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonObjectModelVariable(resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovyVariableModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonObjectModelVariable(resourceDirectory, modID, resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovysonObjectModelVariable getGroovysonModel() {
        return GROOVY_MODEL;
    }

    void setModelElements(String name) {
        GROOVY_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
        TextureEntry.Register.add(name).build();
    }

    GroovysonObjectPart getModelElements(int index) {
        return GROOVY_MODEL.getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return GROOVY_MODEL.getRawModelParts();
    }

    String getModelTextures(String textureName) {
        return GROOVY_MODEL.getRawModelTextures().get(textureName);
    }
    
    Map<String, String> getModelTextures() {
        return GROOVY_MODEL.getRawModelTextures();
    }

    String getModelElementTextures(int index, EnumFacing face) {
        return GROOVY_MODEL.getRawModelParts().get(index).TextureFace(face);
    }

    JsonTexture getJsonTexture(String lookup) {
        return textureMap.get(lookup);
    }

    void setRenderKeysDefintion(GroovysonObjectModelVariable GROOVY_MODEL) {
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
    }

    void JsonTextureMap() {
        String[] name = getModelTextures().keySet().toArray();
        String[] location = getModelTextures().values().toArray();

        for(int i = 0; i < getModelTextures().size(); i++) {
            textureMap.put(name[i], new JsonTexture(location[i]));
        }
    }

    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        JsonTextureMap();
        for (Map.Entry<String, JsonTexture> entry : textureMap.entrySet()) {
            JsonTexture lookup = entry.getValue();
            String location = lookup.location;
            if (location.startsWith("#") || location.startsWith("~")) {
                continue;
            }
            ResourceLocation textureLoc = new ResourceLocation(location);
            toRegisterSprites.add(textureLoc);
        }
    }

    MutableQuad[] bakePart(ArrayList<GroovysonObjectPart> modelParts, ITextureGetter spriteLookup) {
        List<MutableQuad> list = new ArrayList<>();
        GroovysonVariableCuboid cuboid = new GroovysonVariableCuboid(modelParts);
        for (GroovysonObjectPart part : modelParts) {
            cuboid.addQuad(part, list, spriteLookup);
        }
        for (JsonRule rule : rules) {
            if(rule.getWhen().getValue()) {
                rule.apply(list);
            }
        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    //Gets rules as a list of strings not as jsonrules
    private List<String> JsonRules() {
        List<String> temp = ListTools.StringToList(GROOVY_MODEL.getRules().toString().substring(1));
        List<String> rulesP = new ArrayList<>();
        for(int i = 0; i < temp.size(); i++) {
            rulesP.add(ListTools.removeBrackets(temp.get(i)));
        }
        return rulesP;
    }
}
