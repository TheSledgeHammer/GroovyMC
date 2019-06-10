package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import net.minecraft.util.EnumFacing

//Temporary PlaceHolder
class GroovyVariableDefinition {

    private final GroovysonVariableModel model;
    private VariableDouble[] from;
    private VariableDouble[] to;
    private VariableDouble[] uv;
    private VariableBoolean shade;
    private VariableBoolean visible;
    private VariableLong colour
    private VariableLong light
    private VariableBoolean invert;
    private VariableBoolean bothSides;
    private VariableLong textureRotation;
    private VariableObject<String> texture;

    GroovyVariableDefinition(GroovysonVariableModel model) {
        this.model = model
    }

    void setFrom(List<VariableDouble> from) {
        this.from = from;
    }

    void setTo(List<VariableDouble> to) {
        this.to = to;
    }

    void setUV(List<VariableDouble> uv) {
        this.uv = uv;
    }

    void setShade(VariableBoolean shade) {
        this.shade = shade;
    }

    void setVisible(VariableBoolean visible) {
        this.visible = visible;
    }

    void setColour(VariableLong colour) {
        this.colour = colour;
    }

    void setLight(VariableLong light) {
        this.light = light;
    }

    void setInvert(VariableBoolean invert) {
        this.invert = invert;
    }

    void setBothSides(VariableBoolean bothSides) {
        this.bothSides = bothSides;
    }

    void setTexture(VariableObject<String> texture) {
        this.texture = texture;
    }

    void setTextureRotation(VariableLong textureRotation) {
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

    VariableBoolean getShade() {
        return shade;
    }

    VariableBoolean getVisible() {
        return visible;
    }

    VariableLong getColour() {
        return colour;
    }

    VariableLong getLight() {
        return light
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

    GroovysonVariableModel getModel() {
        return model;
    }

    float[] bakePosition(VariableDouble[] vIn) {
        float x = (float) (vIn[0].getValue() / 16f);
        float y = (float) (vIn[1].getValue() / 16f);
        float z = (float) (vIn[2].getValue() / 16f);
        return [x, y, z];
    }
}
