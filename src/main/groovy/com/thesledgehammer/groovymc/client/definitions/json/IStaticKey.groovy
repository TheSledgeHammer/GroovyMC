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
package com.thesledgehammer.groovymc.client.definitions.json

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.util.Direction

interface IStaticKey {

    void setFrom(GroovysonObjectPart part);

    void setTo(GroovysonObjectPart part);

    void setUV(GroovysonObjectPart part);

    void setShade(GroovysonObjectPart part);

    void setVisible(GroovysonObjectPart part);

    void setColour(GroovysonObjectPart part);

    void setLight(GroovysonObjectPart part);

    void setTexture(GroovysonObjectPart part);

    //void setTextureRotation(GroovysonObjectPart part)

    void setRotationAngle(GroovysonObjectPart part);

    void setRotationAxis(GroovysonObjectPart part);

    void setRotationOrigin(GroovysonObjectPart part);

    List<Double> getFrom(GroovysonObjectPart part);

    List<Double> getTo(GroovysonObjectPart part);

    List<Double> getFaceUV(GroovysonObjectPart part, Direction face);

    Boolean getShade(GroovysonObjectPart part);

    Boolean getVisible(GroovysonObjectPart part);

    Long getColour(GroovysonObjectPart part);

    Long getLight(GroovysonObjectPart part);

    Boolean getInvert(GroovysonObjectPart part);

    Boolean getBothSides(GroovysonObjectPart part);

    String getTexture(GroovysonObjectPart part, Direction face);

    //getTextureRotation(GroovysonObjectPart part)

    List<Double> getRotationAngle(GroovysonObjectPart part);

    List<Double> getRotationAxis(GroovysonObjectPart part);

    List<Double> getRotationOrigin(GroovysonObjectPart part);
}