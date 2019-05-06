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
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.client.model.BlankGroovyModel
import com.thesledgehammer.groovymc.client.model.MutableGroovyModel
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState

import javax.annotation.Nullable

class GroovyModelDefinition {

    private IBakedModel bakedModel;
    private GroovysonModel groovyModel;
    private IModel iModel;
    private IModelState iModelState;
    private IModelPart iModelPart;

    IBakedModel getIBakedModel() {
        return bakedModel;
    }

    GroovysonModel getGroovysonModel() {
        return groovyModel;
    }

    IModel getIModel() {
        return iModel
    }

    IModelState getIModelState() {
        return iModelState
    }

    IModelPart getIModelPart() {
        return iModelPart
    }

    void setIBakedModel(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
    }

    void setIModel(IModel iModel) {
        this.iModel = iModel;
    }

    void setIModelState(IModelState iModelState) {
        this.iModelState = iModelState;
    }

    void setIModelPart(IModelPart iModelPart) {
        this.iModelPart = iModelPart;
    }

    void setGroovysonModel(GroovysonModel groovyModel) {
        this.groovyModel = groovyModel;
    }
}
/*
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
*/