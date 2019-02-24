package com.thesledgehammer.groovymc.client.definitions

abstract class ModelEntryConsumer extends ModelEntryProvider {

    ModelEntryConsumer(GroovyDefinitionContext GDC) {
        super(GDC);
    }

    ModelEntryConsumer(GroovyResourceDefinition resources, GroovyModelDefinition models) {
        super(resources, models);
    }

    ModelEntryConsumer() {
        super();
    }
}
