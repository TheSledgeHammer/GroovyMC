package com.thesledgehammer.groovymc.fluids

import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.utils.GroovyLoader
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
        ResourceLocation still = new ResourceLocation(modID, texturePath + fluidName + "_flow");
        return still;
    }

    private static ResourceLocation FluidStill(String texturePath, String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), texturePath + fluidName + "_still");
        return still;
    }

    private static ResourceLocation FluidFlowing(String texturePath, String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), texturePath + fluidName + "_flow");
        return still;
    }

    private static ResourceLocation FluidStill(String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_FLUIDS + fluidName + "_still");
        return still;
    }

    private static ResourceLocation FluidFlowing(String fluidName) {
        ResourceLocation still = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_FLUIDS + fluidName + "_flow");
        return still;
    }
}
