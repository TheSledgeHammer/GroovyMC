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

package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.GroovyStaticModel
import com.thesledgehammer.groovymc.client.model.GroovyVariableModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModel
import groovy.json.JsonException
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.IResource
import net.minecraft.util.ResourceLocation

import java.nio.charset.StandardCharsets

//Work In Progress
//TODO: Groovy Models Initialized through this class. i.e. LoadModel with various checks etc. Like CustomModelLoader
enum GroovyResourceParser {
    INSTANCE;

    private final Map<ResourceLocation, GroovysonObjectModel> cache = new HashMap<>();
    private final List<Boolean> type = new ArrayList<>();

    InputStreamReader load(ResourceLocation location, GroovysonObjectModel model) throws IOException {
        this.cache.put(location, model);
        if(!cache.put(location, model)) {
            throw new JsonException("Already loaded " + location + " from " + cache.get(location));
        }
        cache.get(location);
        IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(location);
        return new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    GroovysonObjectModel parse(ResourceLocation location, boolean isStatic) {
        if(isStatic) {
           return StaticModel(new GroovyStaticModel(location));
        }
        return VariableModel(new GroovyVariableModel(location));
    }

    private static GroovysonObjectModel StaticModel(GroovyStaticModel staticModel) {
        if(staticModel.getGroovysonModel() instanceof GroovysonObjectModel) {
            GroovysonObjectModel model = (GroovysonObjectModel) staticModel.getGroovysonModel();
            return model;
        }
        return null;
    }

    private static GroovysonObjectModel VariableModel(GroovyVariableModel variableModel) {
        if(variableModel.getGroovysonModel() instanceof GroovysonObjectModel) {
            GroovysonObjectModel model = (GroovysonObjectModel) variableModel.getGroovysonModel();
            return model;
        }
        return null;
    }
}
