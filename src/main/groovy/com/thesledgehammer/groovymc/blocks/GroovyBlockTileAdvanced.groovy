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
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeFastTESR
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTESR
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import com.thesledgehammer.groovymc.utils.GroovyMachineStateMapper
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.state.BlockState
import net.minecraft.block.state.IBlockState
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.chunk.BlockStateContainer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.ModelLoader

import javax.annotation.Nonnull
import javax.annotation.Nullable
//To Improve: registerTileEntity
class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements BlockTileTraits, ITileEntityProvider {

    private final boolean hasTESR;
    private final boolean hasFastTESR;

    final P blockType

    GroovyBlockTileAdvanced(P blockType, Properties properties) {
        super(properties);

        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);

        this.hasTESR = blockType instanceof IBlockTypeTESR;
        this.hasFastTESR = blockType instanceof IBlockTypeFastTESR;
        properties.lightValue(!hasFastTESR && !hasTESR ? 255 : 0);
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
    EnumBlockRenderType getRenderType(IBlockState state) {
        if(hasFastTESR || hasTESR) {
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
