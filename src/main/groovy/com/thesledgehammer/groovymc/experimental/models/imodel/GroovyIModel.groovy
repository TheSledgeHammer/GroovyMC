package com.thesledgehammer.groovymc.experimental.models.imodel

import com.google.common.collect.ImmutableSet
import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.common.model.TRSRTransformation

import java.util.function.Function

class GroovyIModel implements IModel {

    @Override
    IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new GroovyBakedIModel(state, format, bakedTextureGetter);
    }

    @Override
    Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    Collection<ResourceLocation> getTextures() {
        return ImmutableSet.of(GroovyDefinitionContext.Instance().getResourceLocation());
    }

    @Override
    IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }
}
