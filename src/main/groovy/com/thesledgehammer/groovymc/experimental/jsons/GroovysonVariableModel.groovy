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

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.json.GroovysonAbstractModel
import com.thesledgehammer.groovymc.experimental.variables.*
import com.thesledgehammer.groovymc.utils.StringTools

class GroovysonVariableModel extends GroovysonAbstractModel {

    GroovysonVariableModel(String resourceObject, String fileName) {
        super(resourceObject, fileName)
    }

    GroovysonVariableModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, resourceObject, fileName)
    }

    boolean Shade(boolean fallback) {
        if(GroovysonObject().getShade() == null) {
            return fallback;
        }
        return GroovysonObject().getShade();
    }

    boolean AmbientOcclusion() {
        return GroovysonObject().AmbientOcclusion();
    }

    String Visible(int index) {
        String name = getVisible(index).toString();
        if(isVariable(name)) {
            return getVariable(name);
        }
        return getVisible(index);
    }

    String Light(int index) {
        String name = getLight(index).toString()
        if(isVariable(name)) {
            return getVariable(name);
        }
        return getLight(index);
    }

    String Colour(int index) {
        String name = getColour(index).toString()
        if(isVariable(name)) {
            return getVariable(name);
        }
        return getColour(index);
    }

    String getVariable(String variableName) {
        if(isVariable(variableName)) {
            return getVariableByName(variableName);
        }
        return null;
    }

    private boolean isVariable(String variableName) {
        if(getVariableByName(variableName) != null) {
            return true;
        }
        return false;
    }

    private String getVisible(int index) {
        return getRawModelPart(index).Visible();
    }

    private String getLight(int index) {
        return getRawModelPart(index).Light();
    }

    private String getColour(int index) {
        return getRawModelPart(index).Colour();
    }
}
