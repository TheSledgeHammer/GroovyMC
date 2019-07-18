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
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

interface IMachineProperties<T extends GroovyTileBasic> extends IStringSerializable, IInitModel {

    void setTeClass(Class<T> teClass);

    void setBlock(Block block, Block.Properties blockProperties);

    void setName(String name);

    void setIsFullCube(boolean isFullCube);

    void setAxisAlignedBB(AxisAlignedBB boundingBox);

    void setRayTraceResult(RayTraceResult rayTraceResult);

    Class<T> getTeClass();

    @Nullable
    Block getBlock();

    String getName();

    boolean getIsFullCube();

    boolean isFullCube(BlockState state);

    AxisAlignedBB getBoundingBox(IBlockReader world, BlockPos pos, BlockState state);

    @Nullable
    RayTraceResult collisionRayTrace(World world, BlockPos pos, BlockState state, Vec3d startVec, Vec3d endVec);

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel();

    void registerTileEntity();

    void registerTileEntity(String modID);

    boolean hasTileEntity(BlockState state)

    TileEntity createNewTileEntity();
}