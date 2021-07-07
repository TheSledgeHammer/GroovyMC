/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Adds Methods, Converted to .groovy
 *********************************************************************************/
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

interface IMachineProperties<T extends GroovyTileBasic> extends IStringSerializable, IInitModel {

    void setTeType(TileEntityType<? extends T> teType);

    void setBlock(Block block);

    void setName(String name);

    void setVoxelShape(VoxelShape shape);

    void setIsFullCube(boolean isFullCube);

    TileEntityType<? extends T> getTeType();

    @Nullable
    Block getBlock();

    VoxelShape getShape();

    boolean isFullCube(BlockState state);

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel();

    boolean hasTileEntity(BlockState state)

    TileEntity createNewTileEntity();
}