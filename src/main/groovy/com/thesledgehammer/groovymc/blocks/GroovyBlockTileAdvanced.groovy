/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.blocks

import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeFastTESR
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTESR
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits
import com.thesledgehammer.groovymc.utils.GroovyStateMapper
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlockTileBasic {

    private final boolean hasTESR;
    private final boolean hasFastTESR;

    final P blockType

    GroovyBlockTileAdvanced(P blockType, Material blockMaterialIn) {
        super(blockMaterialIn);

        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);

        this.hasTESR = blockType instanceof IBlockTypeTESR;
        this.hasFastTESR = blockType instanceof IBlockTypeFastTESR;
        this.lightOpacity = (!hasFastTESR && !hasTESR) ? 255 : 0;

        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.initModel();
    }

    GroovyBlockTileAdvanced(P blockType) {
        this(blockType, Material.IRON);
    }

    private MachinePropertyTraits getDefinition() {
        return blockType.getGroovyMachineProperties();
    }

    @Override
    boolean isOpaqueCube(IBlockState state) {
        return !hasFastTESR && !hasTESR;
    }

    @Override
    boolean isNormalCube(IBlockState state) {
        return !hasFastTESR && !hasTESR;
    }

    @Override
    boolean isBlockNormalCube(IBlockState blockState) {
        return isNormalCube(blockState);
    }

    @Override
    boolean isFullCube(IBlockState state) {
        MachinePropertyTraits definition = getDefinition();
        return definition.isFullCube(state);
    }

    @Override
    BlockStateContainer getBlockState() {
        return this.blockState;
    }

    @Override
    EnumBlockRenderType getRenderType(IBlockState state) {
        if(hasFastTESR || hasTESR) {
            return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
        } else {
            return EnumBlockRenderType.MODEL;
        }
    }

    @Override
    boolean getUseNeighborBrightness(IBlockState state) {
        return hasFastTESR || hasTESR;
    }

    @Override
    TileEntity createNewTileEntity(World worldIn, int meta) {
        return getDefinition().createTileEntity();
    }

    void registerAdvancedTileEntity() {
        blockType.getGroovyMachineProperties().registerTileEntity();
        registerStateMapper();
    }

    void registerStateMapper() {
        ModelLoader.setCustomStateMapper(this, new GroovyStateMapper<>(blockType));
    }

    @Nullable
    @Override
    AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(pos, blockState);
    }

    @Override
    @SideOnly(Side.CLIENT)
    AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(pos, state).offset(pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        MachinePropertyTraits definition = getDefinition();
        return definition.collisionRayTrace(worldIn, pos, start, end);
    }
}
