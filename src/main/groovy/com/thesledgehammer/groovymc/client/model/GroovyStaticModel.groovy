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
import com.thesledgehammer.groovymc.client.definitions.GroovyObjectModelDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModelStatic
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.utils.JsonTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

class GroovyStaticModel {

    private GroovysonObjectModelStatic GROOVY_MODEL;
    private Map<String, String> textureLookup;

    GroovyStaticModel(ResourceLocation resourceLocation) {
        this.GROOVY_MODEL = new GroovysonObjectModelStatic(resourceLocation);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(GROOVY_MODEL), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
        createTextureLookup();
    }

    GroovyStaticModel(String resourceDomain, String resourcePath) {
        this.GROOVY_MODEL = new GroovysonObjectModelStatic(resourceDomain, resourcePath);

        GroovyDefinitionContext GDC = new GroovyDefinitionContext(new GroovyRenderDefinition(GROOVY_MODEL), new GroovyObjectModelDefinition());
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
        createTextureLookup();
    }

    GroovysonObjectModelStatic getGroovysonModel() {
        return GROOVY_MODEL;
    }

    GroovysonObjectPart getModelElements(int index) {
        return GROOVY_MODEL.getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return GROOVY_MODEL.getRawModelParts();
    }

    Map<String, String> TextureLookup() {
        return textureLookup
    }

    MutableQuad[] getMutableQuads(EnumFacing face, TextureAtlasSprite sprite) {
        int size = getModelElements().size();
        MutableQuad[] mutableQuads = new MutableQuad[size];
        for (int i = 0; i < size; i++) {
            mutableQuads[i] = JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite);
        }
        return mutableQuads;
    }

    List<BakedQuad> addBakedQuadsToItem(EnumFacing face, TextureAtlasSprite sprite) {
        int size = getGroovysonModel().getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bakedQuads.add(JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite).toBakedItem());
        }
        return bakedQuads;
    }

    List<BakedQuad> addBakedQuadsToBlock(EnumFacing face, TextureAtlasSprite sprite) {
        int size = getGroovysonModel().getRawModelTextures().size();
        List<BakedQuad> bakedQuads = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            bakedQuads.add(JsonTools.QuadAFace(getModelElements(), face, i).toQuad(sprite).toBakedBlock());
        }
        return bakedQuads;
    }

    private void createTextureLookup() {
        for(int i = 0; i < GROOVY_MODEL.getRawModelTextures().size(); i++) {
            if(StringTools.contains(GROOVY_MODEL.getRawModelTexture(i), '=')) {
                int idx = GROOVY_MODEL.getRawModelTexture(i).indexOf('=');
                String name = GROOVY_MODEL.getRawModelTexture(i).substring(0, idx);
                String location = GROOVY_MODEL.getRawModelTexture(i).substring(idx + 1);
                this.textureLookup.put(name, location);
            }
        }
    }
}