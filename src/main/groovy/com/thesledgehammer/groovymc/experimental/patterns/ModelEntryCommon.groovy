package com.thesledgehammer.groovymc.experimental.patterns

abstract class ModelEntryController {

    private GroovyMVC groovyMVC;

    ModelEntryController(GroovyMVC groovyMVC) {
        this.groovyMVC = groovyMVC;
    }

    ModelEntryController(GroovyResources resources, GroovyModels models) {
        groovyMVC = new GroovyMVC(resources, models);
    }

    ModelEntryController() {
        groovyMVC = new GroovyMVC(new GroovyResources(), new GroovyModels());
    }

    GroovyMVC GroovyMVC() {
        return groovyMVC;
    }
}
