package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import net.minecraft.util.ResourceLocation

abstract class ModelEntryProvider {

    private GroovyDefinitionContext GDC;

    ModelEntryProvider(GroovyDefinitionContext GDC) {
        this.GDC = GDC;
    }

    ModelEntryProvider(GroovyResourceDefinition resources, GroovyModelDefinition models) {
        GDC = new GroovyDefinitionContext(resources, models);
    }

    ModelEntryProvider() {
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition());
    }

    GroovyDefinitionContext GroovyDefinitionContext() {
        return GDC;
    }

    protected abstract void onModelBake();

    abstract void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites);

    abstract boolean hasBakedQuads();
}
