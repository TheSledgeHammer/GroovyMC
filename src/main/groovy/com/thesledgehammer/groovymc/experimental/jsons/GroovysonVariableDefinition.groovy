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
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing

class GroovysonVariableDefinition implements VariableTraits {

    private final Map<GroovysonObjectPart, List<VariableDouble>> from = new HashMap<>();
    private final Map<GroovysonObjectPart, List<VariableDouble>> to = new HashMap<>();
    private final Table<GroovysonObjectPart, EnumFacing, List<VariableDouble>> uv = HashBasedTable.create();
    private final Map<GroovysonObjectPart, VariableBoolean> shade = new HashMap<>();
    private final Map<GroovysonObjectPart, VariableBoolean> visible = new HashMap<>();
    private final Map<GroovysonObjectPart, VariableLong> colour = new HashMap<>();
    private final Map<GroovysonObjectPart, VariableLong> light = new HashMap<>();
    private final Map<GroovysonObjectPart, VariableBoolean> invert = new HashMap<>();
    private final Map<GroovysonObjectPart, VariableBoolean> bothSides = new HashMap<>();
    private final Table<GroovysonObjectPart, EnumFacing, VariableLong> textureRotation = HashBasedTable.create();
    private final Table<GroovysonObjectPart, EnumFacing, VariableObject<String>> texture = HashBasedTable.create();

    GroovysonVariableDefinition() {
    }

    void setGroovysonVariableFaceUV(GroovysonObjectPart parts) {
        setFrom(parts, "0");
        setTo(parts, "0");
        setUV(parts, "0");
        setVisible(parts, "true");
        setInvert(parts, "false");
        setBothSides(parts, "false");
        setTexture(parts);
        setTextureRotation(parts, "0");
    }

    void setGroovysonVariableCuboidBase(GroovysonObjectPart parts) {
        setFrom(parts, "0");
        setTo(parts, "0");
        setShade(parts, "true");
        setVisible(parts, "true");
        setLight(parts, "0");
        setColour(parts, "-1");
    }

    void setFrom(GroovysonObjectPart part, String newValue) {
        this.from.put(part, VariableFrom(part, newValue))
    }

    void setTo(GroovysonObjectPart part, String newValue) {
        this.to.put(part, VariableTo(part, newValue));
    }

    void setUV(GroovysonObjectPart part, String newValue) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if(part.Facing(face) != null) {
                this.uv.put(part, face, VariableUV(part, face, newValue));
            }
        }
    }

    void setShade(GroovysonObjectPart part, String newValue) {
        this.shade.put(part, VariableShade(part, newValue));
    }

    void setVisible(GroovysonObjectPart part, String newValue) {
        this.visible.put(part, VariableVisible(part, newValue));
    }

    void setColour(GroovysonObjectPart part, String newValue) {
        this.colour.put(part, VariableColour(part, newValue));
    }

    void setLight(GroovysonObjectPart part, String newValue) {
        this.light.put(part, VariableLight(part, newValue));
    }

    void setInvert(GroovysonObjectPart part, String newValue) {
        this.invert.put(part, VariableInvert(part, newValue));
    }

    void setBothSides(GroovysonObjectPart part, String newValue) {
        this.bothSides.put(part, VariableBothSides(part, newValue));
    }

    void setTexture(GroovysonObjectPart part) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                this.texture.put(part, face, VariableTexture(part, face));
            }
        }
    }

    void setTextureRotation(GroovysonObjectPart part, String newValue) {
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                this.textureRotation.put(part, face, VariableTextureRotation(part, face, newValue));
            }
        }
    }

    List<VariableDouble> getFrom(GroovysonObjectPart part) {
        return from.get(part);
    }

    List<VariableDouble> getTo(GroovysonObjectPart part) {
        return to.get(part);
    }

    List<VariableDouble> getFaceUV(GroovysonObjectPart part, EnumFacing face) {
        return uv.get(part, face);
    }

    VariableBoolean getShade(GroovysonObjectPart part) {
        return shade.get(part);
    }

    VariableBoolean getVisible(GroovysonObjectPart part) {
        return visible.get(part);
    }

    VariableLong getColour(GroovysonObjectPart part) {
        return colour.get(part);
    }

    VariableLong getLight(GroovysonObjectPart part) {
        return light.get(part);
    }

    VariableBoolean getInvert(GroovysonObjectPart part) {
        return invert.get(part);
    }

    VariableBoolean getBothSides(GroovysonObjectPart part) {
        return bothSides.get(part);
    }

    VariableObject<String> getTexture(GroovysonObjectPart part, EnumFacing face) {
        return texture.get(part, face);
    }

    VariableLong getTextureRotation(GroovysonObjectPart part, EnumFacing face) {
        return textureRotation.get(part, face);
    }

    private List<VariableDouble> VariableFrom(GroovysonObjectPart part, String newValue) {
        String variable = "progress_size"
        List<String> var = ListTools.FloatListToStringList(part.From());
        List<VariableDouble> from = new ArrayList<VariableDouble>(3);
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                from.add(new VariableDouble(var.get(i).toDouble()));
            } else {
                from.add(AssignVariableDouble(newValue, var, i, variable));
            }
        }
        return from;
    }

    private List<VariableDouble> VariableTo(GroovysonObjectPart part, String newValue) {
        String variable = "progress_size"
        List<String> var = ListTools.FloatListToStringList(part.To());
        List<VariableDouble> to = new ArrayList<VariableDouble>(3);
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                to.add(new VariableDouble(var.get(i).toDouble()));
            } else {
                to.add(AssignVariableDouble(newValue, var, i, variable));
            }
        }
        return to;
    }

    private List<VariableDouble> VariableUV(GroovysonObjectPart part, EnumFacing face, String newValue) {
        String variable = "progress_size"
        List<String> var = null;
        if (!getVariableUV(part).get(face).isEmpty()) {
            var = getVariableUV(part).get(face);
        }

        List<VariableDouble> uv = new ArrayList<VariableDouble>(4);//[4];
        if (var.size() != 4) {
            throw new Exception("Expected exactly 4 doubles, but got: ${var.toArray().toString()}")
        } else {
            for (int i = 0; i < 4; i++) {
                if (!var.get(i).contains(variable)) {
                    uv.add(new VariableDouble(var.get(i).toDouble()));
                } else {
                    uv.add(AssignVariableDouble(newValue, var, i, variable));
                }
            }
        }
        return uv;
    }

    private VariableBoolean VariableShade(GroovysonObjectPart part, String newValue) {
        String var = part.Shade();
        VariableBoolean shade;
        if(var != null ) {
            newValue = var;
            shade = new VariableBoolean(Boolean.valueOf(newValue));
        } else {
            shade = AssignVariableBoolean(Boolean.valueOf(newValue), var);
        }
        return shade;
    }

    private VariableLong VariableLight(GroovysonObjectPart part, String newValue) {
        String var = part.Light();
        VariableLong light;
        if(var != null) {
            newValue = var;
            light = new VariableLong(Long.valueOf(newValue));
        } else {
            light = new VariableLong(Long.valueOf(newValue));
        }
        return light;
    }

    private VariableBoolean VariableVisible(GroovysonObjectPart part, String newValue) {
        String var = part.Visible();
        VariableBoolean visible;
        if(var != null ) {
            newValue = var;
            visible = new VariableBoolean(Boolean.valueOf(newValue));
        } else {
            visible = AssignVariableBoolean(Boolean.valueOf(newValue), var);
        }
        return visible;
    }

    private VariableLong VariableColour(GroovysonObjectPart part, String newValue) {
        String var = part.Colour();
        VariableLong colour;
        if(var != null) {
            newValue = var;
            colour = new VariableLong(Long.valueOf(newValue));
        } else {
            colour = new VariableLong(Long.valueOf(newValue));
        }
        return colour;
    }

    private VariableBoolean VariableInvert(GroovysonObjectPart part, String newValue) {
        String var = part.Invert();
        VariableBoolean invert;
        if(var != null) {
            newValue = var;
            invert = new VariableBoolean(Boolean.valueOf(newValue));
        } else {
            invert = AssignVariableBoolean(Boolean.valueOf(newValue), var);
        }
        return invert;
    }

    private VariableBoolean VariableBothSides(GroovysonObjectPart part, String newValue) {
        String var = part.BothSides();
        VariableBoolean bothSides;
        if(var != null) {
            newValue = var;
            bothSides = new VariableBoolean(Boolean.valueOf(newValue));
        } else {
            bothSides = AssignVariableBoolean(Boolean.valueOf(newValue), var);
        }
        return bothSides;
    }

    private VariableLong VariableTextureRotation(GroovysonObjectPart part, EnumFacing face, String newValue) {
        String var = null;
        if(!getVariableTextureRotation(part).isEmpty()) {
            var = getVariableTextureRotation(part).get(face);
        }
        VariableLong textureRotation;
        if(var != null) {
            newValue = var;
            textureRotation = new VariableLong(Long.valueOf(newValue));
        } else {
            textureRotation = new VariableLong(Long.valueOf(newValue));
        }
        return textureRotation;
    }

    private VariableObject<String> VariableTexture(GroovysonObjectPart part, EnumFacing face) {
        String var = null;
        VariableObject<String> texture = new VariableObject<String>()
        if(!getVariableTexture(part).isEmpty()) {
            var = getVariableTexture(part).get(face);
        }
        texture.setValue(var);
        return texture;
    }

    private Map<EnumFacing, List<String>> getVariableUV(GroovysonObjectPart part) {
        List<String> var = null;
        EnumMap<EnumFacing, List<String>> variableMap = new EnumMap<>(EnumFacing.class);
        for(EnumFacing face : EnumFacing.VALUES) {
            if(face != null) {
                var = ListTools.FloatListToStringList(part.FacingUv(face));
                variableMap.put(face, var)
            }
        }
        return variableMap;
    }

    private Map<EnumFacing, String> getVariableTextureRotation(GroovysonObjectPart part) {
        EnumMap<EnumFacing, String> variableMap = new EnumMap<>(EnumFacing.class);
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                variableMap.put(face, part.FacingRotation(face, 0).toString());
            }
        }
        return variableMap
    }

    private Map<EnumFacing, String> getVariableTexture(GroovysonObjectPart part) {
        EnumMap<EnumFacing, String> variableMap = new EnumMap<>(EnumFacing.class);
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                variableMap.put(face, part.TextureFace(face).toString());
            }
        }
        return variableMap
    }
}
