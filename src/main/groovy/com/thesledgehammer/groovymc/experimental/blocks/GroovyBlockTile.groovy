package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.blocks.GroovyBlock
import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeFastTESR
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTESR
import com.thesledgehammer.groovymc.blocks.properties.IMachineProperties
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import com.thesledgehammer.groovymc.utils.GroovyMachineStateMapper
import net.minecraft.block.ITileEntityProvider
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

class GroovyBlockTile<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements BlockTileTraits, ITileEntityProvider {

    private final boolean hasTESR;
    private final boolean hasFastTESR;

    final P blockType

    GroovyBlockTile(P blockType, Material blockMaterialIn) {
        super(blockMaterialIn);
        this.blockType = blockType;

        blockType.getGroovyMachineProperties().setBlock(this);

        this.hasTESR = blockType instanceof IBlockTypeTESR;
        this.hasFastTESR = blockType instanceof IBlockTypeFastTESR;
        this.lightOpacity = (!hasFastTESR && !hasTESR) ? 255 : 0;

        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    GroovyBlockTile(P blockType) {
        super(Material.IRON);
        this.blockType = blockType;
    }

    private IMachineProperties getDefinition() {
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
        IMachineProperties definition = getDefinition();
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
        blockType.getGroovyMachineProperties().initModel();
    }

    void registerAdvancedTileEntity() {
        blockType.getGroovyMachineProperties().registerTileEntity();
        registerStateMapper();
    }

    @SideOnly(Side.CLIENT)
    void registerStateMapper() {
        ModelLoader.setCustomStateMapper(this, new GroovyMachineStateMapper<>(blockType));
    }

    @Nullable
    @Override
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
    }
}
