/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.bakedmodels

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@SideOnly(Side.CLIENT)
class AbstractModelBakerModel implements IAbstractModelBakerModel {


    private GroovysonModel groovysonModel;
    private float[] rotation = getDefaultRotation();
    private float[] translation = getDefaultTranslation();
    private float[] scale = getDefaultScale();

    AbstractModelBakerModel() {

    }

    @Override
    void setGui3d(boolean gui3d) {

    }

    @Override
    void setAmbientOcclusion(boolean ambientOcclusion) {

    }

    @Override
    void setParticleSprite(TextureAtlasSprite particleSprite) {

    }

    @Override
    void addQuad(EnumFacing facing, BakedQuad quad) {

    }

    private static float[] getDefaultRotation() {
        return [-80, -45, 170];
    }

    private static float[] getDefaultTranslation() {
        return [0, 1.5F, -2.75F];
    }

    private static float[] getDefaultScale() {
        return [0.375F, 0.375F, 0.375F];
    }

    @Override
    void setRotation(float[] rotation) {
        this.rotation = rotation;
    }

    @Override
    void setTranslation(float[] translation) {
        this.translation = translation;
    }

    @Override
    void setScale(float[] scale) {
        this.scale = scale;
    }

    @Override
    float[] getRotation() {
        return rotation;
    }

    @Override
    float[] getTranslation() {
        return translation;
    }

    @Override
    float[] getScale() {
        return scale;
    }

    @Override
    List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return null
    }

    @Override
    boolean isAmbientOcclusion() {
        return false
    }

    @Override
    boolean isGui3d() {
        return false
    }

    @Override
    boolean isBuiltInRenderer() {
        return false
    }

    @Override
    TextureAtlasSprite getParticleTexture() {
        return null
    }

    @Override
    ItemOverrideList getOverrides() {
        return null
    }
}
