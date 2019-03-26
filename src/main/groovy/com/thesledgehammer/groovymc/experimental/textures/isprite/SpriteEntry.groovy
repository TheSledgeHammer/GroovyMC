package com.thesledgehammer.groovymc.experimental.textures.isprite

import com.thesledgehammer.groovymc.api.ISprite
import com.thesledgehammer.groovymc.client.definitions.GroovyAtlasSpriteDefinition
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class SpriteEntry implements ISprite {

    final ResourceLocation spriteLocation;
    private TextureAtlasSprite sprite;

    SpriteEntry(ResourceLocation spriteLocation) {
        this.spriteLocation = spriteLocation;
    }

    void onTextureStitchPre(TextureMap map) {
        TextureAtlasSprite spriteVar = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation);
        if(map.setTextureEntry(spriteVar)) {
            sprite = spriteVar;
        } else {
            sprite = map.getTextureExtry(spriteVar.getIconName());
        }
    }

    @Override
    void bindTexture() {

    }

    @Override
    double getInterpU(double u) {
        return 0
    }

    @Override
    double getInterpV(double v) {
        return 0
    }
}
