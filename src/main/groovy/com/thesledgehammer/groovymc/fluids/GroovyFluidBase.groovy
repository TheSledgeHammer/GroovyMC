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

package com.thesledgehammer.groovymc.fluids

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

class GroovyFluidBase extends Fluid {

    GroovyFluidBase(String modID, String texturePath, String fluidName) {
        super(fluidName,
                FluidStill(modID, texturePath, fluidName),
                FluidFlowing(modID, texturePath, fluidName));
        FluidRegistry.addBucketForFluid(this);
    }

    GroovyFluidBase(String texturePath, String fluidName) {
        super(fluidName,
                FluidStill(texturePath, fluidName),
                FluidFlowing(texturePath, fluidName));
        FluidRegistry.addBucketForFluid(this);
    }

    GroovyFluidBase(String fluidName) {
        super(fluidName,
                FluidStill(fluidName),
                FluidFlowing(fluidName));
        FluidRegistry.addBucketForFluid(this);
    }

    private static ResourceLocation FluidStill(String modID, String texturePath, String fluidName) {
        ResourceLocation still = new ResourceLocation(modID, texturePath + fluidName + "_still");
        return still;
    }

    private static ResourceLocation FluidFlowing(String modID, String texturePath, String fluidName) {
        ResourceLocation flow = new ResourceLocation(modID, texturePath + fluidName + "_flow");
        return flow;
    }

    private static ResourceLocation FluidStill(String texturePath, String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), texturePath + fluidName + "_still");
        return still;
    }

    private static ResourceLocation FluidFlowing(String texturePath, String fluidName) {
        ResourceLocation flow = new ResourceLocation(GroovyLoader.Instance().getModID(), texturePath + fluidName + "_flow");
        return flow;
    }

    private static ResourceLocation FluidStill(String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_FLUIDS + fluidName + "_still");
        return still;
    }

    private static ResourceLocation FluidFlowing(String fluidName) {
        ResourceLocation flow = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_FLUIDS + fluidName + "_flow");
        return flow;
    }
}
