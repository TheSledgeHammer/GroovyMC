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


import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import com.thesledgehammer.groovymc.utils.VoxelShapeTools
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

trait MachinePropertyTraits<T extends GroovyTileBasic> implements IMachineProperties<T> {

    @Nullable
    private Block block;
    private String name;
    private Class<T> teClass;
    private AxisAlignedBB boundingBox;
    private RayTraceResult rayTraceResult;
    private boolean isFullCube;

    @Nullable
    private Block.Properties blockProperties;

    @Override
    void setBlock(Block block) {
        this.block = block;
    }

    @Override
    void setName(String name) {
        this.name = name;
    }

    @Override
    void setTeClass(Class<T> teClass) {
        this.teClass = teClass;
    }

    @Override
    void setIsFullCube(boolean isFullCube) {
        this.isFullCube = isFullCube;
    }

    @Override
    void setAxisAlignedBB(AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    void setRayTraceResult(RayTraceResult rayTraceResult) {
        this.rayTraceResult = rayTraceResult;
    }

    @Override
    Class<T> getTeClass() {
        return teClass;
    }

    @Nullable
    @Override
    Block getBlock() {
        return block;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    boolean getIsFullCube() {
        return isFullCube;
    }

    @Override
    boolean isFullCube(BlockState state) {
        return true;
    }

    @Override
    AxisAlignedBB getBoundingBox(IBlockReader world, BlockPos pos, BlockState state) {
        return boundingBox;
    }

    @Override
    @Nullable
    RayTraceResult collisionRayTrace(World world, BlockPos pos, BlockState state, Vec3d startVec, Vec3d endVec) {
        return VoxelShapeTools.rayTrace(pos, startVec, endVec, boundingBox);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel() {

    }

    @Override
    void registerTileEntity() {

    }

    @Override
    void registerTileEntity(String modID) {

    }

    @Override
    void registerTileEntity(TileEntity tile) {
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    TileEntity createNewTileEntity() {
        try {
            return teClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate tile entity of class " + teClass.getName(), e);
        }
    }
}
