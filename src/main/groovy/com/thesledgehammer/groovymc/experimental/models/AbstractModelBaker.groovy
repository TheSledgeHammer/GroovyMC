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

@SideOnly(Side.CLIENT)
class AbstractModelBaker implements IAbstractModelBaker {


    @Override
    void setColorIndex(int color) {

    }

    @Override
    void addBlockModel(BlockPos pos, TextureAtlasSprite[] sprites, int colorIndex) {

    }

    @Override
    void addBlockModel(BlockPos pos, TextureAtlasSprite sprites, int colorIndex) {

    }

    @Override
    void addModel(TextureAtlasSprite[] textures, int colorIndex) {

    }

    @Override
    void addModel(TextureAtlasSprite texture, int colorIndex) {

    }

    @Override
    void addBakedModel(IBlockState state, IBakedModel model) {

    }

    @Override
    void addBakedModelPost(IBlockState state, IBakedModel model) {

    }

    @Override
    void addFace(EnumFacing facing, TextureAtlasSprite sprite) {

    }

    @Override
    void setModelState(IModelState modelState) {

    }

    @Override
    void setParticleSprite(TextureAtlasSprite particleSprite) {

    }

    @Override
    IAbstractModelBakerModel bakeModel(boolean flip) {
        return null
    }
}
