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

import com.thesledgehammer.groovymc.blocks.properties.*

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.EnumProperty
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.IStringSerializable
import net.minecraft.util.Rotation
import net.minecraft.util.math.*
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nonnull
import javax.annotation.Nullable

/**Todo:
 * - Efficient Way to create/ register Tile Entities: Using MachineProperties
 * - Setting hasTileEntity to true: would make this akin ITileEntityProvider (aka a block base for TileEntities)
 */

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements IBlockRotation {

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);

    private boolean hasTER;
    private boolean hasTERFast;
    private boolean hasTERAnimation;

    final P blockType;

    GroovyBlockTileAdvanced(P blockType, Block.Properties blockProperties) {
        super(blockProperties);

        this.blockType = blockType;
        //this.hasTEType = blockType instanceof ITileType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERFast = blockType instanceof IBlockTypeTERFast;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;

        blockType.getGroovyMachineProperties().setBlock(this, getBlockProperties()
                .lightValue(!hasTERFast && !hasTER ? 255 : 0)
        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    private IMachineProperties getDefinition() {
        return blockType.getGroovyMachineProperties();
    }

    @Override
    boolean isVariableOpacity() {
        return !hasTERFast && !hasTER && !hasTERAnimation;
    }

    @Nonnull
    @Override
    TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return getDefinition().createNewTileEntity();
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return getDefinition().hasTileEntity(state);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    void initModel() {
        IMachineProperties definition = getDefinition();
        definition.initModel();
    }

    void registerAdvancedTileEntity() {
        IMachineProperties definition = getDefinition();
        definition.registerTileEntity();
    }

    @Override
    void rotateAfterPlacement(PlayerEntity player, World world, BlockPos pos, Direction side) {
        BlockState state = world.getBlockState(pos);
        Direction facing = getPlacementRotation(player, world, pos, side);
        world.setBlockState(pos, state.with(FACING, facing));
    }

    Direction getPlacementRotation(PlayerEntity player, World world, BlockPos pos, Direction side) {
        int l = MathHelper.floor(player.rotationYaw * 4F / 360F + 0.5D) & 3;
        if (l == 1) {
            return Direction.EAST;
        }
        if (l == 2) {
            return Direction.SOUTH;
        }
        if (l == 3) {
            return Direction.WEST;
        }
        return Direction.NORTH;
    }

    BlockState withRotation(BlockState state, Rotation rot) {
        Direction facing = state.get(FACING);
        return state.with(FACING, rot.rotate(facing));
    }

    @Nullable
    AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
        IMachineProperties definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, blockState);
    }

    @OnlyIn(Dist.CLIENT)
    AxisAlignedBB getSelectedBoundingBox(BlockState state, World worldIn, BlockPos pos) {
        IMachineProperties definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, state).offset(pos);
    }

    @OnlyIn(Dist.CLIENT)
    RayTraceResult collisionRayTrace(BlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        IMachineProperties definition = getDefinition();
        return definition.collisionRayTrace(worldIn, pos, blockState, start, end);
    }
}
