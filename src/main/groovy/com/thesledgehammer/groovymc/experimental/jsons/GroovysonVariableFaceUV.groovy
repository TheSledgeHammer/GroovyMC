package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.experimental.models.GroovyVariableModel
import com.thesledgehammer.groovymc.experimental.models.VariableBase
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing

//Based from BC8's JsonVariableFaceUV
class GroovysonVariableFaceUV {

    final VariableDouble[] uv;
    final VariableLong textureRotation;
    final VariableBoolean visible;
    final VariableBoolean invert;
    final VariableBoolean bothSides;
    final VariableObject<String> texture;

    GroovysonVariableFaceUV(GroovysonVariableModel model) {

    }

    private static VariableObject<String> readVariableString(GroovysonVariableModel model, String variable) {

        return model.AssignVariableString();
    }

    VariableDouble[] readVariableUV(GroovysonVariableModel model, int modelIndex, String newValue, String variable) {
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
                    uv[i] = model.AssignVariableDouble(newValue, var, i, variable);
                }
            }
        }
        return uv;
    }

    Map<EnumFacing, List<String>> getVariableUV(GroovysonVariableModel groovysonModel, int index) {
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

    List<String> getVariableFrom(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).From());
        return var;
    }

    List<String> getVariableTo(GroovysonVariableModel groovysonModel, int index) {
        List<String> var = ListTools.FloatListToStringList(groovysonModel.getRawModelPart(index).To());
        return var;
    }

    VariableDouble[] setVariableFrom(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableFrom(groovysonModel, modelIndex);
        VariableDouble[] from = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                from[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                from[i] = groovysonModel.AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return from;
    }

    VariableDouble[] setVariableTo(GroovysonVariableModel groovysonModel, int modelIndex, String newValue, String variable) {
        List<String> var = getVariableTo(groovysonModel, modelIndex);
        VariableDouble[] to = new VariableDouble[3];
        for(int i = 0; i < 3; i++) {
            if(!var.get(i).contains(variable)) {
                to[i] = new VariableDouble(var.get(i).toDouble());
            } else {
                to[i] = groovysonModel.AssignVariableDouble(newValue, var, i, variable);
            }
        }
        return to;
    }

    VariableBase.VariableFaceData evaluate(GroovyVariableModel.ITextureGetter spriteLookup) {
        VariableBase.VariableFaceData data = new VariableBase.VariableFaceData();
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
