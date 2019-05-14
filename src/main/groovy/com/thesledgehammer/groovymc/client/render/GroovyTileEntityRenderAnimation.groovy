package com.thesledgehammer.groovymc.client.render

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.animation.TileEntityRendererAnimation

@OnlyIn(Dist.CLIENT)
abstract class GroovyTileEntityRenderAnimation<T extends TileEntity> extends TileEntityRendererAnimation<T> {

    GroovyTileEntityRenderAnimation() {

    }
}
