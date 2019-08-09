/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.client.model.bakedmodel

import com.thesledgehammer.groovymc.client.definitions.model.TextureEntry
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

//Work in Progress:
class IModelGroovyBakedModel implements IBakedModel {

    private BlankGroovyBakedModel model;
    private VertexFormat format;

    IModelGroovyBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        this.format = format;
        for(ResourceLocation location : TextureEntry.Instance().getResourceLocations()) {
            bakedTextureGetter.apply(location);
        }
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
