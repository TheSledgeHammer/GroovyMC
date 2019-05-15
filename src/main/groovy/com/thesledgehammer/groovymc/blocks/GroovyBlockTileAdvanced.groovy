/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.blocks

import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTERFast
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTER
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.state.IBlockState
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements BlockTileTraits, ITileEntityProvider {

    private boolean hasTER;
    private boolean hasTERFast;

    final P blockType

    GroovyBlockTileAdvanced(P blockType, Properties properties) {
        super(properties);

        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);

        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERFast = blockType instanceof IBlockTypeTERFast;
        properties.lightValue(!hasTERFast && !hasTER ? 255 : 0);
        this.setDefaultState(getStateContainer().getBaseState().with(BlockStateProperties.FACING, EnumFacing.NORTH));
    }

    GroovyBlockTileAdvanced(P blockType) {
        super();
        this.blockType = blockType;
    }

    private MachinePropertyTraits getDefinition() {
        return blockType.getGroovyMachineProperties();
    }

    @Override
    boolean isVariableOpacity() {
        return !hasTERFast && !hasTER;
    }

    @Override
    boolean isNormalCube(IBlockState state) {
        return !hasTERFast && !hasTER;
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
    EnumBlockRenderType getRenderType(IBlockState state) {
        if(hasTERFast || hasTER) {
            return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
        } else {
            return EnumBlockRenderType.MODEL;
        }
    }

    @Override
    TileEntity createNewTileEntity(IBlockReader worldIn) {
        return getDefinition().CreateTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    void initModel() {
        blockType.getGroovyMachineProperties().initModel();
        VoxelShape
    }

    @OnlyIn(Dist.CLIENT)
    AxisAlignedBB getBoundingBox(Vec3d startVec, Vec3d endVec) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(startVec, endVec);
    }

    @OnlyIn(Dist.CLIENT)
    AxisAlignedBB getBoundingBox(BlockPos minPos, BlockPos maxPos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(minPos, maxPos);
    }

//VoxelShape: Relates to boundingboxes
/*
    @Nullable
    @Override
    AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, blockState);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, state).offset(pos);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        MachinePropertyTraits definition = getDefinition();
        return definition.collisionRayTrace(worldIn, pos, blockState, start, end);
    }
    */
}
