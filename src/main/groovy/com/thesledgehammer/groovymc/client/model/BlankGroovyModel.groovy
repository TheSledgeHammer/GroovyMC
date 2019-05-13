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

import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import org.apache.commons.lang3.tuple.Pair

import javax.annotation.Nullable
import javax.vecmath.Matrix4f

class BlankGroovyModel implements IBakedModel {
	
	private final List<BakedQuad> quads;
	@Nullable
	protected ItemOverrideList overrideList;

	BlankGroovyModel(List<BakedQuad> quads) {
		this.quads = quads;
	}

	@Override
	List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, Random rand) {
		return Collections.emptyList();
	}

	@Override
	boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	boolean isAmbientOcclusion(IBlockState state) {
		return true;
	}

	@Override
	Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
		return super.handlePerspective(cameraTransformType)
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
	IBakedModel getBakedModel() {
		return this;
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
		return ItemOverrideList.EMPTY;
	}

	@Override
	ItemOverrideList getOverrides() {
		if (overrideList == null) {
			overrideList = createOverrides();
		}
		return overrideList;
	}
}
