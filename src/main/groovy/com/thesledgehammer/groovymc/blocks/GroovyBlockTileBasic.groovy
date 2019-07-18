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

import com.thesledgehammer.groovymc.gui.inventory.InventoryTools
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.IInventory
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

import javax.annotation.Nonnull

//Todo: Efficient Way to create Tile Entities: Setting hasTileEntity to true, would make this like ITileEntityProvider
abstract class GroovyBlockTileBasic extends GroovyBlock implements IBlockRotation {

    GroovyBlockTileBasic(Block.Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    abstract TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world);

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
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

    void breakBlock(World world, BlockPos pos, BlockState state) {
        if (world.isRemote) {
            return;
        }
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IInventory) {
            IInventory inventory = (IInventory) tile;
            InventoryTools.dropInventory(inventory, world, pos);
        }
        if (tile instanceof GroovyTileBasic) {
            GroovyTileBasic groovyTile = (GroovyTileBasic) tile;
            groovyTile.onRemoval();
        }
        world.removeTileEntity(pos);
    }
}
