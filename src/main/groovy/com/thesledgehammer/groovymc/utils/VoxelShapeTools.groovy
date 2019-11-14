package com.thesledgehammer.groovymc.utils

import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes

import javax.annotation.Nullable

//BlockState: contains methods related to renderShape, collisionShape, RaytraceShape
class VoxelShapeTools {
/*
    private VoxelShape shape;

    VoxelShapeTools(VoxelShape shape) {
        this.shape = shape;
    }
    */

    @Nullable
    static BlockRayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
        return rayTrace(pos, start, end, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
    }

    @Nullable
    static BlockRayTraceResult rayTrace(BlockPos pos, Vec3d start, Vec3d end, double x1, double y1, double z1, double x2, double y2, double z2) {
        Vec3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        Vec3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        BlockRayTraceResult rayTrace = VoxelShapes.create(x1, y1, z1, x2, y2, z2).rayTrace(vec3d, vec3d1, pos);
        return rayTrace == null ? null : new BlockRayTraceResult(rayTrace.getHitVec().add((double) pos.getX(), (double) pos.getY(), (double) pos.getZ()), rayTrace.getFace(), rayTrace.getPos(), rayTrace.isInside());
    }
/*
    void setVoxelShape(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.shape = VoxelShapes.create(x1, y1, z1, x2, y2, z2);
    }

    VoxelShape getShape() {
        return shape;
    }

    VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getCollisionShape(worldIn, pos);
    }

    VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getCollisionShape(worldIn, pos, context);
    }

    VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getRenderShape(worldIn, pos);
    }

    VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getRaytraceShape(worldIn, pos);
    }
    */
}
