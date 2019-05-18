package com.thesledgehammer.groovymc.experimental.models.imodel

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.model.bakedmodel.BlankGroovyBakedModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.model.IModelState

import javax.annotation.Nullable
import java.util.function.Function

class GroovyBakedIModel implements IBakedModel {

    private BlankGroovyBakedModel model;
    private VertexFormat format;
    private TextureAtlasSprite sprite;

    GroovyBakedIModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        this.format = format;
        this.sprite = bakedTextureGetter.apply(GroovyDefinitionContext.Instance().getResourceLocation());
    }

    @Override
    List<BakedQuad> getQuads(@Nullable IBlockState iBlockState, @Nullable EnumFacing enumFacing, long l) {
        return Collections.emptyList();
    }

    @Override
    boolean isAmbientOcclusion() {
        return model.isAmbientOcclusion();
    }

    @Override
    boolean isGui3d() {
        return model.isGui3d();
    }

    @Override
    boolean isBuiltInRenderer() {
        return model.isBuiltInRenderer();
    }

    @Override
    TextureAtlasSprite getParticleTexture() {
        return model.getParticleTexture();
    }

    @Override
    ItemOverrideList getOverrides() {
        return model.getOverrides();
    }
}