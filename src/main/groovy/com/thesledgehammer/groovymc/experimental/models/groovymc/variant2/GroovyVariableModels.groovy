package com.thesledgehammer.groovymc.experimental.models.groovymc.variant2

import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel

class GroovyVariableModels {

    private GroovyVariableModel block;
    private GroovyVariableModel item;

    GroovyVariableModels(String fileName) {
        this.block = new GroovyVariableModel("block", fileName)
        this.item = new GroovyVariableModel("item", fileName)
    }

    GroovyVariableModels(String resourceDirectory, String modID, String fileName) {
        this.block = new GroovyVariableModel(resourceDirectory, modID, "block", fileName)
        this.item = new GroovyVariableModel(resourceDirectory, modID, "item", fileName)
    }

    GroovyVariableModel getBlockModel() {
        return block;
    }

    GroovyVariableModel getItemModel() {
        return item;
    }
}
