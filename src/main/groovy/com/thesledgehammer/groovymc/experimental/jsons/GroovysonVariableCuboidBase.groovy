package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import net.minecraft.util.EnumFacing

class GroovysonVariableCuboidBase {

    GroovyVariableDefinition variableDefinition;
    private EnumFacing face;
    private VariableDouble[] from;
    private VariableDouble[] to;
    private VariableBoolean visible;
    private VariableBoolean shade;
    private VariableLong colour
    private VariableLong light

    GroovysonVariableCuboidBase(GroovysonVariableModel model, int modelIndex, EnumFacing face, String newValue, String variable) {
        this.variableDefinition = new GroovyVariableDefinition(model);
        this.face = face;
        variableDefinition.setFrom(model.VariableFrom(modelIndex, newValue, variable));
        variableDefinition.setTo(model.VariableTo(modelIndex, newValue, variable));
        variableDefinition.setShade(model.VariableShade(modelIndex, true));
        variableDefinition.setVisible(model.VariableVisible(modelIndex, true));
        variableDefinition.setLight(model.VariableLight(modelIndex, "0"))
        variableDefinition.setColour(model.VariableColour(modelIndex, "-1"));
    }
/*
    void addQuads(List<MutableQuad> addTo, ITextureGetter spriteLookup) {
        if (visible.getValue()) {
            float[] f = bakePosition(variableDefinition.getFrom());
            float[] t = bakePosition(variableDefinition.getTo());
            boolean s = shade.getValue();
            int l = (int) (variableDefinition.getLight().getValue() & 15);
            //int rgba = RenderUtil.swapARGBforABGR((int) colour.evaluate());
            for (EnumFacing face : EnumFacing.VALUES) {
                VariableFaceData data = getFaceData(face, spriteLookup);
                if (data != null) {
                    Vector3f radius = new Vector3f(t[0] - f[0] as float, t[1] - f[1] as float, t[2] - f[2] as float);
                    radius.scale(0.5f);
                    Vector3f center = new Vector3f(f);
                    center.add(radius);
                    MutableQuad quad = ModelUtil.createFace(face, center, radius, data.uvs);
                    quad.rotateTextureUp(data.rotations);
                    quad.lighti(l, 0);
                    quad.colouri(rgba);
                    quad.texFromSprite(data.sprite);
                    quad.setSprite(data.sprite);
                    quad.setShade(s);
                    if (data.bothSides) {
                        addTo.add(quad.copyAndInvertNormal());
                    } else if (data.invertNormal) {
                        quad = quad.copyAndInvertNormal();
                    }
                    addTo.add(quad);
                }
            }
        }
    }
*/
}
