/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.models

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@SideOnly(Side.CLIENT)
interface IAbstractModelBaker {

    void setColorIndex(int color);

    void addBlockModel(@Nullable BlockPos pos, TextureAtlasSprite[] sprites, int colorIndex);

    void addBlockModel(@Nullable BlockPos pos, TextureAtlasSprite sprites, int colorIndex);

    void addModel(TextureAtlasSprite[] textures, int colorIndex);

    void addModel(TextureAtlasSprite texture, int colorIndex);

    void addBakedModel(@Nullable IBlockState state, IBakedModel model);

    void addBakedModelPost(@Nullable IBlockState state, IBakedModel model);

    void addFace(EnumFacing facing, TextureAtlasSprite sprite);

    void setModelState(@Nullable IModelState modelState);

    void setParticleSprite(TextureAtlasSprite particleSprite);

    IAbstractModelBakerModel bakeModel(boolean flip);
}