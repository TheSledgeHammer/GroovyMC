package com.thesledgehammer.groovymc.utils

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.renderer.model.ModelResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
interface IStateMapper {

    Map<BlockState, ModelResourceLocation> putStateModelLocations(Block blockIn);
}