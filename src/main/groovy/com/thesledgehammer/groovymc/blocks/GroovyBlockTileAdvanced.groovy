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
import com.thesledgehammer.groovymc.utils.VoxelShapeTools
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.EnumProperty
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.IStringSerializable
import net.minecraft.util.Rotation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.vector.Vector3d
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nonnull
import javax.annotation.Nullable

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements IBlockRotation {

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);

    private boolean hasTER;
    private boolean hasTERAnimation;
    final P blockType;

    GroovyBlockTileAdvanced(P blockType, Properties blockProperties) {
        super(blockProperties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

        this.blockType = blockType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;
    }

    GroovyBlockTileAdvanced(P blockType, Material material) {
        super(material);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

        this.blockType = blockType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;
    }

    GroovyBlockTileAdvanced(P blockType) {
        super(Material.IRON);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

        this.blockType = blockType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;
    }

    private IMachineProperties getDefinition() {
        return blockType.getGroovyMachineProperties();
    }

    @Override
    TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return getDefinition().createNewTileEntity();
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return getDefinition().hasTileEntity(state);
    }

    @Override
    void rotateAfterPlacement(PlayerEntity player, World world, BlockPos pos, Direction side) {
        BlockState state = world.getBlockState(pos);
        Direction facing = getPlacementRotation(player, world, pos, side);
        world.setBlockState(pos, state.with(FACING, facing));
    }

    static Direction getPlacementRotation(PlayerEntity player, World world, BlockPos pos, Direction side) {
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

    static BlockState withRotation(BlockState state, Rotation rot) {
        Direction facing = state.get(FACING);
        return state.with(FACING, rot.rotate(facing));
    }
}
