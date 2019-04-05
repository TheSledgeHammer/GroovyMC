package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.ISprite
import com.thesledgehammer.groovymc.client.definitions.GroovyAtlasSpriteDefinition
import com.thesledgehammer.groovymc.utils.SpriteTools
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class GroovyISpriteDefinition implements ISprite {

    private final ResourceLocation spriteLocation;
    private TextureAtlasSprite sprite;

    GroovyISpriteDefinition(ResourceLocation spriteLocation) {
        this.spriteLocation = spriteLocation;
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation);
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
        SpriteTools.BindBlockTextureMap();
    }

    @Override
    double getInterpU(double u) {
        return sprite.getInterpolatedU(u * 16);
    }

    @Override
    double getInterpV(double v) {
        return sprite.getInterpolatedV(v * 16);
    }
}
