package com.thesledgehammer.groovymc.experimental.textures

import net.minecraft.client.renderer.texture.ITextureMapPopulator
import net.minecraft.client.renderer.texture.TextureMap

import javax.annotation.Nullable

class GroovyTextureMap extends TextureMap {

    GroovyTextureMap(String basePathIn) {
        super(basePathIn)
    }

    GroovyTextureMap(String basePathIn, @Nullable ITextureMapPopulator iconCreatorIn) {
        super(basePathIn, iconCreatorIn)
    }

    GroovyTextureMap(String basePathIn, boolean skipFirst) {
        super(basePathIn, skipFirst)
    }

    GroovyTextureMap(String basePathIn, @Nullable ITextureMapPopulator iconCreatorIn, boolean skipFirst) {
        super(basePathIn, iconCreatorIn, skipFirst)
    }
}
