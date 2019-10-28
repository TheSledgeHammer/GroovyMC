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

import com.thesledgehammer.groovymc.api.client.ISprite
import com.thesledgehammer.groovymc.client.definitions.GroovyAtlasSpriteDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyISpriteDefinition
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

class TextureEntry extends ModelEntryConsumer  {

    private ResourceLocation spriteResourceLocation;
    private TextureAtlasSprite textureAtlasSprite;
    private List<ResourceLocation> spriteResourceLocations = new LinkedList<>();
    private List<TextureAtlasSprite> textureAtlasSprites = new LinkedList<>();

    TextureEntry(String modID, String baseName) {
        setTextureAtlasSprite(new ResourceLocation(modID, baseName));
    }

    TextureEntry(String spriteResourceLocation) {
        setTextureAtlasSprite(new ResourceLocation(spriteResourceLocation));
    }

    TextureEntry(ResourceLocation spriteResourceLocation) {
        setTextureAtlasSprite(spriteResourceLocation);
    }

    private void setTextureAtlasSprite(ResourceLocation spriteResourceLocation) {
        this.spriteResourceLocation = spriteResourceLocation;
        if(this.textureAtlasSprite instanceof ISprite) {
            this.textureAtlasSprite = GroovyISpriteDefinition.createForConfig(spriteResourceLocation);
        }
        this.textureAtlasSprite = GroovyAtlasSpriteDefinition.createForConfig(spriteResourceLocation);

        //this.spriteResourceLocations.add(spriteResourceLocation);
        //this.textureAtlasSprites.add(textureAtlasSprite);
    }

    ResourceLocation getSpriteResourceLocation() {
        return spriteResourceLocation;
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return textureAtlasSprite;
    }

    void onTextureStitchPre(TextureMap map) {
        GroovyISpriteDefinition.onTextureStitchPre(map, getTextureAtlasSprite(), getSpriteResourceLocation());
    }
}
