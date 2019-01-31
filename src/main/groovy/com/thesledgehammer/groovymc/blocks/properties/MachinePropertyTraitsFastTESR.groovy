/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraftforge.client.model.animation.FastTESR
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraitsFastTESR <T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesFastTESR<T> {

    private String particleTextureLocation;

    @Nullable
    @SideOnly(Side.CLIENT)
    private FastTESR<? super T> rendererFast;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @SideOnly(Side.CLIENT)
    void setRenderer(FastTESR<? super T> rendererFast) {
        this.rendererFast = rendererFast;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }
}