/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Converted to .groovy, Adds FastTESR Render Abilities
 *********************************************************************************/

package com.thesledgehammer.groovymc.blocks


import com.thesledgehammer.groovymc.blocks.properties.IBlockType
<<<<<<< HEAD
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeFastTESR
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTESR
import com.thesledgehammer.groovymc.blocks.properties.IMachineProperties
import com.thesledgehammer.groovymc.gui.inventory.InventoryTools
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import com.thesledgehammer.groovymc.utils.GroovyMachineStateMapper
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
=======
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTER
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTERAnimation
import com.thesledgehammer.groovymc.blocks.properties.IMachineProperties
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.EnumProperty
>>>>>>> 1.16.x
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.IStringSerializable
import net.minecraft.util.Rotation
<<<<<<< HEAD
import net.minecraft.util.math.*
import net.minecraft.world.IBlockAccess
=======
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockReader
>>>>>>> 1.16.x
import net.minecraft.world.World

<<<<<<< HEAD
import javax.annotation.Nullable

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements IBlockRotation, ITileEntityProvider {

    protected static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class, EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.DOWN, EnumFacing.UP);
=======
class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements IBlockRotation {
>>>>>>> 1.16.x

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);

<<<<<<< HEAD
    protected final P blockType

    GroovyBlockTileAdvanced(P blockType, Material blockMaterialIn) {
        super(blockMaterialIn);
        this.blockType = blockType;
=======
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
>>>>>>> 1.16.x

    GroovyBlockTileAdvanced(P blockType, Material material) {
        super(material);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

        this.blockType = blockType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;
    }

    GroovyBlockTileAdvanced(P blockType) {
        super(Material.IRON);
<<<<<<< HEAD
        this.blockType = blockType;
    }

    private IMachineProperties getDefinition() {
        return blockType.getGroovyMachineProperties();
=======
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

        this.blockType = blockType;
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERAnimation = blockType instanceof IBlockTypeTERAnimation;
>>>>>>> 1.16.x
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
<<<<<<< HEAD
    boolean isFullCube(IBlockState state) {
        IMachineProperties definition = getDefinition();
        return definition.isFullCube(state);
=======
    void rotateAfterPlacement(PlayerEntity player, World world, BlockPos pos, Direction side) {
        BlockState state = world.getBlockState(pos);
        Direction facing = getPlacementRotation(player, world, pos, side);
        world.setBlockState(pos, state.with(FACING, facing));
>>>>>>> 1.16.x
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

<<<<<<< HEAD
    @Override
    boolean getUseNeighborBrightness(IBlockState state) {
        return hasFastTESR || hasTESR;
    }

    @Override
    int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    @Override
    BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    TileEntity createNewTileEntity(World worldIn, int meta) {
        return getDefinition().CreateTileEntity();
    }

    @SideOnly(Side.CLIENT)
    @Override
    void initModel() {
        getDefinition().initModel();
    }

    void registerAdvancedTileEntity() {
        getDefinition().registerTileEntity();
        registerStateMapper();
    }

    @SideOnly(Side.CLIENT)
    void registerStateMapper() {
        ModelLoader.setCustomStateMapper(this, new GroovyMachineStateMapper(blockType));
    }

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
    }

    @Override
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

    @Override
    IBlockState withRotation(IBlockState state, Rotation rot) {
        EnumFacing facing = state.getValue(FACING);
        return state.withProperty(FACING, rot.rotate(facing));
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
            groovyTile.addDrops(drops, fortune);
        }
        super.getDrops(result, world, pos, metadata, fortune);
    }*/

    @Override
    @Nullable
    AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        IMachineProperties definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, blockState);
    }

    @Override
    @SideOnly(Side.CLIENT)
    AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        IMachineProperties definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, state).offset(pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        IMachineProperties definition = getDefinition();
        return definition.collisionRayTrace(worldIn, pos, blockState, start, end);
=======
    static BlockState withRotation(BlockState state, Rotation rot) {
        Direction facing = state.get(FACING);
        return state.with(FACING, rot.rotate(facing));
>>>>>>> 1.16.x
    }
}
