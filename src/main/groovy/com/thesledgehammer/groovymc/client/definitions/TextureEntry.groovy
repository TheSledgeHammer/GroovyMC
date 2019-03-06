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

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

abstract class TextureEntry extends ModelEntryConsumer {

    TextureEntry(String sprite) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(sprite);
        this.GroovyDefinitionContext().setResourceLocation(sprite);
    }

    TextureEntry(ResourceLocation spriteLocation) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(spriteLocation);
        this.GroovyDefinitionContext().setResourceLocation(spriteLocation);
    }

    TextureEntry(String modID, String baseName) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        this.GroovyDefinitionContext().setResourceLocation(modID, baseName);
    }

    TextureEntry(String modID, String type, String baseName) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        this.GroovyDefinitionContext().setCustomResourceLocation(modID, type, baseName);
    }

    ResourceLocation getResourceLocation() {
        return this.GroovyDefinitionContext().getResourceLocation()
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return this.GroovyDefinitionContext().getTextureAtlasSprite()
    }

    static class Register extends TextureEntry {

        private static List<TextureEntry> TEXTURE_ENTRIES = new LinkedList<>();

        private Register(String sprite) {
            super(sprite)
        }

        private Register(ResourceLocation spriteLocation) {
            super(spriteLocation)
        }

        private Register(String modID, String baseName) {
            super(modID, baseName)
        }

        private Register(String modID, String type, String baseName) {
            super(modID, type, baseName)
        }

        static void add(String sprite) {
            TEXTURE_ENTRIES.add(new Register(sprite));
        }

        static void add(ResourceLocation spriteLocation) {
            TEXTURE_ENTRIES.add(new Register(spriteLocation));
        }

        static void add(String modID, String baseName) {
            TEXTURE_ENTRIES.add(new Register(modID, baseName));
        }

        static void add(String modID, String type, String baseName) {
            TEXTURE_ENTRIES.add(new Register(modID, type, baseName));
        }

        static List<TextureEntry> getTextureEntries() {
            return TEXTURE_ENTRIES;
        }
    }
}
