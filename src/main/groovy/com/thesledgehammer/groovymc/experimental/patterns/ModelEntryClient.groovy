package com.thesledgehammer.groovymc.experimental.patterns

class ModelEntry extends ModelEntryController {

    ModelEntry(GroovyMVC groovyMVC) {
        super(groovyMVC);
    }

    ModelEntry(GroovyResources resources, GroovyModels models) {
        super(resources, models);
    }

    ModelEntry() {
        super();
    }
}
