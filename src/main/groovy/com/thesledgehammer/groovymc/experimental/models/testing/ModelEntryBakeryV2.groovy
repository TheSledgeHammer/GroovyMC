package com.thesledgehammer.groovymc.experimental.models.testing

import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryConsumer
import net.minecraft.util.ResourceLocation

abstract class ModelEntryBakeryV2<M extends ModelEntryV2, T extends TextureEntryV2> extends ModelEntryConsumer {

    private M modelEntry;
    private T textureEntry;

    ModelEntryBakeryV2() {
        super();
    }

    private ModelEntryBakeryV2(T textureEntry, M modelEntry) {
        this();
        this.textureEntry = textureEntry;
        this.modelEntry = modelEntry;
    }

    abstract boolean hasBakedQuads();

    abstract void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites);

    protected abstract void onModelBake();
}
