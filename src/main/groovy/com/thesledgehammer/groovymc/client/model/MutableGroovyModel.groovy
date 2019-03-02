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

package com.thesledgehammer.groovymc.client.model

import com.google.common.collect.ImmutableList
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class MutableGroovyModel implements IBakedModel {
	
	private final List<BakedQuad> quads;
	private BlankGroovyModel model;
	
	MutableGroovyModel(MutableQuad[]... quads) {
		ImmutableList.Builder<BakedQuad> list = ImmutableList.builder();
		for (MutableQuad[] qu : quads) {
			for (MutableQuad q : qu) {
				list.add(q.toBakedItem());
			}
		}
		this.quads = list.build();
	}
	
	@Override
	List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
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
