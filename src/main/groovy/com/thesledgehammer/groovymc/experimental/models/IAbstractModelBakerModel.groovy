/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.models

import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@SideOnly(Side.CLIENT)
interface IAbstractModelBakerModel extends IBakedModel {

    void setGui3d(boolean gui3d);

    void setAmbientOcclusion(boolean ambientOcclusion);

    void setParticleSprite(TextureAtlasSprite particleSprite);

    void addQuad(@Nullable EnumFacing facing, BakedQuad quad);

    void setRotation(float[] rotation);

    void setTranslation(float[] translation);

    void setScale(float[] scale);

    float[] getRotation();

    float[] getTranslation();

    float[] getScale();
}