package com.thesledgehammer.groovymc.experimental.jsons

class GroovyVariableDefinition {

    private GroovysonVariableModel model;
    private GroovysonVariableCuboid cuboid;

    GroovyVariableDefinition() {
        setVariableModel(null);
    }

    GroovyVariableDefinition(GroovysonVariableModel model) {
        setVariableModel(model);
    }

    void setVariableModel(GroovysonVariableModel model) {
        this.model = model;
    }

    void setVariableCuboid(GroovysonVariableCuboid cuboid) {
        this.cuboid = cuboid;
    }

    GroovysonVariableCuboid getVariableCuboid() {
        return cuboid;
    }
}
