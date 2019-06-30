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

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.definitions.json.GroovysonStaticKey
import com.thesledgehammer.groovymc.client.definitions.json.GroovysonVariableKey
import com.thesledgehammer.groovymc.utils.variables.VariableBoolean
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.variables.VariableLong
import com.thesledgehammer.groovymc.utils.variables.VariableObject
import net.minecraft.util.EnumFacing

class GroovysonModelDefinition {

    private GroovysonStaticKey staticKey;
    private GroovysonVariableKey variableKey;

    GroovysonModelDefinition() {
        this.staticKey = new GroovysonStaticKey();
        this.variableKey = new GroovysonVariableKey();
    }

    //Static Models
    void setStaticFrom(GroovysonObjectPart part) {
        staticKey.setFrom(part)
    }

    void setStaticTo(GroovysonObjectPart part) {
        staticKey.setTo(part)
    }
    void setStaticUV(GroovysonObjectPart part) {
        staticKey.setUV(part)
    }

    void setStaticShade(GroovysonObjectPart part) {
        staticKey.setShade(part)
    }

    void setStaticVisible(GroovysonObjectPart part) {
        staticKey.setVisible(part)
    }

    void setStaticColour(GroovysonObjectPart part) {
        staticKey.setColour(part)
    }

    void setStaticLight(GroovysonObjectPart part) {
        staticKey.setLight(part)
    }

    void setStaticTexture(GroovysonObjectPart part) {
        staticKey.setTexture(part)
    }

    void setStaticRotationAngle(GroovysonObjectPart part) {
        staticKey.setRotationAngle(part)
    }

    void setStaticRotationAxis(GroovysonObjectPart part) {
        staticKey.setRotationAxis(part)
    }

    void setStaticRotationOrigin(GroovysonObjectPart part) {
        staticKey.setRotationOrigin(part)
    }

    List<Double> getStaticFrom(GroovysonObjectPart part) {
        return staticKey.getFrom(part)
    }

    List<Double> getStaticTo(GroovysonObjectPart part) {
        return staticKey.getTo(part)
    }

    List<Double> getStaticFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return staticKey.getFaceUV(part, face)
    }

    Boolean getStaticShade(GroovysonObjectPart part) {
        return staticKey.getShade(part)
    }

    Boolean getStaticVisible(GroovysonObjectPart part) {
        return staticKey.getVisible(part)
    }

    Long getStaticColour(GroovysonObjectPart part) {
        return staticKey.getColour(part)
    }

    Long getStaticLight(GroovysonObjectPart part) {
        return staticKey.getLight(part)
    }

    Boolean getStaticInvert(GroovysonObjectPart part) {
        return staticKey.getInvert(part)
    }

    Boolean getStaticBothSides(GroovysonObjectPart part) {
        return staticKey.getBothSides(part)
    }

    String getStaticTexture(GroovysonObjectPart part, EnumFacing face) {
        return staticKey.getTexture(part, face)
    }

    List<Double> getStaticRotationAngle(GroovysonObjectPart part) {
        return staticKey.getRotationAngle(part)
    }

    List<Double> getStaticRotationAxis(GroovysonObjectPart part) {
        return staticKey.getRotationAxis(part)
    }

    List<Double> getStaticRotationOrigin(GroovysonObjectPart part) {
        return staticKey.getRotationOrigin(part)
    }

    //Variable Models

    void setVariableFrom(GroovysonObjectPart part, String newValue) {
        variableKey.setFrom(part, newValue)
    }

    void setVariableTo(GroovysonObjectPart part, String newValue) {
        variableKey.setTo(part, newValue)
    }

    void setVariableUV(GroovysonObjectPart part, String newValue) {
        variableKey.setUV(part, newValue)
    }

    void setVariableShade(GroovysonObjectPart part, String newValue) {
        variableKey.setShade(part, newValue)
    }

    void setVariableVisible(GroovysonObjectPart part, String newValue) {
        variableKey.setVisible(part, newValue)
    }

    void setVariableColour(GroovysonObjectPart part, String newValue) {
        variableKey.setColour(part, newValue)
    }

    void setVariableLight(GroovysonObjectPart part, String newValue) {
        variableKey.setLight(part, newValue)
    }

    void setVariableInvert(GroovysonObjectPart part, String newValue) {
        variableKey.setInvert(part, newValue)
    }

    void setVariableBothSides(GroovysonObjectPart part, String newValue) {
        variableKey.setBothSides(part, newValue)
    }

    void setVariableTexture(GroovysonObjectPart part) {
        variableKey.setTexture(part)
    }

    void setVariableTextureRotation(GroovysonObjectPart part, String newValue) {
        variableKey.setTextureRotation(part, newValue)
    }

    List<VariableDouble> getVariableFrom(GroovysonObjectPart part) {
        return variableKey.getFrom(part)
    }

    List<VariableDouble> getVariableTo(GroovysonObjectPart part) {
        return variableKey.getTo(part)
    }

    List<VariableDouble> getVariableFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getFaceUV(part, face)
    }

    VariableBoolean getVariableShade(GroovysonObjectPart part) {
        return variableKey.getShade(part)
    }

    VariableBoolean getVariableVisible(GroovysonObjectPart part) {
        return variableKey.getVisible(part)
    }

    VariableLong getVariableColour(GroovysonObjectPart part) {
        return variableKey.getColour(part)
    }

    VariableLong getVariableLight(GroovysonObjectPart part) {
        return variableKey.getLight(part)
    }

    VariableBoolean getVariableInvert(GroovysonObjectPart part) {
        return variableKey.getInvert(part)
    }

    VariableBoolean getVariableBothSides(GroovysonObjectPart part) {
        return variableKey.getBothSides(part)
    }

    VariableObject<String> getVariableTexture(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getTexture(part, face)
    }

    VariableLong getVariableTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getTextureRotation(part, face)
    }
}
