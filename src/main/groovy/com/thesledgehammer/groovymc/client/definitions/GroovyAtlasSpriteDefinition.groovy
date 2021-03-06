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

class GroovyAtlasSpriteDefinition extends TextureAtlasSprite {

    protected GroovyAtlasSpriteDefinition(String spriteName) {
        super(spriteName);
    }

    static TextureAtlasSprite createForConfig(ResourceLocation baseName) {
        return makeAtlasSprite(baseName);
    }

    static TextureAtlasSprite createForConfig(String modID, String baseName) {
        ResourceLocation resourceLocation = new ResourceLocation(modID, baseName);
        return makeAtlasSprite(resourceLocation);
    }

    static TextureAtlasSprite createForConfig(String baseName) {
        ResourceLocation resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), baseName);
        return makeAtlasSprite(resourceLocation);
    }
}