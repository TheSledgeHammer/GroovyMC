/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 *********************************************************************************/

package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface IMachinePropertiesTESR<T extends GroovyTileBasic> extends IMachineProperties<T> {

    void setParticleTextureLocation(String particleTextureLocation);

    String getParticleTextureLocation();

    @SideOnly(Side.CLIENT)
    void setRenderer(TileEntitySpecialRenderer<? super T> renderer);

    @Override
    void registerTileEntity();

    @Override
    void registerTileEntity(String modID);
}