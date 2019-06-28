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

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import net.minecraft.util.EnumFacing

//Work in Progress: Map Static Models
class GroovysonStaticDefinition {

    private final Map<GroovysonObjectPart, List<Double>> from = new HashMap<>();
    private final Map<GroovysonObjectPart, List<Double>> to = new HashMap<>();
    private final Table<GroovysonObjectPart, EnumFacing, List<Double>> uv = HashBasedTable.create();
    private final Map<GroovysonObjectPart, Boolean> shade = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> visible = new HashMap<>();
    private final Map<GroovysonObjectPart, Long> colour = new HashMap<>();
    private final Map<GroovysonObjectPart, Long> light = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> invert = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> bothSides = new HashMap<>();
    private final Table<GroovysonObjectPart, EnumFacing, Long> textureRotation = HashBasedTable.create();
    private final Table<GroovysonObjectPart, EnumFacing, String> texture = HashBasedTable.create();

    GroovysonStaticDefinition() {

    }

    void setFrom(GroovysonObjectPart part) {
        this.from.put(part, From(part));
    }

    void setTo(GroovysonObjectPart part) {
        this.to.put(part, To(part));
    }
    void setUV(GroovysonObjectPart part) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if(part.Facing(face) != null) {
                this.uv.put(part, face, FaceUV(part, face));
            }
        }
    }

    void setShade(GroovysonObjectPart part) {
        this.shade.put(part, Shade(part));
    }

    void setVisible(GroovysonObjectPart part) {
        this.visible.put(part, Visible(part));
    }

    void setColour(GroovysonObjectPart part) {
        this.colour.put(part, Long.valueOf(Colour(part)));
    }

    void setLight(GroovysonObjectPart part) {
        this.light.put(part, Long.valueOf(Light(part)));
    }

    void setTexture(GroovysonObjectPart part) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                this.texture.put(part, face, TextureFace(part, face));
            }
        }
    }
/*
    void setTextureRotation(GroovysonObjectPart part, String newValue) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                this.textureRotation.put(part, face, Te);
            }
        }
    }
*/
    List<Double> getFrom(GroovysonObjectPart part) {
        return from.get(part);
    }

    List<Double> getTo(GroovysonObjectPart part) {
        return to.get(part);
    }

    List<Double> getFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return uv.get(part, face);
    }

    Boolean getShade(GroovysonObjectPart part) {
        return shade.get(part);
    }

    Boolean getVisible(GroovysonObjectPart part) {
        return visible.get(part);
    }

    Long getColour(GroovysonObjectPart part) {
        return colour.get(part);
    }

    Long getLight(GroovysonObjectPart part) {
        return light.get(part);
    }

    Boolean getInvert(GroovysonObjectPart part) {
        return invert.get(part);
    }

    Boolean getBothSides(GroovysonObjectPart part) {
        return bothSides.get(part);
    }

    String getTexture(GroovysonObjectPart part, EnumFacing face) {
        return texture.get(part, face);
    }
/*
    Long getTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return textureRotation.get(part, face);
    }*/

    private List<Double> From(GroovysonObjectPart part) {
        return part.From();
    }

    private List<Double> To(GroovysonObjectPart part) {
        return part.To();
    }

    private List<Double> FaceUV(GroovysonObjectPart part, EnumFacing face) {
        return part.FacingUv(face);
    }

    private Float RotationAngle(GroovysonObjectPart part, int axis) {
        if(part.RotationAxis() != null) {
            return part.RotationAxis().get(axis);
        }
        return null;
    }

    private Float RotationAxis(GroovysonObjectPart part, int axis) {
        if(part.RotationAxis() != null) {
            return part.RotationAxis().get(axis);
        }
        return null;
    }

    private Float RotationOrigin(GroovysonObjectPart part, int axis) {
        if(part.RotationOrigin() != null) {
            return part.RotationOrigin().get(axis);
        }
        return null;
    }

    private float FaceTint(GroovysonObjectPart part, EnumFacing face, int fallback) {
        return part.FacingTint(face, fallback);
    }

    private float FaceRotation(GroovysonObjectPart part, EnumFacing face, int fallback) {
        return part.FacingRotation(face, fallback);
    }

    private String TextureFace(GroovysonObjectPart part, EnumFacing face) {
        return part.TextureFace(face);
    }

    private boolean Shade(GroovysonObjectPart part) {
        return part.Shade()
    }

    private boolean RotationRescale(GroovysonObjectPart part) {
        return part.RotationRescale();
    }

    private String CullFace(GroovysonObjectPart part, EnumFacing face) {
        return part.CullFaceFace(face);
    }

    private String Light(GroovysonObjectPart part) {
        return part.Light()
    }

    private boolean Visible(GroovysonObjectPart part) {
        return part.Visible();
    }

    private String Colour(GroovysonObjectPart part) {
        return part.Colour();
    }
}
