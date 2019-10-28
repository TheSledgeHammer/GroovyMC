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
import com.thesledgehammer.groovymc.client.definitions.GroovyObjectModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.json.*
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.ResourceLocation

//Work In Progress: JsonRule
class GroovyVariableModel extends GroovysonObjectModel {

    private Map<String, JsonTexture> textureMap;
    private JsonRule[] rules;

    GroovyVariableModel(ResourceLocation resourceLocation) {
        super(resourceLocation);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(this), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(this));
        GDC.setTranslucentKey(new TranslucentKey(this));
        GDC.setSolidKey(new SolidKey(this));
        GDC.setCutoutMippedKey(new CutoutMippedKey(this));
        JsonTextureMap();

        List<JsonRule> rulesP = new ArrayList<>()
        for(int i = 0; i < JsonRules().size(); i++) {
            if(getRules() != null) {
                //rulesP.add(JsonRule.SetRules(GROOVY_MODEL));
            }
        }
       // this.rules = rulesP.toArray(new JsonRule[rulesP.size()]);
    }

    GroovyVariableModel(String resourceDomain, String resourcePath) {
        super(resourceDomain, resourcePath);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(this), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(this));
        GDC.setTranslucentKey(new TranslucentKey(this));
        GDC.setSolidKey(new SolidKey(this));
        GDC.setCutoutMippedKey(new CutoutMippedKey(this));
        JsonTextureMap();

        List<JsonRule> rulesP = new ArrayList<>()
        for(int i = 0; i < JsonRules().size(); i++) {
            if(getRules() != null) {
                //rulesP.add(JsonRule.SetRules(GROOVY_MODEL));
            }
        }
       // this.rules = rulesP.toArray(new JsonRule[rulesP.size()]);
    }

    GroovysonObjectPart getModelElements(int index) {
        return getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return getRawModelParts();
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
        List<String> temp = ListTools.StringToList(getRules().toString().substring(1));
        List<String> rulesP = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            rulesP.add(ListTools.removeBrackets(temp.get(i)));
        }
        return rulesP;
    }

    private void JsonTextureMap() {
        this.textureMap = new HashMap<>();
        for(int i = 0; i < getRawModelTextures().size(); i++) {
            if(StringTools.contains(getRawModelTexture(i), '=')) {
                int idx = getRawModelTexture(i).indexOf('=');
                String name = getRawModelTexture(i).substring(0, idx);
                String location = getRawModelTexture(i).substring(idx + 1);
                this.textureMap.put(name, new JsonTexture(location));
            }
        }
    }
}
