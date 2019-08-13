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

import com.thesledgehammer.groovymc.api.client.definitions.IGroovysonModelDefinition
import com.thesledgehammer.groovymc.client.definitions.json.GroovysonStaticKey
import com.thesledgehammer.groovymc.client.definitions.json.GroovysonVariableKey
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.variables.VariableBoolean
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.variables.VariableLong
import com.thesledgehammer.groovymc.utils.variables.VariableObject
import net.minecraft.util.EnumFacing

class GroovysonModelDefinition implements IGroovysonModelDefinition{

    private GroovysonStaticKey staticKey;
    private GroovysonVariableKey variableKey;

    GroovysonModelDefinition() {
        this.staticKey = new GroovysonStaticKey();
        this.variableKey = new GroovysonVariableKey();
    }

    //Static Models
    @Override
    void setStaticFrom(GroovysonObjectPart part) {
        staticKey.setFrom(part)
    }

    @Override
    void setStaticTo(GroovysonObjectPart part) {
        staticKey.setTo(part)
    }

    @Override
    void setStaticUV(GroovysonObjectPart part) {
        staticKey.setUV(part)
    }

    @Override
    void setStaticShade(GroovysonObjectPart part) {
        staticKey.setShade(part)
    }

    @Override
    void setStaticVisible(GroovysonObjectPart part) {
        staticKey.setVisible(part)
    }

    @Override
    void setStaticColour(GroovysonObjectPart part) {
        staticKey.setColour(part)
    }

    @Override
    void setStaticLight(GroovysonObjectPart part) {
        staticKey.setLight(part)
    }

    @Override
    void setStaticTexture(GroovysonObjectPart part) {
        staticKey.setTexture(part)
    }

    @Override
    void setStaticRotationAngle(GroovysonObjectPart part) {
        staticKey.setRotationAngle(part)
    }

    @Override
    void setStaticRotationAxis(GroovysonObjectPart part) {
        staticKey.setRotationAxis(part)
    }

    @Override
    void setStaticRotationOrigin(GroovysonObjectPart part) {
        staticKey.setRotationOrigin(part)
    }

    @Override
    List<Double> getStaticFrom(GroovysonObjectPart part) {
        return staticKey.getFrom(part)
    }

    @Override
    List<Double> getStaticTo(GroovysonObjectPart part) {
        return staticKey.getTo(part)
    }

    @Override
    List<Double> getStaticFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return staticKey.getFaceUV(part, face)
    }

    @Override
    Boolean getStaticShade(GroovysonObjectPart part) {
        return staticKey.getShade(part)
    }

    @Override
    Boolean getStaticVisible(GroovysonObjectPart part) {
        return staticKey.getVisible(part)
    }

    @Override
    Long getStaticColour(GroovysonObjectPart part) {
        return staticKey.getColour(part)
    }

    @Override
    Long getStaticLight(GroovysonObjectPart part) {
        return staticKey.getLight(part)
    }

    @Override
    Boolean getStaticInvert(GroovysonObjectPart part) {
        return staticKey.getInvert(part)
    }

    @Override
    Boolean getStaticBothSides(GroovysonObjectPart part) {
        return staticKey.getBothSides(part)
    }

    @Override
    String getStaticTexture(GroovysonObjectPart part, EnumFacing face) {
        return staticKey.getTexture(part, face)
    }

    @Override
    List<Double> getStaticRotationAngle(GroovysonObjectPart part) {
        return staticKey.getRotationAngle(part)
    }

    @Override
    List<Double> getStaticRotationAxis(GroovysonObjectPart part) {
        return staticKey.getRotationAxis(part)
    }

    @Override
    List<Double> getStaticRotationOrigin(GroovysonObjectPart part) {
        return staticKey.getRotationOrigin(part)
    }

    //Variable Models
    @Override
    void setVariableFrom(GroovysonObjectPart part, String newValue) {
        variableKey.setFrom(part, newValue)
    }

    @Override
    void setVariableTo(GroovysonObjectPart part, String newValue) {
        variableKey.setTo(part, newValue)
    }

    @Override
    void setVariableUV(GroovysonObjectPart part, String newValue) {
        variableKey.setUV(part, newValue)
    }

    @Override
    void setVariableShade(GroovysonObjectPart part, String newValue) {
        variableKey.setShade(part, newValue)
    }

    @Override
    void setVariableVisible(GroovysonObjectPart part, String newValue) {
        variableKey.setVisible(part, newValue)
    }

    @Override
    void setVariableColour(GroovysonObjectPart part, String newValue) {
        variableKey.setColour(part, newValue)
    }

    @Override
    void setVariableLight(GroovysonObjectPart part, String newValue) {
        variableKey.setLight(part, newValue)
    }

    @Override
    void setVariableInvert(GroovysonObjectPart part, String newValue) {
        variableKey.setInvert(part, newValue)
    }

    @Override
    void setVariableBothSides(GroovysonObjectPart part, String newValue) {
        variableKey.setBothSides(part, newValue)
    }

    @Override
    void setVariableTexture(GroovysonObjectPart part) {
        variableKey.setTexture(part)
    }

    @Override
    void setVariableTextureRotation(GroovysonObjectPart part, String newValue) {
        variableKey.setTextureRotation(part, newValue)
    }

    @Override
    List<VariableDouble> getVariableFrom(GroovysonObjectPart part) {
        return variableKey.getFrom(part)
    }

    @Override
    List<VariableDouble> getVariableTo(GroovysonObjectPart part) {
        return variableKey.getTo(part)
    }

    @Override
    List<VariableDouble> getVariableFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getFaceUV(part, face)
    }

    @Override
    VariableBoolean getVariableShade(GroovysonObjectPart part) {
        return variableKey.getShade(part)
    }

    @Override
    VariableBoolean getVariableVisible(GroovysonObjectPart part) {
        return variableKey.getVisible(part)
    }

    @Override
    VariableLong getVariableColour(GroovysonObjectPart part) {
        return variableKey.getColour(part)
    }

    @Override
    VariableLong getVariableLight(GroovysonObjectPart part) {
        return variableKey.getLight(part)
    }

    @Override
    VariableBoolean getVariableInvert(GroovysonObjectPart part) {
        return variableKey.getInvert(part)
    }

    @Override
    VariableBoolean getVariableBothSides(GroovysonObjectPart part) {
        return variableKey.getBothSides(part)
    }

    @Override
    VariableObject<String> getVariableTexture(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getTexture(part, face)
    }

    @Override
    VariableLong getVariableTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return variableKey.getTextureRotation(part, face)
    }
}
