package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.MutableQuad
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.event.ModelBakeEvent

import javax.annotation.Nonnull

interface IRenderEntry<M extends ModelEntryHolder, T extends TileEntity> {

    void setModelEntryHolder(M model);

    void setTileEntity(T tileEntity);

    void setQuads(MutableQuad[] quads);

    M getModelEntryHolder();

    T getTileEntity();

    MutableQuad[] getQuads();

    void onModelBake(ModelBakeEvent event);

    void render(@Nonnull T tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer);
/*
    MutableQuad[] getQuads(MutableQuad[] quads, T tile, float partialTicks);

    MutableQuad[] getQuads(M modelEntryHolder, T tile, float partialTicks);
*/
}