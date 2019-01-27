/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IStringSerializable
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraftforge.client.model.animation.FastTESR
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraits<T extends GroovyTileBasic> implements IStringSerializable {

    @Nullable
    private Block block;
    private String name;
    private Class<T> teClass;
    private AxisAlignedBB boundingBox;
    private RayTraceResult rayTraceResult;
    private boolean isFullCube;
    private String particleTextureLocation;

    @Nullable
    @SideOnly(Side.CLIENT)
    private FastTESR<? super T> rendererFast;

    @Nullable
    @SideOnly(Side.CLIENT)
    private TileEntitySpecialRenderer<? super T> renderer;

    void setBlock(Block block) {
        this.block = block;
    }

    void setName(String name) {
        this.name = name;
    }

    void setTeClass(Class<T> teClass) {
        this.teClass = teClass;
    }

    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    void setIsFullCube(boolean isFullCube) {
        this.isFullCube = isFullCube;
    }

    void setAxisAlignedBB(AxisAlignedBB boundingBox) {
        this.boundingBox = boundingBox;
    }

    void setRayTraceResult(RayTraceResult rayTraceResult) {
        this.rayTraceResult = rayTraceResult;
    }

    @SideOnly(Side.CLIENT)
    void setRenderer(FastTESR<? super T> rendererFast) {
        this.rendererFast = rendererFast;
    }

    @SideOnly(Side.CLIENT)
    void setRenderer(TileEntitySpecialRenderer<? super T> renderer) {
        this.renderer = renderer;
    }

    @Nullable
    Block getBlock() {
        return block;
    }

    String getName() {
        return name;
    }

    Class<T> getTeClass() {
        return teClass;
    }

    String getParticleTextureLocation() {
        return particleTextureLocation;
    }

    boolean getIsFullCube() {
        return isFullCube;
    }

    boolean isFullCube(IBlockState state) {
        return true;
    }

    AxisAlignedBB getBoundingBox(BlockPos pos, IBlockState state) {
        return boundingBox;
    }

    @Nullable
    RayTraceResult collisionRayTrace(World world, BlockPos pos, Vec3d startVec, Vec3d endVec) {
        return rayTrace(pos, start, end, blockState.getBoundingBox(world, pos));
    }

    @Nullable
    private RayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
        Vec3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        Vec3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
        return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.addVector((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), raytraceresult.sideHit, pos);
    }

    @SideOnly(Side.CLIENT)
    void initModel() {

    }

    void registerTileEntity() {
        registerTile(teClass, name);
    }

    void registerTileEntity(String modID) {
        registerTile(teClass, modID, name);
    }

    TileEntity createTileEntity() {
        try {
            return teClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to instantiate tile entity of class " + teClass.getName(), e);
        }
    }

    private static void registerTile(Class<? extends TileEntity> tileClass, String location) {
        GameRegistry.registerTileEntity(tileClass, new ResourceLocation(GroovyLoader.Instance().getModID(), location));
    }

    private static void registerTile(Class<? extends TileEntity> tileClass, String modID, String location) {
        GameRegistry.registerTileEntity(tileClass, new ResourceLocation(modID, location));
    }
}