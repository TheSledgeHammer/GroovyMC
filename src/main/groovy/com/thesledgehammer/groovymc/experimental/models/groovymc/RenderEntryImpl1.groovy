package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.client.model.ModelEntryHolderVariable
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.test.objects.TestTile
import net.minecraft.client.renderer.BufferBuilder
import net.minecraftforge.client.event.ModelBakeEvent

import javax.annotation.Nonnull

//WORK IN PROGRESS, Example of possible implementation
//Using a single render for multiple tile/ blocks.
class RenderEntryImpl1 extends RenderEntry<ModelEntryHolderVariable, TestTile> {

    //static RenderEntryImpl1 INSTANCE = new RenderEntryImpl1();
    static ModelEntryHolderVariable model;

    RenderEntryImpl1(String modID, String eventName, ModelEntryHolderVariable model) {
        super(modID, eventName, model)
    }

    @Override
    void onModelBake(ModelBakeEvent event) {

    }

    @Override
    void render(@Nonnull TestTile tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

    }

    @Override
    MutableQuad[] getQuads(ModelEntryHolderVariable modelEntryHolder, TestTile tile, float partialTicks) {
        return new MutableQuad[0]
    }
}
