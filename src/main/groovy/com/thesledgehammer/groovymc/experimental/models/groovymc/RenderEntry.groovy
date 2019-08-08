package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.MutableQuad
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.event.ModelBakeEvent

import javax.annotation.Nonnull

abstract class RenderEntry<M extends ModelEntryHolder, T extends TileEntity> implements IRenderEntry<M, T> {

    private M modelEntry;
    private T tileEntity;
    private MutableQuad[] quads;

    RenderEntry() {

    }

    RenderEntry(MutableQuad[] quads) {
        setQuads(quads);
    }

    RenderEntry(M model, T tileEntity) {
        setModelEntryHolder(model);
        setTileEntity(tileEntity);
    }

    RenderEntry(M model, T tileEntity, MutableQuad[] quads) {
        setModelEntryHolder(model);
        setTileEntity(tileEntity);
        setQuads(quads);
    }

    @Override
    void setModelEntryHolder(M model) {
        this.modelEntry = model;
    }

    @Override
    void setTileEntity(T tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    M getModelEntryHolder() {
        return modelEntry
    }

    @Override
    T getTileEntity() {
        return tileEntity
    }

    @Override
    void setQuads(MutableQuad[] quads) {
        this.quads = quads;
    }

    @Override
    MutableQuad[] getQuads() {
        return quads;
    }

    @Override
    abstract void onModelBake(ModelBakeEvent event);

    @Override
    abstract void render(@Nonnull T tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer);
}
