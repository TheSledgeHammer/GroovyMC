package com.thesledgehammer.groovymc.experimental.models

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.IResource
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation

import java.nio.charset.StandardCharsets
//Work In Progress
class GroovysonObjectModelParser {

    private IResourceManager manager;
    private InputStreamReader objStream;

    GroovysonObjectModelParser(IResource from, IResourceManager manager) {
        this.manager = manager;
        this.objStream = startLoader(from.getResourceLocation());//new InputStreamReader(from.getInputStream(), StandardCharsets.UTF_8);
    }

    InputStreamReader startLoader(ResourceLocation location) throws IOException {
        IResource res = Minecraft.getMinecraft().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }
}
