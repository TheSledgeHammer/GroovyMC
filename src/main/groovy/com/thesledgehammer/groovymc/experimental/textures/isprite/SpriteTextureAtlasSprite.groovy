package com.thesledgehammer.groovymc.experimental.textures.isprite

import com.thesledgehammer.groovymc.api.ISprite
import com.thesledgehammer.groovymc.client.definitions.GroovyAtlasSpriteDefinition
import net.minecraft.client.renderer.texture.TextureAtlasSprite

//ISprite Used in MutableVertex: Lines 202 - 204 (renderTex()
class SpriteTextureAtlasSprite extends TextureAtlasSprite implements ISprite {

    protected SpriteTextureAtlasSprite(String spriteName) {
        super(spriteName)
    }

    @Override
    void bindTexture() {
        GroovyAtlasSpriteDefinition.BindBlockTextureMap();
    }

    @Override
    double getInterpU(double u) {
        return this.getInterpolatedU(u * 16);
    }

    @Override
    double getInterpV(double v) {
        return this.getInterpolatedV(v * 16);
    }
}
