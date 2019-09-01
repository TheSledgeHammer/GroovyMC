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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.client.ISprite
import com.thesledgehammer.groovymc.utils.SpriteTools
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class GroovyISpriteDefinition implements ISprite {

    private final ResourceLocation spriteLocation;
    private TextureAtlasSprite sprite;

    protected GroovyISpriteDefinition(ResourceLocation spriteLocation) {
        this.spriteLocation = spriteLocation;
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation);
    }

    protected GroovyISpriteDefinition(String modID, String baseName) {
        this.spriteLocation = new ResourceLocation(modID, baseName);
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(modID, baseName);
    }

    protected GroovyISpriteDefinition(String baseName) {
        this.spriteLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), baseName);
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(baseName);
    }

    static TextureAtlasSprite createForConfig(ResourceLocation baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String modID, String baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(modID, baseName);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName);
        return iSprite.getTextureAtlasSprite();
    }

    static void onTextureStitchPre(TextureMap map, TextureAtlasSprite sprite, ResourceLocation spriteLocation) {
        //TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry()
        TextureAtlasSprite spriteVar = createForConfig(spriteLocation);
        if(map.setTextureEntry(spriteVar)) {
            sprite = spriteVar;
        } else {
            sprite = map.getTextureExtry(spriteVar.getIconName());
        }
    }

    @Override
    void bindTexture() {
        SpriteTools.BindBlockTextureMap();
    }

    @Override
    double getInterpU(double u) {
        return sprite.getInterpolatedU(u * 16);
    }

    @Override
    double getInterpV(double v) {
        return sprite.getInterpolatedV(v * 16);
    }

    private TextureAtlasSprite getTextureAtlasSprite() {
        return sprite;
    }
}
