package com.thesledgehammer.groovymc.client.definitions

import net.minecraft.util.ResourceLocation

abstract class ModelEntryBakery<M extends ModelEntry, T extends TextureEntry> extends ModelEntryConsumer {

    private M modelEntry;
    private T textureEntry;

    ModelEntryBakery(GroovyDefinitionContext GDC) {
        super(GDC);
    }

    ModelEntryBakery(GroovyResourceDefinition resources, GroovyModelDefinition models) {
        super(resources, models);
    }

    ModelEntryBakery() {
        super();
    }

    private ModelEntryBakery(T textureEntry, M modelEntry, GroovyDefinitionContext GDC) {
        this(GDC);
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    private ModelEntryBakery(T textureEntry, M modelEntry, GroovyResourceDefinition resources, GroovyModelDefinition models) {
        this(resources, models);
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    private ModelEntryBakery(T textureEntry, M modelEntry) {
        this();
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    protected abstract void onModelBake();

    abstract void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites);

    abstract boolean hasBakedQuads();
}
