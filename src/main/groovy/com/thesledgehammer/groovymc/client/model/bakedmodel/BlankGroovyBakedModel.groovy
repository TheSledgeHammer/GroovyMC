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

import net.minecraft.block.BlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.ItemCameraTransforms
import net.minecraft.client.renderer.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.Direction
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

@OnlyIn(Dist.CLIENT)
class BlankGroovyBakedModel implements IBakedModel {

	@Nullable
	protected ItemOverrideList overrideList;

	@Override
	List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
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
		return Minecraft.getInstance().getTextureMap().missingImage;
	}

	@Override
	ItemOverrideList getOverrides() {
		if (overrideList == null) {
			overrideList = createOverrides();
		}
		return overrideList;
	}

	protected static ItemOverrideList createOverrides() {
		return ItemOverrideList.EMPTY;
	}
	
	@Override
	ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}
}
