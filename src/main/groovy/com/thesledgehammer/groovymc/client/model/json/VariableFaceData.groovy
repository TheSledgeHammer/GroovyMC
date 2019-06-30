/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.ModelUtil
import net.minecraft.client.renderer.texture.TextureAtlasSprite

class VariableFaceData {

    static ModelUtil.UvFaceData uvs = new ModelUtil.UvFaceData();
    static TextureAtlasSprite sprite;
    static int rotations = 0;
    static boolean invertNormal = false;
    static boolean bothSides = false;
}
