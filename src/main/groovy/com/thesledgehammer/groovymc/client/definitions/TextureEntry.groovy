package com.thesledgehammer.groovymc.client.definitions

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

abstract class TextureEntry extends ModelEntryConsumer {

    TextureEntry(String sprite) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(sprite);
        this.GroovyDefinitionContext().setResourceLocation(sprite);
    }

    TextureEntry(ResourceLocation spriteLocation) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(spriteLocation);
        this.GroovyDefinitionContext().setResourceLocation(spriteLocation);
    }

    TextureEntry(String modID, String baseName) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        this.GroovyDefinitionContext().setResourceLocation(modID, baseName);
    }

    TextureEntry(String modID, String type, String baseName) {
        this.GroovyDefinitionContext().setTextureAtlasSprite(modID, baseName);
        this.GroovyDefinitionContext().setCustomResourceLocation(modID, type, baseName);
    }

    ResourceLocation getResourceLocation() {
        return this.GroovyDefinitionContext().getResourceLocation()
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return this.GroovyDefinitionContext().getTextureAtlasSprite()
    }
}
