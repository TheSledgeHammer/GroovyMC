package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.blocks.properties.GroovyMachineProperties
import com.thesledgehammer.groovymc.blocks.properties.IBlockTypeFastTESR
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraitsFastTESR
import com.thesledgehammer.groovymc.client.model.ModelEntryHolderVariable
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.test.objects.TestTile
import net.minecraft.client.renderer.BufferBuilder
import net.minecraftforge.client.event.ModelBakeEvent

//WORK IN PROGRESS, Example of possible implementation
//Using a single render for multiple tile/ blocks.
enum RenderEntryImpl2 implements IEntryRender<ModelEntryHolderVariable, TestTile>, IBlockTypeFastTESR {
    INSTANCE;

    private final MachinePropertyTraitsFastTESR<?> machinePropertyTraits;

    RenderEntryImpl2(MachinePropertyTraitsFastTESR machinePropertyTraits) {
        this.machinePropertyTraits = machinePropertyTraits;
    }

    protected static MachinePropertyTraitsFastTESR<?> createRenderProperties(Class<? extends TestTile> teClass, String name, String loc) {
        MachinePropertyTraitsFastTESR<? extends TestTile> renderProperties = new GroovyMachineProperties.WithFastTESR(teClass, name, loc);
        return renderProperties;
    }

    @Override
    void onModelBake(ModelBakeEvent event) {

    }

    @Override
    void render(TestTile tile, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {

    }

    @Override
    MutableQuad[] getQuads(ModelEntryHolderVariable modelEntryHolder, TestTile tile, float partialTicks) {
        return new MutableQuad[0]
    }

    @Override
    MachinePropertyTraitsFastTESR getGroovyMachineProperties() {
        return machinePropertyTraits
    }

    @Override
    String getName() {
        return getGroovyMachineProperties().getName();
    }
}