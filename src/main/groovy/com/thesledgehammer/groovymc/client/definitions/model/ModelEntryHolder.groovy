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

package com.thesledgehammer.groovymc.client.definitions.model

import net.minecraft.util.ResourceLocation

abstract class ModelEntryHolder extends ModelEntryBakery<ModelEntry, TextureEntry> {

    private final ResourceLocation modelLocation;

    ModelEntryHolder(ResourceLocation modelLocation) {
        this.modelLocation = modelLocation;
        ModelEntry.Instance();
        TextureEntry.Instance();
        ModelEntryHolderManager.Instance().ENTRY_HOLDERS().add(this);
    }

    ModelEntryHolder(String resourceDomain, String resourcePath) {
        this(new ResourceLocation(resourceDomain, resourcePath))
        ModelEntry.Instance();
        TextureEntry.Instance();
        ModelEntryHolderManager.Instance().ENTRY_HOLDERS().add(this);
    }
}
