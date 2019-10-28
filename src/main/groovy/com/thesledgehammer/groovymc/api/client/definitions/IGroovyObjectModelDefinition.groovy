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
package com.thesledgehammer.groovymc.api.client.definitions

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.variables.VariableBoolean
import com.thesledgehammer.groovymc.utils.variables.VariableDouble
import com.thesledgehammer.groovymc.utils.variables.VariableLong
import com.thesledgehammer.groovymc.utils.variables.VariableObject
import net.minecraft.util.Direction

interface IGroovyObjectModelDefinition {

    //IStaticKey
    void setStaticFrom(GroovysonObjectPart part);

    void setStaticTo(GroovysonObjectPart part);

    void setStaticUV(GroovysonObjectPart part);

    void setStaticShade(GroovysonObjectPart part);

    void setStaticVisible(GroovysonObjectPart part)

    void setStaticColour(GroovysonObjectPart part);

    void setStaticLight(GroovysonObjectPart part);

    void setStaticTexture(GroovysonObjectPart part);

    void setStaticRotationAngle(GroovysonObjectPart part);

    void setStaticRotationAxis(GroovysonObjectPart part);

    void setStaticRotationOrigin(GroovysonObjectPart part);

    List<Double> getStaticFrom(GroovysonObjectPart part);

    List<Double> getStaticTo(GroovysonObjectPart part);

    List<Double> getStaticFaceUV(GroovysonObjectPart part, Direction face);

    Boolean getStaticShade(GroovysonObjectPart part);

    Boolean getStaticVisible(GroovysonObjectPart part);

    Long getStaticColour(GroovysonObjectPart part);

    Long getStaticLight(GroovysonObjectPart part);

    Boolean getStaticInvert(GroovysonObjectPart part);

    Boolean getStaticBothSides(GroovysonObjectPart part);

    String getStaticTexture(GroovysonObjectPart part, Direction face);

    List<Double> getStaticRotationAngle(GroovysonObjectPart part);

    List<Double> getStaticRotationAxis(GroovysonObjectPart part);

    List<Double> getStaticRotationOrigin(GroovysonObjectPart part);

    //IVariableKey
    void setVariableFrom(GroovysonObjectPart part, String newValue);

    void setVariableTo(GroovysonObjectPart part, String newValue);

    void setVariableUV(GroovysonObjectPart part, String newValue);

    void setVariableShade(GroovysonObjectPart part, String newValue);

    void setVariableVisible(GroovysonObjectPart part, String newValue);

    void setVariableColour(GroovysonObjectPart part, String newValue);

    void setVariableLight(GroovysonObjectPart part, String newValue);

    void setVariableInvert(GroovysonObjectPart part, String newValue);

    void setVariableBothSides(GroovysonObjectPart part, String newValue);

    void setVariableTexture(GroovysonObjectPart part);

    void setVariableTextureRotation(GroovysonObjectPart part, String newValue);

    List<VariableDouble> getVariableFrom(GroovysonObjectPart part);

    List<VariableDouble> getVariableTo(GroovysonObjectPart part);

    List<VariableDouble> getVariableFaceUV(GroovysonObjectPart part, Direction face);

    VariableBoolean getVariableShade(GroovysonObjectPart part);

    VariableBoolean getVariableVisible(GroovysonObjectPart part);

    VariableLong getVariableColour(GroovysonObjectPart part);

    VariableLong getVariableLight(GroovysonObjectPart part);

    VariableBoolean getVariableInvert(GroovysonObjectPart part);

    VariableBoolean getVariableBothSides(GroovysonObjectPart part);

    VariableObject<String> getVariableTexture(GroovysonObjectPart part, Direction face);

    VariableLong getVariableTextureRotation(GroovysonObjectPart part, Direction face);
}