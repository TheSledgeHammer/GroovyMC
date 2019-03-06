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

import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.ModelEntryBakery
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import net.minecraft.util.ResourceLocation

class ModelEntryStatic extends ModelEntryBakery<ModelEntry, TextureEntry> {

    GroovyBaseModel GROOVY_MODEL;
    private MutableQuad[][] quads;

    ModelEntryStatic(ModelEntryBakery MEB) {

    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {
        //get Textures from GROOVY_MODEL
    }

    @Override
    protected void onModelBake() {
        if(GROOVY_MODEL == null) {
            quads = null;
        }
    }

    @Override
    boolean hasBakedQuads() {
        return quads != null;
    }
}
