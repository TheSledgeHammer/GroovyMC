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
}
