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

package com.thesledgehammer.groovymc.client.definitions

import net.minecraft.util.ResourceLocation

abstract class ModelEntryBakery<M extends ModelEntry, T extends TextureEntry> extends ModelEntryConsumer {

    private M modelEntry;
    private T textureEntry;

    ModelEntryBakery(GroovyDefinitionContext GDC) {
        super(GDC);
    }

    ModelEntryBakery(GroovyResourceDefinition resources, GroovyModelDefinition models) {
        super(resources, models);
    }

    ModelEntryBakery() {
        super();
    }

    private ModelEntryBakery(T textureEntry, M modelEntry, GroovyDefinitionContext GDC) {
        this(GDC);
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    private ModelEntryBakery(T textureEntry, M modelEntry, GroovyResourceDefinition resources, GroovyModelDefinition models) {
        this(resources, models);
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    private ModelEntryBakery(T textureEntry, M modelEntry) {
        this();
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    abstract boolean hasBakedQuads();

    abstract void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites);

    protected abstract void onModelBake();
}
