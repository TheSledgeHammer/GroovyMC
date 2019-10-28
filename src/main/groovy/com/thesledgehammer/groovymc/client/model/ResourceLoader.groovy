package com.thesledgehammer.groovymc.client.model

import groovy.json.JsonException
import net.minecraft.client.Minecraft
import net.minecraft.resources.IResource
import net.minecraft.util.ResourceLocation

import java.nio.charset.StandardCharsets
//Temporary: Referenced from Buildcraft's ResourceLoaderContext
//Likely to Change or be Refactored
class ResourceLoader {

    private final Set<ResourceLocation> loaded = new HashSet<>();
    private final Deque<ResourceLocation> loadingStack = new ArrayDeque<>();

    InputStreamReader startLoading(ResourceLocation location) throws IOException {
        if (!loaded.add(location)) {
            throw new JsonException("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res =  Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    InputStreamReader startLoading(String namespace, String path) throws IOException {
        ResourceLocation location = new ResourceLocation(namespace, path);
        if (!loaded.add(location)) {
            throw new JsonException("Already loaded " + location + " from " + loadingStack.peek())
        }
        loadingStack.push(location);
        IResource res = Minecraft.getInstance().getResourceManager().getResource(location);
        return new InputStreamReader(res.getInputStream(), StandardCharsets.UTF_8);
    }

    void finishLoading() {
        loadingStack.pop();
    }
}
