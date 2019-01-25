/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.world


import com.thesledgehammer.groovymc.GroovyMC
import net.minecraft.world.DimensionType
import net.minecraftforge.common.DimensionManager

class ModDimensions {

    static DimensionType testDimensionType;
    static int dimensionID = 100;

    static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(GroovyMC.MOD_ID, "_test", dimensionID, TestWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(dimensionID, testDimensionType);
    }
}
