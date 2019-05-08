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

import com.thesledgehammer.groovymc.client.model.ModelEntryHolderRegistry

abstract class ModelEntryHolder extends ModelEntryBakery<ModelEntry, TextureEntry> {

    private ModelEntry modelEntry;
    private TextureEntry textureEntry;

    ModelEntryHolder() {
        modelEntry = ModelEntry.Instance();
        textureEntry = TextureEntry.Instance();
        ModelEntryHolderRegistry.ENTRY_HOLDERS().add(this);
    }

    ModelEntry ModelEntry() {
        return modelEntry;
    }

    TextureEntry TextureEntry() {
        return textureEntry;
    }
}
