/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.api.client.json

import groovy.json.JsonException
import net.minecraft.util.ResourceLocation

class GroovyResourceLoaderContext {

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
