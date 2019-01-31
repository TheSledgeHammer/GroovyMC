/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraitsTESR<T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesTESR<T> {

    private String particleTextureLocation;

    @Nullable
    @SideOnly(Side.CLIENT)
    private TileEntitySpecialRenderer<? super T> renderer;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @SideOnly(Side.CLIENT)
    void setRenderer(TileEntitySpecialRenderer<? super T> renderer) {
        this.renderer = renderer;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }
}