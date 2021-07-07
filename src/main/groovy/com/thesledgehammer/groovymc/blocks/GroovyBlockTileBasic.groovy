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

<<<<<<< HEAD
import com.thesledgehammer.groovymc.gui.inventory.InventoryTools
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.inventory.IInventory
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class GroovyBlockTileBasic extends GroovyBlock implements ITileEntityProvider {

    protected static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.DOWN, EnumFacing.UP);

    GroovyBlockTileBasic(Material blockMaterialIn) {
        super(blockMaterialIn);
    }
=======
>>>>>>> 1.16.x

import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.state.EnumProperty
import net.minecraft.util.Direction

<<<<<<< HEAD
    @Override
    void breakBlock(World world, BlockPos pos, IBlockState state) {
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
        super.breakBlock(world, pos, state);
=======
abstract class GroovyBlockTileBasic extends GroovyBlock {

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);

    GroovyBlockTileBasic(Properties properties) {
        super(properties);
    }

    GroovyBlockTileBasic(Material material) {
        super(Properties.create(material));
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
>>>>>>> 1.16.x
    }
/*
    //TODO: Incomplete
    void getDrops(NonNullList<ItemStack> result, IBlockAccess world, BlockPos pos, IBlockState metadata, int fortune) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof GroovyTileBasic) {
            GroovyTileBasic groovyTile = (GroovyTileBasic) tile;
            ItemStack stack = new ItemStack(Item.getItemFromBlock(tile.getBlockType()));
            NBTTagCompound nbt = new NBTTagCompound();
            groovyTile.writeToNBT(nbt);
            stack.setTagCompound(nbt);
            //groovyTile.addDrops(drops, fortune);
        }
        super.getDrops(result, world, pos, metadata, fortune);
    }*/
}
