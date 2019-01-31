/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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

    static class WithTESR<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsTESR<T> {

        WithTESR(Class<T> teClass, String name, String particleTextureLocation) {
            this(teClass, name, particleTextureLocation, true);
        }

        WithTESR(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
            this(teClass, name, boundingBox, particleTextureLocation, true);
        }

        WithTESR(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithTESR(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name, boundingBox);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }

    static class WithFastTESR<T extends GroovyTileBasic> extends GroovyMachineProperties<T> implements MachinePropertyTraitsFastTESR<T> {

        WithFastTESR(Class<T> teClass, String name, String particleTextureLocation) {
            this(teClass, name, particleTextureLocation, true);
        }

        WithFastTESR(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
            this(teClass, name, boundingBox, particleTextureLocation, true);
        }

        WithFastTESR(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }

        WithFastTESR(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
            super(teClass, name, boundingBox);
            setParticleTextureLocation(particleTextureLocation);
            setIsFullCube(isFullCube);
        }
    }
}
