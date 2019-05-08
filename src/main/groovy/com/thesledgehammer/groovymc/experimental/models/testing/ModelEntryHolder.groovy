package com.thesledgehammer.groovymc.experimental.models.testing

abstract class ModelEntryHolder extends ModelEntryBakeryV2<ModelEntryV2, TextureEntryV2> {

    private ModelEntryV2 modelEntry
    private TextureEntryV2 textureEntry;

    ModelEntryHolder() {
        modelEntry = ModelEntryV2.Instance();
        textureEntry = TextureEntryV2.Instance();
        ModelEntryHolderRegistry.HOLDERS.add(this);
    }

    ModelEntryV2 ModelEntry() {
        return modelEntry
    }

    TextureEntryV2 TextureEntry() {
        return textureEntry;
    }
}
