package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject

//Based from BC8's JsonVariableFaceUV
//TODO: Complete: textureRotation, Invert, BothSides & Texture
class GroovysonVariableFaceUV implements VariableTraits {
    
    private VariableDouble[] from;
    private VariableDouble[] to;
    private VariableDouble[] uv;
    private VariableLong textureRotation;
    private VariableBoolean visible;
    private VariableBoolean invert;
    private VariableBoolean bothSides;
    private VariableObject<String> texture;
    
    GroovysonVariableFaceUV(GroovysonVariableModel model, int modelIndex, String newValue, String variable) {
        setFrom(model.VariableFrom(modelIndex, newValue, variable));
        setTo(model.VariableTo(modelIndex, newValue, variable));
        setUV(model.VariableUV(modelIndex, newValue, variable));
        setVisible(model.VariableVisible(modelIndex, true));
        setInvert(model.VariableInvert(modelIndex, false));
        setBothSides(model.VariableBothSides(modelIndex, false));
        setTexture(model.VariableTexture(modelIndex));
        setTextureRotation(model.TextureRotation(modelIndex, "0"));
    }
    
    private void setFrom(List<VariableDouble> from) {
        this.from = from;
    }

    private void setTo(List<VariableDouble> to) {
        this.to = to;
    }

    private void setUV(List<VariableDouble> uv) {
        this.uv = uv;
    }

    private void setVisible(VariableBoolean visible) {
        this.visible = visible;
    }

    private void setInvert(VariableBoolean invert) {
        this.invert = invert;
    }

    private void setBothSides(VariableBoolean bothSides) {
        this.bothSides = bothSides;
    }

    private void setTexture(VariableObject<String> texture) {
        this.texture = texture;
    }

    private void setTextureRotation(VariableLong textureRotation) {
        this.textureRotation = textureRotation;
    }

    VariableDouble[] getFrom() {
        return from;
    }

    VariableDouble[] getTo() {
        return to;
    }

    VariableDouble[] getFaceUV() {
        return uv;
    }

    VariableBoolean getVisible() {
        return visible;
    }

    VariableBoolean getInvert() {
        return invert;
    }

    VariableBoolean getBothSides() {
        return bothSides;
    }

    VariableObject<String> getTexture() {
        return texture;
    }

    VariableLong getTextureRotation() {
        return textureRotation;
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
