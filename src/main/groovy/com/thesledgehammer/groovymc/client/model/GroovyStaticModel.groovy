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
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.JsonTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation

class GroovyStaticModel extends GroovysonObjectModel {

    private Map<String, String> textureLookup;

    GroovyStaticModel(ResourceLocation resourceLocation) {
        super(resourceLocation);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(this), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(this));
        GDC.setTranslucentKey(new TranslucentKey(this));
        GDC.setSolidKey(new SolidKey(this));
        GDC.setCutoutMippedKey(new CutoutMippedKey(this));
        createTextureLookup();
    }

    GroovyStaticModel(String resourceDomain, String resourcePath) {
        super(resourceDomain, resourcePath)

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(this), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(this));
        GDC.setTranslucentKey(new TranslucentKey(this));
        GDC.setSolidKey(new SolidKey(this));
        GDC.setCutoutMippedKey(new CutoutMippedKey(this));
        createTextureLookup();
    }

    GroovysonObjectPart getModelElements(int index) {
        return getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return getRawModelParts();
    }

    Map<String, String> TextureLookup() {
        return textureLookup;
    }

    MutableQuad[] getMutableQuads(Direction face, TextureAtlasSprite sprite) {
        int size = getModelElements().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for (int i = 0; i < size; i++) {
            mutableQuads[i] = JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite);
        }
        return mutableQuads;
    }

    List<BakedQuad> addBakedQuadsToItem(Direction face, TextureAtlasSprite sprite) {
        int size = getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bakedQuads.add(JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite).toBakedItem());
        }
        return bakedQuads;
    }

    List<BakedQuad> addBakedQuadsToBlock(Direction face, TextureAtlasSprite sprite) {
        int size = getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bakedQuads.add(JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite).toBakedBlock());
        }
        return bakedQuads;
    }

    private void createTextureLookup() {
        this.textureLookup = new HashMap<>();
        for(int i = 0; i < getRawModelTextures().size(); i++) {
            if(StringTools.contains(getRawModelTexture(i), '=')) {
                int idx = getRawModelTexture(i).indexOf('=');
                String name = getRawModelTexture(i).substring(0, idx);
                String location = getRawModelTexture(i).substring(idx + 1);
                this.textureLookup.put(name, location);
            }
        }
    }
}