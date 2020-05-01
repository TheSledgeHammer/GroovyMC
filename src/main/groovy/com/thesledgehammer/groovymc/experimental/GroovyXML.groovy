package com.thesledgehammer.groovymc.experimental

import groovy.xml.XmlSlurper
import net.minecraft.util.ResourceLocation

class GroovyXML implements IInterpreter {

    private String path;
    private ResourceLocation resourceLocation;
    private XmlSlurper xmlSlurper;
    private GroovyObject groovyobj;

    GroovyXML(String file) {
        this.xmlSlurper = new XmlSlurper();
        setGroovyObject(file);
    }

    XmlSlurper getXmlSlurper() {
        return xmlSlurper;
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
        this.groovyobj = xmlSlurper.parse(Deserialize(file));
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
