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
import net.minecraft.util.math.RayTraceResult

class GroovyMachineProperties<T extends GroovyTileBasic> implements MachinePropertyTraits<T> {

    GroovyMachineProperties(Class<T> teClass, String name) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(new AxisAlignedBB(0, 0, 0, 1, 1, 1));
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
    }
/*
    GroovyMachineProperties(Class<T> teClass, String name, String particleTextureLocation) {
        setTeClass(teClass);
        setName(name);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(true);
    }

    GroovyMachineProperties(Class<T> teClass, String name, String particleTextureLocation, boolean isFullCube) {
        setTeClass(teClass);
        setName(name);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(isFullCube);
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(true);
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox, String particleTextureLocation, boolean isFullCube) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(isFullCube);
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox, RayTraceResult rayTrace, String particleTextureLocation) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
        setRayTraceResult(rayTrace);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(true);
    }

    GroovyMachineProperties(Class<T> teClass, String name, AxisAlignedBB boundingBox, RayTraceResult rayTrace, String particleTextureLocation, boolean isFullCube) {
        setTeClass(teClass);
        setName(name);
        setAxisAlignedBB(boundingBox);
        setRayTraceResult(rayTrace);
        setParticleTextureLocation(particleTextureLocation);
        setIsFullCube(isFullCube);
    }
    */
}
