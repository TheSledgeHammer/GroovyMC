package com.thesledgehammer.groovymc.experimental.patterns

import net.minecraft.client.renderer.block.model.IBakedModel

class ModelEntryBaker extends ModelEntry {

    ModelEntryBaker(String modelLocation, IBakedModel bakedModel) {
        this.GroovyMVC().setModelResourceLocation(modelLocation);
        this.GroovyMVC().setIBakedModel(bakedModel);
    }
}
