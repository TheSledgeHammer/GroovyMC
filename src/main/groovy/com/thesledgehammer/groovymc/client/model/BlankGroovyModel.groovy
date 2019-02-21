/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model

import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.annotation.Nullable

class BlankGroovyModel implements IBakedModel {
	
	private final List<BakedQuad> quads;
	@Nullable
	protected ItemOverrideList overrideList;

	BlankGroovyModel(List<BakedQuad> quads) {
		this.quads = quads;
	}
	
	@Override
	List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return Collections.emptyList();
	}

	@Override
	boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	boolean isGui3d() {
		return true;
	}

	@Override
	boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	TextureAtlasSprite getParticleTexture() {
		return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
	}
	
	@Override
	ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}
	
	protected ItemOverrideList createOverrides() {
		return ItemOverrideList.NONE;
	}

	@Override
	ItemOverrideList getOverrides() {
		if (overrideList == null) {
			overrideList = createOverrides();
		}
		return overrideList;
	}
}
