/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: From AtlasSpriteVariants.
 */

package com.thesledgehammer.groovymc.utils

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

class SpriteTools {

    static void BindTexture(String identifier) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(identifier));
    }

    static void BindTexture(ResourceLocation identifier) {
        Minecraft.getMinecraft().renderEngine.bindTexture(identifier);
        TextureMap.LOCATION_BLOCKS_TEXTURE
    }

    static void BindBlockTextureMap() {
        BindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }
}
