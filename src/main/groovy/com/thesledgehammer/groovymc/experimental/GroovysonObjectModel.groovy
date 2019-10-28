package com.thesledgehammer.groovymc.experimental

import com.google.common.collect.ImmutableMap
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.IUnbakedModel
import net.minecraft.client.renderer.model.ModelBakery
import net.minecraft.client.renderer.texture.ISprite
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.ResourceLocation

import java.util.function.Function

class GroovysonObjectModel implements IUnbakedModel {

    @Override
    Collection<ResourceLocation> getDependencies() {
        return Collections.emptyList();
    }

    @Override
    Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter, Set<String> missingTextureErrors) {
        return null
    }

    @Override
    IBakedModel bake(ModelBakery bakery, Function<ResourceLocation, TextureAtlasSprite> spriteGetter, ISprite sprite, VertexFormat format) {
        return null
    }

    @Override
    IUnbakedModel process(ImmutableMap<String, String> customData) {
        return super.process(customData)
    }

    @Override
    IUnbakedModel retexture(ImmutableMap<String, String> textures) {
        return super.retexture(textures)
    }
}