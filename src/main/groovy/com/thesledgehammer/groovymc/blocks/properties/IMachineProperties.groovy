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

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

interface IMachineProperties<T extends GroovyTileBasic> extends IStringSerializable, IInitModel {

    void setTeClass(Class<T> teClass);

    void setBlock(Block block);

    void setName(String name);

    void setIsFullCube(boolean isFullCube);

    void setAxisAlignedBB(AxisAlignedBB boundingBox);

    void setRayTraceResult(RayTraceResult rayTraceResult);

    Class<T> getTeClass();

    @Nullable
    Block getBlock();

    String getName();

    boolean getIsFullCube();

    boolean isFullCube(IBlockState state);

    AxisAlignedBB getBoundingBox(IBlockAccess world, BlockPos pos, IBlockState state);

    @Nullable
    RayTraceResult collisionRayTrace(World world, BlockPos pos, IBlockState state, Vec3d startVec, Vec3d endVec);

    @Override
    @SideOnly(Side.CLIENT)
    void initModel();

    void registerTileEntity();

    void registerTileEntity(String modID);

    TileEntity CreateTileEntity();
}