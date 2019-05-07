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

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

@Deprecated //Being Replaced with TextureEntryV2
abstract class TextureEntry extends ModelEntryConsumer {

    private List<ResourceLocation> resourceLocationList = GroovyDefinitionContext().getResourceLocations();
    private List<TextureAtlasSprite> textureAtlasSpriteList = GroovyDefinitionContext().getTextureAtlasSprites();

    TextureEntry(String sprite) {
        GroovyDefinitionContext().setTextureAtlasSprite(sprite);
        GroovyDefinitionContext().setResourceLocation(sprite);
    }

    TextureEntry(ResourceLocation spriteLocation) {
        GroovyDefinitionContext().setTextureAtlasSprite(spriteLocation);
        GroovyDefinitionContext().setResourceLocation(spriteLocation);
    }

    TextureEntry(String modID, String baseName) {
        GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        GroovyDefinitionContext().setResourceLocation(modID, baseName);
    }

    TextureEntry(String modID, String type, String baseName) {
        GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        GroovyDefinitionContext().setCustomResourceLocation(modID, type, baseName);
    }

    TextureAtlasSprite getTextureAtlasSprite(int index) {
   //     textureAtlasSpriteList.add(GroovyDefinitionContext().getTextureAtlasSprite())
        return GroovyDefinitionContext().getTextureAtlasSprites().get(index)
    }

    ResourceLocation getResourceLocation(int index) {
     //   resourceLocationList.add(GroovyDefinitionContext().getResourceLocation())
        return GroovyDefinitionContext().getResourceLocations().get(index)
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return GroovyDefinitionContext().getTextureAtlasSprites();
    }

    List<ResourceLocation> getResourceLocations() {
        return GroovyDefinitionContext().getResourceLocations();
    }

    static class Register extends TextureEntry {

        private static List<TextureEntry> TEXTURE_ENTRIES = new LinkedList<>();

        private Register(String sprite) {
            super(sprite)
            //TEXTURE_ENTRIES.add(this);
        }

        private Register(ResourceLocation spriteLocation) {
            super(spriteLocation)
           // TEXTURE_ENTRIES.add(this);
        }

        private Register(String modID, String baseName) {
            super(modID, baseName)
           // TEXTURE_ENTRIES.add(this);
        }

        private Register(String modID, String type, String baseName) {
            super(modID, type, baseName)
            //TEXTURE_ENTRIES.add(this);
        }

        static Register add(String sprite) {
            new Register(sprite);
        }

        static Register add(ResourceLocation spriteLocation) {
            new Register(spriteLocation);
        }

        static Register add(String modID, String baseName) {
            new Register(modID, baseName);
        }

        static Register add(String modID, String type, String baseName) {
            new Register(modID, type, baseName);
        }
/*
        static List<TextureEntry> getTextureEntries() {
            return TEXTURE_ENTRIES;
        }

        static TextureEntry getTextureEntryByEntry(TextureEntry entry) {
            int idx = 0;
            for(int i = 0; i < TEXTURE_ENTRIES.size(); i++) {
                if(TEXTURE_ENTRIES.contains(entry)) {
                    idx = TEXTURE_ENTRIES.indexOf(entry);
                }
            }
            return TEXTURE_ENTRIES.get(idx);
        }
       */
    }
}
