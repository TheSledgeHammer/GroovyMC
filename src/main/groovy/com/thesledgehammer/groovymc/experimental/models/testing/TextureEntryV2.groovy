package com.thesledgehammer.groovymc.experimental.models.testing

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

class TextureEntryV2 extends ModelEntryConsumer {

    private static TextureEntryV2 instance;

    TextureEntryV2(Register register) {
        instance = this;
    }

    static TextureEntryV2 Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    List<ResourceLocation> getResourceLocations() {
        return GroovyDefinitionContext().getResourceLocations()
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return GroovyDefinitionContext().getTextureAtlasSprites();
    }

    ResourceLocation getResourceLocation(ResourceLocation resourceLocation) {
        for(ResourceLocation location : getResourceLocations()) {
            if(location.equals(resourceLocation)) {
                return location
            }
        }
        Log.logDebug("No ResourceLocation was found at ${resourceLocation}")
        return null;
    }

    TextureAtlasSprite getTextureAtlasSprite(TextureAtlasSprite atlasSprite) {
        for(TextureAtlasSprite sprite : getTextureAtlasSprites()) {
            if(sprite.equals(atlasSprite)) {
                return sprite
            }
        }
        Log.logDebug("No TextureAtlasSprite was found named ${atlasSprite}")
        return null;
    }

    static class Register {

        Register() {

        }

        static Register add(String sprite) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(sprite);
            GroovyDefinitionContext.Instance().setResourceLocation(sprite);
            return new Register();
        }

        static Register add(ResourceLocation spriteLocation) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(spriteLocation);
            GroovyDefinitionContext.Instance().setResourceLocation(spriteLocation);
            return new Register();
        }

        static Register add(String modID, String baseName) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(modID, baseName);
            GroovyDefinitionContext.Instance().setResourceLocation(modID, baseName);
            return new Register();
        }

        static Register add(String modID, String type, String baseName) {
            GroovyDefinitionContext.Instance().setTextureAtlasSprite(modID, baseName);
            GroovyDefinitionContext.Instance().setCustomResourceLocation(modID, type, baseName);
            return new Register();
        }

        TextureEntryV2 build() {
            return new TextureEntryV2(this);
        }
    }
}
