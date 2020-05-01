package com.thesledgehammer.groovymc.experimental

import net.minecraft.util.ResourceLocation

interface IInterpreter {

    GroovyObject getGroovyObject();

    String getPath();

    ResourceLocation getResourceLocation();

    void setResourceLocation(String path, ResourceLocation resourceLocation);

    void setGroovyObject(String file);

    FileReader Deserialize(String filename);

    FileWriter Serialize(String filename);
}