package com.thesledgehammer.groovymc.experimental

import groovy.json.JsonSlurper
import net.minecraft.util.ResourceLocation

class GroovyJson implements IInterpreter {

    private String path;
    private ResourceLocation resourceLocation;
    private JsonSlurper jsonSlurper;
    private GroovyObject groovyobj;

    GroovyJson(String file) {
        this.jsonSlurper = new JsonSlurper();
        setGroovyObject(file);
    }

    JsonSlurper getJsonSlurper() {
        return jsonSlurper;
    }

    @Override
    GroovyObject getGroovyObject() {
        return groovyobj;
    }

    @Override
    ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    String getPath() {
        return path;
    }

    @Override
    void setResourceLocation(String path, ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        this.path = "${path}/${resourceLocation.getNamespace()}/${resourceLocation.getPath()}";
    }

    @Override
    void setGroovyObject(String file) {
        this.groovyobj = jsonSlurper.parse(Deserialize(file)) as GroovyObject;
    }

    @Override
    FileReader Deserialize(String filename) {
        return new FileReader(filename);
    }

    @Override
    FileWriter Serialize(String filename) {
        return new FileWriter(filename);
    }
}
