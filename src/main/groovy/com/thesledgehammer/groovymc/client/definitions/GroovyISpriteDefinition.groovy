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

import net.minecraft.client.renderer.texture.AtlasTexture
import net.minecraft.client.renderer.texture.ISprite
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class GroovyISpriteDefinition implements ISprite {

    private TextureAtlasSprite sprite;

    protected GroovyISpriteDefinition(String spriteName, int widthIn, int heightIn) {
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(spriteName, widthIn, heightIn);
    }

    protected GroovyISpriteDefinition(ResourceLocation spriteLocation, int widthIn, int heightIn) {
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation, widthIn, heightIn);
    }

    protected GroovyISpriteDefinition(String modID, String baseName, int widthIn, int heightIn) {
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(modID, baseName, widthIn, heightIn);
    }

    static TextureAtlasSprite createForConfig(ResourceLocation baseName, int widthIn, int heightIn) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName, widthIn, heightIn);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String baseName, int widthIn, int heightIn) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName, widthIn, heightIn);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String modID, String baseName, int widthIn, int heightIn) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(modID, baseName, widthIn, heightIn);
        return iSprite.getTextureAtlasSprite();
    }

    static void onTextureStitchPre(AtlasTexture map, TextureAtlasSprite sprite, ResourceLocation spriteLocation) {
        int widthIn = map.getSprite(spriteLocation).getWidth();
        int heightIn = map.getSprite(spriteLocation).getHeight();

        TextureAtlasSprite spriteVar = createForConfig(spriteLocation, widthIn, heightIn);

        if(map.getSprite(spriteVar.getName()) && spriteVar.getWidth() == widthIn && spriteVar.getHeight() == heightIn) {
            sprite = spriteVar;
        } else {
            sprite = map.getAtlasSprite(spriteVar.toString());
        }
    }

    double getInterpU(double u) {
        return sprite.getInterpolatedU(u * 16);
    }

    double getInterpV(double v) {
        return sprite.getInterpolatedV(v * 16);
    }

    private TextureAtlasSprite getTextureAtlasSprite() {
        return sprite;
    }
}
