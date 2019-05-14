package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.ISprite
import com.thesledgehammer.groovymc.utils.SpriteTools
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.resources.IResourceManager
import net.minecraft.resources.SimpleResource
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class GroovyISpriteDefinition implements ISprite,  {

    private final ResourceLocation spriteLocation;
    private TextureAtlasSprite sprite;

    protected GroovyISpriteDefinition(ResourceLocation spriteLocation) {
        this.spriteLocation = spriteLocation;
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation);
    }

    protected GroovyISpriteDefinition(String modID, String baseName) {
        this.spriteLocation = new ResourceLocation(modID, baseName);
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(modID, baseName);
    }

    protected GroovyISpriteDefinition(String baseName) {
        this.spriteLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), baseName);
        this.sprite = GroovyAtlasSpriteDefinition.createForConfig(baseName);
    }

    static TextureAtlasSprite createForConfig(ResourceLocation baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String modID, String baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(modID, baseName);
        return iSprite.getTextureAtlasSprite();
    }

    static TextureAtlasSprite createForConfig(String baseName) {
        GroovyISpriteDefinition iSprite = new GroovyISpriteDefinition(baseName);
        return iSprite.getTextureAtlasSprite();
    }
    Map<ResourceLocation, TextureAtlasSprite> Maps = new HashMap<>();

    static void onTextureStitchPre(TextureMap map, TextureAtlasSprite sprite, ResourceLocation spriteLocation) {
        TextureAtlasSprite spriteVar = createForConfig(spriteLocation);
        if(map.registerSprite(spriteVar.name)) {
            sprite = spriteVar;
        } else {
            //sprite = map.getAtlasSprite(spriteVar.toString());
            sprite = map.getSprite(spriteVar.name)
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

    private TextureAtlasSprite getTextureAtlasSprite() {
        return sprite;
    }

    static void onTextureStitchPre(IResourceManager manager, TextureMap map, TextureAtlasSprite sprite, ResourceLocation spriteLocation) {
        TextureAtlasSprite spriteVar = createForConfig(spriteLocation);
        if(map.registerSprite(manager, spriteVar.getName())) {
            sprite = spriteVar;
        } else {
            //sprite = map.getAtlasSprite(spriteVar.toString());
            sprite = map.getSprite(spriteVar.name)
        }
    }
}
