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
package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.client.model.GroovyVariableModel
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

//Work In Progress: JsonRules
abstract class AbstractModelEntryVariable extends ModelEntryHolder {

    protected GroovyVariableModel groovyVariableModel;
    private boolean unseen = true;

    @Override
    boolean hasBakedQuads() {
        return groovyVariableModel != null;
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        groovyVariableModel = null;
        if(groovyVariableModel != null) {
            groovyVariableModel.onTextureStitchPre(toRegisterSprites);
        }
    }

    @Override
    void onModelBake() {

    }

    ModelUtil.TexturedFace TexturedLookup(String lookup) {
        int attempts = 0;
        JsonTexture texture = new JsonTexture(lookup);
        TextureAtlasSprite sprite;
        while (texture.location.startsWith("#") && attempts < 10) {
            JsonTexture tex = groovyVariableModel.getJsonTexture(lookup);
            if(tex == null) {
                break;
            } else {
                texture = texture.inParent(tex);
            }
            attempts++;
        }
        lookup = texture.location;
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        face.sprite = sprite;
        face.faceData = texture.faceData;

        return face;
    }

    MutableQuad[] getCutoutQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return groovyVariableModel.bakePart(GroovyDefinitionContext.Instance().getCutoutKey().getCutoutModelElements(), this.&TexturedLookup);
    }

    MutableQuad[] getTranslucentQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return groovyVariableModel.bakePart(GroovyDefinitionContext.Instance().getTranslucentKey().getTranslucentModelElements(), this.&TexturedLookup);
    }

    MutableQuad[] getSolidQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return groovyVariableModel.bakePart(GroovyDefinitionContext.Instance().getSolidKey().getSolidModelElements(), this.&TexturedLookup);
    }

    MutableQuad[] getCutoutMippedQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return groovyVariableModel.bakePart(GroovyDefinitionContext.Instance().getCutoutMippedKey().getCutoutMippedModelElements(), this.&TexturedLookup);
    }
}
