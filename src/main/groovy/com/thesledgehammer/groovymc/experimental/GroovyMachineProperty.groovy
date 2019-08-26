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
import net.minecraft.util.math.AxisAlignedBB

class GroovyMachineProperties<T extends GroovyTileBasic> implements MachinePropertyTraits<T> {

    GroovyMachineProperties(Class<T> teClass, String name) {
        this(teClass, name, new AxisAlignedBB(0, 0, 0, 1, 1, 1))
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
    }

    static class WithTER<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTER<T> {

        WithTER(Class<T> teClass, String name, String particleTextureLocation) {
            this(teClass, name, particleTextureLocation, true);
        }

        WithTER(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
            this(teClass, name, boundingBox, particleTextureLocation, true);
        }

        WithTER(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTER(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name, boundingBox);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }

    static class WithTERFast<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTERFast<T> {

        WithTERFast(Class<T> teClass, String name, String particleTextureLocation) {
            this(teClass, name, particleTextureLocation, true);
        }

        WithTERFast(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
            this(teClass, name, boundingBox, particleTextureLocation, true);
        }

        WithTERFast(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTERFast(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name, boundingBox);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }

    static class WithTERAnimation<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTERAnimation<T> {

        WithTERAnimation(Class<T> teClass, String name, String particleTextureLocation) {
            this(teClass, name, particleTextureLocation, true);
        }

        WithTERAnimation(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
            this(teClass, name, boundingBox, particleTextureLocation, true);
        }

        WithTERAnimation(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTERAnimation(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name, boundingBox);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }
}
