package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonAbstractModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonStaticModel
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.vecmath.Vector3f

//Based from BC8's VariableCuboidBase
abstract class VariableBase {

    static VariableDouble[] from;
    static VariableDouble[] to;
    static VariableBoolean visible;
    static VariableBoolean shade;
    static VariableLong light;
    static VariableLong colour;

    VariableBase(GroovysonAbstractModel groovysonModel) {
        from = new VariableDouble[groovysonModel.getObjectCache().From()];
        to = new VariableDouble[groovysonModel.getObjectCache().To()];
        shade = new VariableBoolean(groovysonModel.getObjectCache().Shade(true));
        visible = new VariableBoolean(groovysonModel.getObjectCache().Visible())
        light = new VariableLong(groovysonModel.getObjectCache().Light())
        colour = new VariableLong(groovysonModel.getObjectCache().Colour());
    }

    static void addQuads(List<MutableQuad> addTo) {
        float[] f = bakePosition(from);
        float[] t = bakePosition(to);
        boolean s = shade;
        int l = (int) (light & 15);
        int rgba = 0;
        for(EnumFacing face : EnumFacing.VALUES) {
            VariableFaceData data = getFaceData(face);
            if(data != null) {
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

    static float[] bakePosition(VariableDouble[] In) {
        float x = (float) (In[0].getValue() / 16f);
        float y = (float) (In[1].getValue() / 16f);
        float z = (float) (In[2].getValue() / 16f);
        return [x, y, z];
    }

    protected abstract VariableFaceData getFaceData(EnumFacing side);

    static class VariableFaceData {
        public ModelUtil.UvFaceData uvs = new ModelUtil.UvFaceData();
        public TextureAtlasSprite sprite;
        public int rotations = 0;
        public boolean invertNormal = false;
        public boolean bothSides = false;
    }
}
