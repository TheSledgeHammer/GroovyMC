package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

class TextureEntryV2 extends ModelEntryConsumer {

    private final String sprite
    private final ResourceLocation spriteLocation
    private final String modID
    private final String baseName
    private final String type

    TextureEntryV2(Register register) {
        this.sprite = register.sprite;
        this.spriteLocation = register.spriteLocation;
        this.modID = register.modID;
        this.baseName = register.baseName;
        this.type = register.type;
    }

    List<ResourceLocation> getResourceLocations() {
        return GroovyDefinitionContext().getResourceLocations()
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return GroovyDefinitionContext().getTextureAtlasSprites();
    }

    static class Register {

        private String sprite
        private ResourceLocation spriteLocation
        private String modID
        private String baseName
        private String type

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
