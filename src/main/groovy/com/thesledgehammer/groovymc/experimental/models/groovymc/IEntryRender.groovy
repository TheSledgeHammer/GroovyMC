package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nonnull

@SideOnly(Side.CLIENT)
interface IEntryRender<M extends ModelEntryHolder, T extends TileEntity> {

    void onModelBake(ModelBakeEvent event);

    void render(@Nonnull T tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer);
}