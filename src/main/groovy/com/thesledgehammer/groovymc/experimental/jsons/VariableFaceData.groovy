package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import net.minecraft.client.renderer.texture.TextureAtlasSprite

class VariableFaceData {

    static ModelUtil.UvFaceData uvs = new ModelUtil.UvFaceData();
    static TextureAtlasSprite sprite;
    static int rotations = 0;
    static boolean invertNormal = false;
    static boolean bothSides = false;
}
