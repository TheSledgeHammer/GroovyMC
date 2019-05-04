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

package com.thesledgehammer.groovymc.client.render.keys

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel

class GroovyRenderKeyDefinition {

    private static GroovyRenderKeyDefinition instance;
    private CutoutKey cutoutKey;
    private CutoutMippedKey cutoutMippedKey;
    private SolidKey solidKey;
    private TranslucentKey translucentKey;
    private GroovysonModel groovysonModel;

    GroovyRenderKeyDefinition() {
        setGroovysonModel(null);
        instance = this;
    }

    GroovyRenderKeyDefinition(GroovysonModel groovysonModel) {
        setGroovysonModel(groovysonModel);
        instance = this;
    }

    static GroovyRenderKeyDefinition Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    CutoutKey getCutoutKey() {
        return cutoutKey;
    }

    CutoutMippedKey getCutoutMippedKey() {
        return cutoutMippedKey;
    }

    SolidKey getSolidKey() {
        return solidKey;
    }

    TranslucentKey getTranslucentKey() {
        return translucentKey;
    }

    private void setGroovysonModel(GroovysonModel groovysonModel) {
        this.groovysonModel = groovysonModel;
    }

    void setCutoutKey(CutoutKey cutoutKey) {
        this.cutoutKey = cutoutKey;
    }

    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        this.cutoutMippedKey = cutoutMippedKey;
    }


    void setSolidKey(SolidKey solidKey) {
        this.solidKey = solidKey;
    }

    void setTranslucentKey(TranslucentKey translucentKey) {
        this.translucentKey = translucentKey;
    }
}
