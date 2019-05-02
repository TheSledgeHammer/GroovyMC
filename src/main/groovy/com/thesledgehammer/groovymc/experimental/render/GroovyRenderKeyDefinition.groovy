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

package com.thesledgehammer.groovymc.experimental.render

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel

class GroovyRenderKeyDefinition {
    private CutoutKey cutoutKey;
    private CutoutMippedKey cutoutMippedKey;
    private SolidKey solidKey;
    private TranslucentKey translucentKey;
    private GroovyBaseModel groovyBaseModel;

    GroovyRenderKeyDefinition() {
        setGroovyBaseModel(null);
    }

    GroovyRenderKeyDefinition(GroovyBaseModel groovyBaseModel) {
        setGroovyBaseModel(groovyBaseModel);
    }

    GroovyBaseModel getGroovyBaseModel() {
        return groovyBaseModel;
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

    void setGroovyBaseModel(GroovyBaseModel groovyBaseModel) {
        this.groovyBaseModel = groovyBaseModel;
    }

    void setCutoutKey(CutoutKey cutoutKey) {
        this.cutoutKey = cutoutKey;
    }

    void setCutoutKey(int element) {
        this.cutoutKey = new CutoutKey(groovyBaseModel, element);
    }

    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        this.cutoutMippedKey = cutoutMippedKey;
    }

    void setCutoutMippedKey(int element) {
        this.cutoutMippedKey = new CutoutMippedKey(groovyBaseModel, element);
    }

    void setSolidKey(SolidKey solidKey) {
        this.solidKey = solidKey;
    }

    void setSolidKey(int element) {
        this.solidKey = new SolidKey(groovyBaseModel, element);
    }

    void setTranslucentKey(TranslucentKey translucentKey) {
        this.translucentKey = translucentKey;
    }

    void setTranslucentKey(int element) {
        this.translucentKey = new TranslucentKey(groovyBaseModel, element);
    }

    boolean contains(int element, String renderKeyType) {
        if(groovyBaseModel.getGroovyModel().getRawModelPart(element).BlockRenderTypes().contains(renderKeyType)) {
            return true;
        }
        return false;
    }
}
