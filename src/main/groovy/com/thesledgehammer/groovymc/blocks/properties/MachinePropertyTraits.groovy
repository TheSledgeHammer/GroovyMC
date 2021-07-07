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

package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolderManager
import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
<<<<<<< HEAD
import com.thesledgehammer.groovymc.utils.TileEntityTools
=======
>>>>>>> 1.16.x
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
<<<<<<< HEAD
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
=======
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
>>>>>>> 1.16.x

import javax.annotation.Nullable

trait MachinePropertyTraits<T extends GroovyTileBasic> implements IMachineProperties<T> {

    @Nullable
    private Block block;
    private String name;
    private TileEntityType<? extends T> teType;
    private boolean isFullCube;
    private VoxelShape shape;

    @Override
    void setBlock(Block block) {
        this.block = block;
    }

    @Override
    void setName(String name) {
        this.name = name;
    }

    @Override
    void setTeType(TileEntityType<? extends T> teType) {
        this.teType = teType;
    }

    @Override
    void setVoxelShape(VoxelShape shape) {
        this.shape = shape;
    }

    @Override
    void setIsFullCube(boolean isFullCube) {
        this.isFullCube = isFullCube;
    }

    @Nullable
    @Override
    Block getBlock() {
        return block;
    }

    @Override
    String getString() {
        return name;
    }

    @Override
    TileEntityType<? extends T> getTeType() {
        return teType;
    }

    @Override
    VoxelShape getShape() {
        return shape;
    }

    @Override
    boolean isFullCube(BlockState state) {
        return isFullCube;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel() {
<<<<<<< HEAD
        ModelEntryHolderManager.Instance().initModel(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    @Override
    void registerTileEntity() {
        TileEntityTools.registerTileEntity(teClass, name);
    }

    @Override
    void registerTileEntity(String modID) {
        TileEntityTools.registerTileEntity(teClass, modID, name);
    }

    @Override
    TileEntity CreateTileEntity() {
        try {
            return teClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate tile entity of class " + teClass.getName(), e);
        }
=======

    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    TileEntity createNewTileEntity() {
        return teType.create();
>>>>>>> 1.16.x
    }
}
