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

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTER
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeTERFast
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.state.StateContainer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
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

class GroovyBlockTileAdvanced<P extends Enum<P> & IBlockType & IStringSerializable> extends GroovyBlock implements BlockTileTraits, IInitModel {

    private boolean hasTER;
    private boolean hasTERFast;

    final P blockType;

    GroovyBlockTileAdvanced(P blockType) {
        super();
        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERFast = blockType instanceof IBlockTypeTERFast;
        Block.Properties.create(Material.IRON)
                .hardnessAndResistance(1.5F)
                .lightValue(!hasTERFast && !hasTER ? 255 : 0);

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }


    GroovyBlockTileAdvanced(P blockType, Material material) {
        super(material);
        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);
        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERFast = blockType instanceof IBlockTypeTERFast;
        Block.Properties.create(material)
                .hardnessAndResistance(1.5F)
                .lightValue(!hasTERFast && !hasTER ? 255 : 0);

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    GroovyBlockTileAdvanced(P blockType, Block.Properties properties) {
        super(properties);
        this.blockType = blockType;
        blockType.getGroovyMachineProperties().setBlock(this);

        this.hasTER = blockType instanceof IBlockTypeTER;
        this.hasTERFast = blockType instanceof IBlockTypeTERFast;
        properties.lightValue(!hasTERFast && !hasTER ? 255 : 0);

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    private MachinePropertyTraits getDefinition() {
        return blockType.getGroovyMachineProperties();
    }

    @Override
    boolean isVariableOpacity() {
        return !hasTERFast && !hasTER;
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
        MachinePropertyTraits definition = getDefinition();
        definition.initModel();
    }

    void registerAdvancedTileEntity() {
        MachinePropertyTraits definition = getDefinition();
        definition.registerTileEntity();
    }

    @Override
    StateContainer<Block, BlockState> getStateContainer() {
        return this.stateContainer;
    }

    @Nullable
    AxisAlignedBB getCollisionBoundingBox(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, blockState);
    }

    @OnlyIn(Dist.CLIENT)
    AxisAlignedBB getSelectedBoundingBox(BlockState state, World worldIn, BlockPos pos) {
        MachinePropertyTraits definition = getDefinition();
        return definition.getBoundingBox(worldIn, pos, state).offset(pos);
    }

    @OnlyIn(Dist.CLIENT)
    RayTraceResult collisionRayTrace(BlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        MachinePropertyTraits definition = getDefinition();
        return definition.collisionRayTrace(worldIn, pos, blockState, start, end);
    }
}
