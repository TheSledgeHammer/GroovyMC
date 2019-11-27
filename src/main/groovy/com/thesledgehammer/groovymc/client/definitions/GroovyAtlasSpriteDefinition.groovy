/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: From SpriteAtlasSwappable.
 */

package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.GroovyLoader
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

/* May eventually extend to include animations from TextureAtlasSprite */
class GroovyAtlasSpriteDefinition extends TextureAtlasSprite {

    protected GroovyAtlasSpriteDefinition(String spriteName, int widthIn, int heightIn) {
        this(new ResourceLocation(GroovyLoader.Instance().getModID(), spriteName), widthIn, heightIn);
    }

    protected GroovyAtlasSpriteDefinition(ResourceLocation baseName,  int widthIn, int heightIn) {
        super(baseName, widthIn, heightIn);
    }

    static TextureAtlasSprite createForConfig(String spriteName, int widthIn, int heightIn) {
        return new GroovyAtlasSpriteDefinition(spriteName, widthIn, heightIn);
    }

    static TextureAtlasSprite createForConfig(ResourceLocation baseName,  int widthIn, int heightIn) {
        return new GroovyAtlasSpriteDefinition(baseName, widthIn, heightIn);
    }

    static TextureAtlasSprite createForConfig(String modID, String baseName, int widthIn, int heightIn) {
        ResourceLocation resourceLocation = new ResourceLocation(modID, baseName);
        return createForConfig(resourceLocation, widthIn, heightIn);
    }
}