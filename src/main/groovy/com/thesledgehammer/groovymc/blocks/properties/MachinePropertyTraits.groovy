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
import com.thesledgehammer.groovymc.api.GroovyLoader
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraits<T extends GroovyTileBasic> implements IMachineProperties<T> {

    @Nullable
    private Block block;
    private String name;
    private Class<T> teClass;
    private AxisAlignedBB boundingBox;
    private RayTraceResult rayTraceResult;
    private boolean isFullCube;

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
    boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    AxisAlignedBB getBoundingBox(IBlockAccess world, BlockPos pos, IBlockState state) {
        return boundingBox;
    }

    @Override
    @Nullable
    RayTraceResult collisionRayTrace(World world, BlockPos pos, IBlockState state, Vec3d startVec, Vec3d endVec) {
        return rayTrace(pos, startVec, endVec, state.getBoundingBox(world, pos));
    }

    @Nullable
    private RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
        Vec3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        Vec3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
        return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.addVector((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), raytraceresult.sideHit, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    @Override
    void registerTileEntity() {
        registerTile(teClass, name);
    }

    @Override
    void registerTileEntity(String modID) {
        registerTile(teClass, modID, name);
    }

    @Override
    TileEntity CreateTileEntity() {
        try {
            return teClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate tile entity of class " + teClass.getName(), e);
        }
    }

    private void registerTile(Class<? extends TileEntity> tileClass, String tileName) {
        GameRegistry.registerTileEntity(tileClass, new ResourceLocation(GroovyLoader.Instance().getModID(), tileName));
    }

    private void registerTile(Class<? extends TileEntity> tileClass, String modID, String tileName) {
        GameRegistry.registerTileEntity(tileClass, new ResourceLocation(modID, tileName));
    }
}
