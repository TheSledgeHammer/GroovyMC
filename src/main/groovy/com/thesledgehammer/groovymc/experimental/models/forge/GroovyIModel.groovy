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

package com.thesledgehammer.groovymc.experimental.models.forge

import com.thesledgehammer.groovymc.client.model.bakedmodel.IModelGroovyBakedModel
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.common.model.TRSRTransformation

import java.util.function.Function
//Work in Progess
class GroovyIModel implements IModel {

    GroovyIModel() {

    }

    @Override
    IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new IModelGroovyBakedModel(state, format, bakedTextureGetter);
    }

    @Override
    Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    Collection<ResourceLocation> getTextures() {
        return Collections.emptySet();
    }

    @Override
    IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }
}
