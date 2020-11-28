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
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes

class GroovyMachineProperties<T extends GroovyTileBasic> implements MachinePropertyTraits<T> {

    GroovyMachineProperties(TileEntityType<? extends T> teType, String name) {
        this(teType, name, VoxelShapes.create(new AxisAlignedBB(0, 0, 0, 1, 1, 1)));
    }

    GroovyMachineProperties(TileEntityType<? extends T> teType, String name, VoxelShape shape) {
        setTeType(teType);
        setName(name);
        setVoxelShape(shape);
    }

    static class WithTER<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTER<T> {

        WithTER(TileEntityType<? extends T> teType, String name, String particleTextureLocation) {
            this(teType, name, particleTextureLocation, true);
        }

        WithTER(TileEntityType<? extends T> teType, String name, VoxelShape shape, String particleTextureLocation) {
            this(teType, name, shape, particleTextureLocation, true);
        }

        WithTER(TileEntityType<? extends T> teType, String name, String particleTextureLocation, boolean isFullCube) {
            super(teType, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTER(TileEntityType<? extends T> teType, String name, VoxelShape shape, String particleTextureLocation, boolean isFullCube) {
            super(teType, name, shape);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }

    static class WithTERAnimation<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTERAnimation<T> {

        WithTERAnimation(TileEntityType<? extends T> teType, String name, String particleTextureLocation) {
            this(teType, name, particleTextureLocation, true);
        }

        WithTERAnimation(TileEntityType<? extends T> teType, String name, VoxelShape shape, String particleTextureLocation) {
            this(teType, name, shape, particleTextureLocation, true);
        }

        WithTERAnimation(TileEntityType<? extends T> teType, String name, String particleTextureLocation, boolean isFullCube) {
            super(teType, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTERAnimation(TileEntityType<? extends T> teType, String name, VoxelShape shape, String particleTextureLocation, boolean isFullCube) {
            super(teType, name, shape);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }
}
