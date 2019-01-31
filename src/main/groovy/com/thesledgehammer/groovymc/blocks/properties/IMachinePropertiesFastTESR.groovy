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

interface IMachinePropertiesFastTESR<T extends GroovyTileBasic> extends IMachineProperties<T> {

    void setParticleTextureLocation(String particleTextureLocation);

    String getParticleTextureLocation();

    @SideOnly(Side.CLIENT)
    void setRenderer(FastTESR<? super T> rendererFast)
}