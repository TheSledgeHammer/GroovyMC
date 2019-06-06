package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.MathTools
import net.minecraft.util.EnumFacing

//Based from BC8's JsonVariableFaceUV
//TODO: Complete: textureRotation, Invert, BothSides & Texture
class GroovysonVariableFaceUV implements VariableTraits {
    
    private VariableDouble[] from;
    private VariableDouble[] to;
    private VariableDouble[] uv;
    final VariableLong textureRotation;
    private VariableBoolean visible;
    final VariableBoolean invert;
    final VariableBoolean bothSides;
    final VariableObject<String> texture;
    
    GroovysonVariableFaceUV(GroovysonVariableModel model, int modelIndex, String newValue, String variable) {
        setFrom(setVariableFrom(model, modelIndex, newValue, variable));
        setTo(setVariableTo(model, modelIndex, newValue, variable));
        setUV(setVariableUV(model, modelIndex, newValue, variable));
        setVisible(setVariableVisible(model, modelIndex, true));
    }
    
    private void setFrom(VariableDouble[] from) {
        this.from = from;
    }

    private void setTo(VariableDouble[] to) {
        this.to = to;
    }

    private void setUV(VariableDouble[] uv) {
        this.uv = uv;
    }

    private void setVisible(VariableBoolean visible) {
        this.visible = visible;
    }

    private void setColour(VariableLong colour) {
        this.colour = colour;
    }

    private void setLight(VariableLong light) {
        this.light = light;
    }

    VariableDouble[] setVariableFrom(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableFrom(groovysonModel, modelIndex);
        VariableDouble[] from = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                from[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                from[i] = AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return from;
    }

    private VariableDouble[] setVariableTo(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableTo(groovysonModel, modelIndex);
        VariableDouble[] to = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                to[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                to[i] = AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return to;
    }

    private VariableDouble[] setVariableUV(GroovysonVariableModel model, int modelIndex, String newValue, String variable) {
        List<String> var = null
        for(EnumFacing face : EnumFacing.VALUES) {
            if (!getVariableUV(model, modelIndex).get(face).isEmpty()) {
                var = getVariableUV(model, modelIndex).get(face);
            }
        }
        VariableDouble[] uv = new VariableDouble[4];
        if(var.size() != 4) {
            throw new Exception("Expected exactly 4 doubles, but got: ${var.toArray().toString()}")
        } else {
            for(int i = 0; i < 4; i++) {
                if(!var.get(i).contains(variable)) {
                    uv[i] = new VariableDouble(var.get(i).toDouble());
                } else {
                    uv[i] = AssignVariableDouble(newValue, var, i, variable);
                }
            }
        }
        return uv;
    }

    private VariableBoolean setVariableVisible(GroovysonVariableModel model, int modelIndex, boolean newValue) {
        String var = getVariableVisible(model, modelIndex);
        VariableBoolean visible;
        if(MathTools.isBoolean(var)) {
            newValue = var;
            visible = new VariableBoolean(newValue);
        }
        visible = AssignVariableBoolean(newValue, var);
        return visible;
    }

    private VariableLong setVariableColour(GroovysonVariableModel model, int modelIndex, String newValue) {
        String var = getVariableColour(model, modelIndex);
        VariableLong colour;
        if(var != null) {
            newValue = var;
            colour = new VariableLong(Long.valueOf(newValue));
        } else {
            colour = new VariableLong(Long.valueOf(newValue));
        }
        return colour;
    }

    private VariableLong setVariableLight(GroovysonVariableModel model, int modelIndex, String newValue) {
        String var = getVariableLight(model, modelIndex);
        VariableLong light;
        if(var != null) {
            newValue = var;
            light = new VariableLong(Long.valueOf(newValue));
        } else {
            light = new VariableLong(Long.valueOf(newValue));
        }
        return light;
    }
    
    private static List<String> getVariableFrom(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).From());
        return var;
    }

    private static List<String> getVariableTo(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).To());
        return var;
    }

    private static Map<EnumFacing, List<String>> getVariableUV(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = null;
        HashMap<EnumFacing, List<String>> variableMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if(face != null) {
                var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).FacingUv(face));
                variableMap.put(face, var)
            }
        }
        return variableMap;
    }

    private static String getVariableVisible(GroovysonVariableModel model, int index) {
        return model.Visible(index);
    }

    private static String getVariableLight(GroovysonVariableModel model, int index) {
        return model.Light(index);
    }

    private static String getVariableColour(GroovysonVariableModel model, int index) {
        return model.Colour(index);
    }
    
   VariableFaceData evaluate(ITextureGetter spriteLookup) {
       VariableFaceData data = new VariableFaceData();
       ModelUtil.TexturedFace face = spriteLookup.get(texture.getValue());
       data.sprite = face.sprite;
       data.rotations = (int) textureRotation.getValue();
       data.uvs.minU = (float) (uv[0].getValue() / 16.0);
       data.uvs.minV = (float) (uv[1].getValue() / 16.0);
       data.uvs.maxU = (float) (uv[2].getValue() / 16.0);
       data.uvs.maxV = (float) (uv[3].getValue() / 16.0);
       data.uvs = data.uvs.inParent(face.faceData);
       data.invertNormal = invert.getValue();
       data.bothSides = bothSides.getValue();
       return data;
    }
}
