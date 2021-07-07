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

package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.client.definitions.IRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModel

class GroovyRenderDefinition implements IRenderDefinition {

    private CutoutKey cutoutKey;
    private CutoutMippedKey cutoutMippedKey;
    private SolidKey solidKey;
    private TranslucentKey translucentKey;
    private GroovysonObjectModel groovysonModel;

    GroovyRenderDefinition() {
        setGroovysonModel(null);
    }

    GroovyRenderDefinition(GroovysonObjectModel groovysonModel) {
        setGroovysonModel(groovysonModel);
    }

    @Override
    CutoutKey getCutoutKey() {
        return cutoutKey;
    }

    @Override
    CutoutMippedKey getCutoutMippedKey() {
        return cutoutMippedKey;
    }

    @Override
    SolidKey getSolidKey() {
        return solidKey;
    }

    @Override
    TranslucentKey getTranslucentKey() {
        return translucentKey;
    }

    @Override
    void setCutoutKey(CutoutKey cutoutKey) {
        this.cutoutKey = cutoutKey;
    }

    @Override
    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        this.cutoutMippedKey = cutoutMippedKey;
    }

    @Override
    void setSolidKey(SolidKey solidKey) {
        this.solidKey = solidKey;
    }

    @Override
    void setTranslucentKey(TranslucentKey translucentKey) {
        this.translucentKey = translucentKey;
    }

    private void setGroovysonModel(GroovysonObjectModel groovysonModel) {
        this.groovysonModel = groovysonModel;
    }
}
