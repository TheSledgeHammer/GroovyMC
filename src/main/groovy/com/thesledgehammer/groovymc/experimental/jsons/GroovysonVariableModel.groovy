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
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.MathTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.EnumFacing

class GroovysonVariableModel extends GroovysonAbstractModel implements VariableTraits {

    GroovysonVariableModel(String resourceObject, String fileName) {
        super(resourceObject, fileName)
    }

    GroovysonVariableModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, resourceObject, fileName)
    }

    List<VariableDouble> VariableFrom(int modelIndex, String newValue, String variable) {
        List<String> var = ListTools.FloatListToStringList(getRawModelPart(modelIndex).From());
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

    List<VariableDouble> VariableTo(int modelIndex, String newValue, String variable) {
        List<String> var = ListTools.FloatListToStringList(getRawModelPart(modelIndex).To());
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

    List<VariableDouble> VariableUV(int modelIndex, String newValue, String variable) {
        List<String> var = null
        for (EnumFacing face : EnumFacing.VALUES) {
            if (!getVariableUV(modelIndex).get(face).isEmpty()) {
                var = getVariableUV(modelIndex).get(face);
            }
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

    VariableLong TextureRotation(int modelIndex, String newValue) {
        String var = null;
        for (EnumFacing face : EnumFacing.VALUES) {
            if(!getVariableTextureRotation(modelIndex).isEmpty()) {
                var = getVariableTextureRotation(modelIndex).get(face);
            }
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

    VariableLong VariableLight(int modelIndex, String newValue) {
        String var = getRawModelPart(modelIndex).Light();
        VariableLong light;
        if(var != null) {
            newValue = var;
            light = new VariableLong(Long.valueOf(newValue));
        } else {
            light = new VariableLong(Long.valueOf(newValue));
        }
        return light;
    }

    VariableBoolean VariableVisible(int modelIndex, boolean newValue) {
        String var = getRawModelPart(modelIndex).Visible();
        VariableBoolean visible;
        if(var != null ) {
            newValue = var;
            visible = new VariableBoolean(newValue);
        } else {
            visible = AssignVariableBoolean(newValue, var);
        }
        return visible;
    }

    VariableLong VariableColour(int modelIndex, String newValue) {
        String var = getRawModelPart(modelIndex).Colour();
        VariableLong colour;
        if(var != null) {
            newValue = var;
            colour = new VariableLong(Long.valueOf(newValue));
        } else {
            colour = new VariableLong(Long.valueOf(newValue));
        }
        return colour;
    }

    VariableBoolean VariableInvert(int modelIndex, boolean newValue) {
        String var = getRawModelPart(modelIndex).Invert();
        VariableBoolean invert;
        if(var != null) {
            newValue = var;
            invert = new VariableBoolean(newValue);
        } else {
            invert = AssignVariableBoolean(newValue, var);
        }
        return invert;
    }

    VariableBoolean VariableBothSides(int modelIndex, boolean newValue) {
        String var = getRawModelPart(modelIndex).BothSides();
        VariableBoolean bothSides;
        if(var != null) {
            newValue = var;
            bothSides = new VariableBoolean(newValue);
        } else {
            bothSides = AssignVariableBoolean(newValue, var);
        }
        return bothSides;
    }

    //May need to read by face
    VariableObject<String> VariableTexture(int modelIndex) {
        String var = null;
        VariableObject<String> texture = new VariableObject<String>()
        for (EnumFacing face : EnumFacing.VALUES) {
            if(!getVariableTexture(modelIndex).isEmpty()) {
                var = getVariableTexture(modelIndex).get(face);
            }
        }
        texture.setValue(var);
        return texture;
    }

    private Map<EnumFacing, List<String>> getVariableUV(int index) {
        List<String> var = null;
        HashMap<EnumFacing, List<String>> variableMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if(face != null) {
                var = ListTools.FloatListToStringList(getRawModelPart(index).FacingUv(face));
                variableMap.put(face, var)
            }
        }
        return variableMap;
    }

    private Map<EnumFacing, String> getVariableTextureRotation(int index) {
        HashMap<EnumFacing, String> variableMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                variableMap.put(face, getRawModelPart(index).FacingRotation(face, 0).toString());
            }
        }
        return variableMap
    }

    private Map<EnumFacing, String> getVariableTexture(int index) {
        HashMap<EnumFacing, String> variableMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                variableMap.put(face, getRawModelPart(index).TextureFace(face).toString());
            }
        }
        return variableMap
    }
}
