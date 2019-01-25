/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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
