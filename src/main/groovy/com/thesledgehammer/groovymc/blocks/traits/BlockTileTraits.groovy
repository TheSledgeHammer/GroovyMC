/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.blocks.traits

import com.thesledgehammer.groovymc.blocks.properties.IBlockRotation

import com.thesledgehammer.groovymc.utils.GroovyLoader
import com.thesledgehammer.groovymc.utils.InventoryTools
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

trait BlockTileTraits implements IBlockRotation {

    static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.DOWN, EnumFacing.UP);

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
    }

    void rotateAfterPlacement(EntityPlayer player, World world, BlockPos pos, EnumFacing side) {
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = getPlacementRotation(player, world, pos, side);
        world.setBlockState(pos, state.withProperty(FACING, facing));
    }

    EnumFacing getPlacementRotation(EntityPlayer player, World world, BlockPos pos, EnumFacing side) {
        int l = MathHelper.floor(player.rotationYaw * 4F / 360F + 0.5D) & 3;
        if (l == 1) {
            return EnumFacing.EAST;
        }
        if (l == 2) {
            return EnumFacing.SOUTH;
        }
        if (l == 3) {
            return EnumFacing.WEST;
        }
        return EnumFacing.NORTH;
    }

    IBlockState withRotation(IBlockState state, Rotation rot) {
        EnumFacing facing = state.getValue(FACING);
        return state.withProperty(FACING, rot.rotate(facing));
    }

    //TODO: Incomplete
    void getDrops(NonNullList<ItemStack> result, IBlockAccess world, BlockPos pos, IBlockState metadata, int fortune) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof GroovyTileBasic) {
            GroovyTileBasic groovyTile = (GroovyTileBasic) tile;
            ItemStack stack = new ItemStack(Item.getItemFromBlock(block));
            NBTTagCompound nbt = new NBTTagCompound();
            groovyTile.writeToNBT(nbt);
            stack.setTagCompound(nbt);
            groovyTile.addDrops(drops, fortune);
        }
        super.getDrops(result, world, pos, metadata, fortune);
    }

    void registerBasicTileEntity(Class<? extends TileEntity> tileEntity, String modId, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(modId, tileName));
    }

    void registerBasicTileEntity(Class<? extends TileEntity> tileEntity, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(GroovyLoader.Instance().getModID(), tileName));
    }
}