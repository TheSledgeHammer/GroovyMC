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

package com.thesledgehammer.groovymc.client.model


import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovysonModelDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.json.*
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

//Work In Progress: JsonRule
class GroovyVariableModel {

    private GroovysonObjectModelVariable GROOVY_MODEL;
    private Map<String, JsonTexture> textureMap = new HashMap<>();
    private JsonRule[] rules;

    GroovyVariableModel(ResourceLocation resourceLocation) {
        this.GROOVY_MODEL = new GroovysonObjectModelVariable(resourceLocation);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(GROOVY_MODEL), new GroovysonModelDefinition());
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));

        List<JsonRule> rulesP = new ArrayList<>()
        for(int i = 0; i < JsonRules().size(); i++) {
            if(GROOVY_MODEL.getRules() != null) {
                //rulesP.add(JsonRule.SetRules(GROOVY_MODEL));
            }
        }
       // this.rules = rulesP.toArray(new JsonRule[rulesP.size()]);
    }

    GroovyVariableModel(String resourceDomain, String resourcePath) {
        this.GROOVY_MODEL = new GroovysonObjectModelVariable(resourceDomain, resourcePath);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(GROOVY_MODEL), new GroovysonModelDefinition());
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));

        List<JsonRule> rulesP = new ArrayList<>()
        for(int i = 0; i < JsonRules().size(); i++) {
            if(GROOVY_MODEL.getRules() != null) {
                //rulesP.add(JsonRule.SetRules(GROOVY_MODEL));
            }
        }
       // this.rules = rulesP.toArray(new JsonRule[rulesP.size()]);
    }

    GroovysonObjectModelVariable getGroovysonModel() {
        return GROOVY_MODEL;
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
        JsonTextureMap();
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

    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
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
            cuboid.addQuads(part, list, spriteLookup);
        }
        for (JsonRule rule : rules) {
            if (rule.getWhen().getValue()) {
                rule.apply(list);
            }
        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    //Gets rules as a list of Strings, to determine number of rules
    private List<String> JsonRules() {
        List<String> temp = ListTools.StringToList(GROOVY_MODEL.getRules().toString().substring(1));
        List<String> rulesP = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            rulesP.add(ListTools.removeBrackets(temp.get(i)));
        }
        return rulesP;
    }

    private void JsonTextureMap() {
        String[] name = getModelTextures().keySet().toArray();
        String[] location = getModelTextures().values().toArray();

        for(int i = 0; i < getModelTextures().size(); i++) {
            textureMap.put(name[i], new JsonTexture(location[i]));
        }
    }
}
