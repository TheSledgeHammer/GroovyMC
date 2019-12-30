package com.thesledgehammer.groovymc.api.client.json

import groovy.json.JsonException
import net.minecraft.util.ResourceLocation

class GroovysonLoaderContext {

    static def deserializeJson(ResourceLocation location) {
        return deserializeJson(location, new ResourceLoader());
    }

    static def deserializeXml(ResourceLocation location) {
        return deserializeXml(location, new ResourceLoader());
    }

    static def deserializeJson(String domain, String path) {
        return deserializeJson(domain, path, new ResourceLoader());
    }

    static def deserializeXml(String domain, String path) {
        return deserializeXml(domain, path, new ResourceLoader());
    }

    protected static def deserializeJson(ResourceLocation location, ResourceLoader ctx) throws JsonException, IOException {
        try {
            return GroovysonReader.JsonSlurpy(ctx.startLoading(location));
        } finally {
            ctx.finishLoading();
        }
    }

    protected static def deserializeXml(ResourceLocation location, ResourceLoader ctx) throws Exception, IOException {
        try {
            return GroovysonReader.XmlSlurpy(ctx.startLoading(location));
        } finally {
            ctx.finishLoading();
        }
    }

    protected static def deserializeJson(String domain, String path, ResourceLoader ctx) throws JsonException, IOException {
        try {
            return GroovysonReader.JsonSlurpy(ctx.startLoading(domain, path));
        } finally {
            ctx.finishLoading();
        }
    }

    protected static def deserializeXml(String domain, String path, ResourceLoader ctx) throws Exception, IOException {
        try {
            return GroovysonReader.XmlSlurpy(ctx.startLoading(domain, path));
        } finally {
            ctx.finishLoading();
        }
    }
}
