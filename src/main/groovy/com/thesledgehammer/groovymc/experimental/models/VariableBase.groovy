package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.vecmath.Vector3f

//Based from BC8's VariableCuboidBase
abstract class VariableBase {

    static final VariableDouble[] from;
    static final VariableDouble[] to;
    static final VariableBoolean visible;
    static final VariableBoolean shade;
    static final VariableLong light;
    static final VariableLong colour;

    VariableBase(GroovysonModel groovysonModel) {
        /*from = new VariableDouble[groovysonModel.getObjectCache().From()];
        to = groovysonModel.getObjectCache().To()
        shade = groovysonModel.getObjectCache().Shade(true);
        visible = groovysonModel.getObjectCache().Visible()
        light = groovysonModel.getObjectCache().Light()
        colour = groovysonModel.getObjectCache().Colour()
        */
    }

    static void addQuads(List<MutableQuad> addTo) {
        float[] f = bakePosition(from);
        float[] t = bakePosition(to);
        boolean s = shade;
        int l = (light & 15);
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
        float x = In[0] / 16f;
        float y = In[1] / 16f;
        float z = In[2] / 16f;
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
