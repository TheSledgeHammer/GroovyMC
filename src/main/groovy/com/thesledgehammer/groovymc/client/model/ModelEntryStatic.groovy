package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.ModelEntryBakery
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import net.minecraft.util.ResourceLocation

class ModelEntryStatic extends ModelEntryBakery<ModelEntry, TextureEntry> {

    ModelEntryStatic() {

    }

    @Override
    protected void onModelBake() {

    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {

    }

    @Override
    boolean hasBakedQuads() {
        return false
    }
}
