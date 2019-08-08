package com.thesledgehammer.groovymc.experimental.models.groovymc


import com.thesledgehammer.groovymc.client.model.ModelEntryHolderVariable
import com.thesledgehammer.groovymc.test.objects.TestTile
import net.minecraft.client.renderer.BufferBuilder
import net.minecraftforge.client.event.ModelBakeEvent

//WORK IN PROGRESS, Example of possible implementation
//Using a single render for multiple tile/ blocks.
enum RenderEntryImpl2 implements IEntryRender<ModelEntryHolderVariable, TestTile> {
    INSTANCE;

    @Override
    void onModelBake(ModelBakeEvent event) {

    }

    @Override
    void render(TestTile tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

    }
}