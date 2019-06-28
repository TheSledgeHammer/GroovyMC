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

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

class TextureEntry extends ModelEntryConsumer {

    private static TextureEntry instance;

    TextureEntry(Register register) {
        instance = this;
    }

    static TextureEntry Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    List<ResourceLocation> getResourceLocations() {
        return GroovyDefinitionContext().getResourceLocations()
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return GroovyDefinitionContext().getTextureAtlasSprites();
    }

    ResourceLocation getResourceLocation(ResourceLocation resourceLocation) {
        for(ResourceLocation location : getResourceLocations()) {
            if(location.equals(resourceLocation)) {
                return location
            }
        }
        Log.logDebug("No ResourceLocation was found at ${resourceLocation}")
        return null;
    }

    TextureAtlasSprite getTextureAtlasSprite(TextureAtlasSprite atlasSprite) {
        for(TextureAtlasSprite sprite : getTextureAtlasSprites()) {
            if(sprite.equals(atlasSprite)) {
                return sprite
            }
        }
        Log.logDebug("No TextureAtlasSprite was found named ${atlasSprite}")
        return null;
    }

    void onTextureStitchPre(TextureMap map) {
        GroovyDefinitionContext().onTextureStitchPre(map);
    }

    static class Register {

        Register() {

        }

        static Register add(String sprite) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(sprite);
            GroovyDefinitionContext.Instance().setResourceLocation(sprite);
            return new Register();
        }

        static Register add(ResourceLocation spriteLocation) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(spriteLocation);
            GroovyDefinitionContext.Instance().setResourceLocation(spriteLocation);
            return new Register();
        }

        static Register add(String modID, String baseName) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(modID, baseName);
            GroovyDefinitionContext.Instance().setResourceLocation(modID, baseName);
            return new Register();
        }

        static Register add(String modID, String type, String baseName) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(modID, baseName);
            GroovyDefinitionContext.Instance().setCustomResourceLocation(modID, type, baseName);
            return new Register();
        }

        TextureEntry build() {
            return new TextureEntry(this);
        }
    }
}
