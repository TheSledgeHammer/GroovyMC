package com.thesledgehammer.groovymc.utils

import net.minecraft.block.BlockState
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes
import net.minecraft.world.IBlockReader

//BlockState: contains methods related to renderShape, collisionShape, RaytraceShape
class VoxelShapeTools {
/*
    @Nullable
    static BlockRayTraceResult rayTrace(BlockPos pos, Vector3d start, Vector3d end, AxisAlignedBB boundingBox) {
        return rayTrace(pos, start, end, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
    }

    @Nullable
    static BlockRayTraceResult rayTrace(BlockPos pos, Vector3d start, Vector3d end, double x1, double y1, double z1, double x2, double y2, double z2) {
        Tuple3d vec3d = start.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        Tuple3d vec3d1 = end.subtract((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        BlockRayTraceResult rayTrace = VoxelShapes.create(x1, y1, z1, x2, y2, z2).rayTrace(vec3d, vec3d1, pos);
        return rayTrace == null ? null : new BlockRayTraceResult(rayTrace.getHitVec().add((double) pos.getX(), (double) pos.getY(), (double) pos.getZ()), rayTrace.getFace(), rayTrace.getPos(), rayTrace.isInside());
    }
    */
    private VoxelShape shape;

    VoxelShapeTools(VoxelShape shape) {
        this.shape = shape;
    }

    void setVoxelShape(VoxelShape shape) {
        this.shape = shape;
    }

    static VoxelShape createShape(double x1, double y1, double z1, double x2, double y2, double z2) {
        return VoxelShapes.create(x1, y1, z1, x2, y2, z2);
    }

    static VoxelShape createShape(AxisAlignedBB boundingBox) {
        return VoxelShapes.create(boundingBox);
    }

    VoxelShape getShape() {
        return shape;
    }

    static VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getCollisionShape(worldIn, pos);
    }

    static VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getCollisionShape(worldIn, pos, context);
    }

    static VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getRenderShape(worldIn, pos);
    }

    static VoxelShape getRaytraceShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.getRayTraceShape(reader, pos);
    }
}
