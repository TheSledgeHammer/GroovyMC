/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.client.model.BlankGroovyModel
import com.thesledgehammer.groovymc.client.model.MutableGroovyModel
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.GroovysonModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.annotation.Nullable

class GroovyModelDefinition {
//Add IModel's & IModelState
    private IBakedModel bakedModel;
    private GroovysonModel groovyModel;

    IBakedModel getIBakedModel() {
        return bakedModel;
    }

    GroovysonModel getGroovysonModel() {
        return groovyModel;
    }

    void setIBakedModel(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
    }

    void setMutableIBakedModel(MutableQuad[]... quads) {
        this.bakedModel = new MutableGroovyModel(quads);
    }

    void setIBakedModelFromMutableGroovyModel(MutableGroovyModel mutableGroovyModel) {
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
    }

    void setIBakedModelFromBlankGroovyModel(BlankGroovyModel blankGroovyModel) {
        this.bakedModel = new IBakedModel() {
            @Override
            List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
                return blankGroovyModel.getQuads(state, side, rand);
            }

            @Override
            boolean isAmbientOcclusion() {
                return blankGroovyModel.isAmbientOcclusion();
            }

            @Override
            boolean isGui3d() {
                return blankGroovyModel.isGui3d();
            }

            @Override
            boolean isBuiltInRenderer() {
                return blankGroovyModel.isBuiltInRenderer();
            }

            @Override
            TextureAtlasSprite getParticleTexture() {
                return blankGroovyModel.getParticleTexture();
            }

            @Override
            ItemOverrideList getOverrides() {
                return blankGroovyModel.getOverrides();
            }
        }
    }

    void setGroovysonModel(GroovysonModel groovyModel) {
        this.groovyModel = groovyModel;
    }
}
