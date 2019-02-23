package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.MutableGroovyModel
import com.thesledgehammer.groovymc.client.model.MutableQuad
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.annotation.Nullable

class GroovyModelBuilder {
    private IBakedModel bakedModel;

    private GroovyModelBuilder(Builder builder) {
        this(builder.bakedModel);
    }

    private GroovyModelBuilder(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
    }

    IBakedModel getIBakedModel() {
        return bakedModel;
    }

    static class Builder {
        private IBakedModel bakedModel;

        Builder() {

        }

        Builder(IBakedModel bakedModel) {
            this.bakedModel = bakedModel;
        }

        Builder setIBakedModel(IBakedModel bakedModel) {
            this.bakedModel = bakedModel;
            return this;
        }

        /*Testing: IBakedModel implementation using a MutableGroovyModel*/
        Builder setIBakedModel(MutableGroovyModel mutableGroovyModel) {
            this.bakedModel = new IBakedModel() {
                @Override
                List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
                    return mutableGroovyModel.getQuads(state, side, rand);
                }

                @Override
                boolean isAmbientOcclusion() {
                    return mutableGroovyModel.isAmbientOcclusion();
                }

                @Override
                boolean isGui3d() {
                    return mutableGroovyModel.isGui3d();
                }

                @Override
                boolean isBuiltInRenderer() {
                    return mutableGroovyModel.isBuiltInRenderer();
                }

                @Override
                TextureAtlasSprite getParticleTexture() {
                    return mutableGroovyModel.getParticleTexture();
                }

                @Override
                ItemOverrideList getOverrides() {
                    return mutableGroovyModel.getOverrides();
                }
            }
            return this;
        }

        /*Testing: IBakedModel = MutableGroovyModel*/
        Builder setMutableGroovyModel(MutableQuad[]... quads) {
            this.bakedModel = new MutableGroovyModel(quads);
            return this;
        }

        GroovyModelBuilder build() {
            return new GroovyModelBuilder(this);
        }
    }
}
