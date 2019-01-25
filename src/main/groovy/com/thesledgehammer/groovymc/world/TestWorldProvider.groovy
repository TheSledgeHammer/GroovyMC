/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.world

import net.minecraft.world.DimensionType
import net.minecraft.world.WorldProvider
import net.minecraft.world.gen.IChunkGenerator

class TestWorldProvider extends WorldProvider {

    @Override
    DimensionType getDimensionType() {
        return ModDimensions.testDimensionType;
    }

    @Override
    String getSaveFolder() {
        return "TEST";
    }

    @Override
    IChunkGenerator createChunkGenerator() {
        return new TestChunkGenerator(world);
    }
}
