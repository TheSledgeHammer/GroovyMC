package com.thesledgehammer.groovymc.experimental.bakedmodels

import com.thesledgehammer.groovymc.client.model.json.GroovysonModel

class GroovyAbstractModelBaker implements IGroovyAbstractModelBaker{


    private boolean isGui3d;
    private boolean isAmbientOcclusion;
    private GroovysonModel GROOVYSON_MODEL;
    String name;
    private float[] rotation;
    private float[] translation;
    private float[] scale;


    GroovyAbstractModelBaker(String resourceObject, String fileName) {
        this.GROOVYSON_MODEL = new GroovysonModel(resourceObject, fileName);
    }

    void setModelParts(String name) {
        GROOVYSON_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String textureName) {
        GROOVYSON_MODEL.setRawModelTextures(textureName);
        GROOVYSON_MODEL.JsonTextureMapping();
    }

    @Override
    void setGui3d(boolean gui3d) {

    }

    @Override
    void setAmbientOcclusion(boolean ambientOcclusion) {

    }

    //Add Name for TESR from AbstractModel
    @Override
    void setRotation(double[] rotation) {
        this.rotation = rotation;
        if(rotation == null && GROOVYSON_MODEL.Rotation(name) != null) {
            this.rotation = GROOVYSON_MODEL.Rotation(name);
        }

        if(rotation == null && GROOVYSON_MODEL.Rotation(name) == null) {
            this.rotation = DefaultRotation();
        }
    }

    //Add Name for TESR from AbstractModel
    @Override
    void setTranslation(double[] translation) {
        this.translation = translation;
        if(translation == null) {
            if(GROOVYSON_MODEL.Translation(name) != null) {
                this.translation = GROOVYSON_MODEL.Translation(name);
            }
        } else {
            this.translation = DefaultTranslation();
        }
    }

    //Add Name for TESR from AbstractModel
    @Override
    void setScale(double[] scale) {
        this.scale = scale;
        if(scale == null && GROOVYSON_MODEL.Scale(name) != null) {
            this.scale = GROOVYSON_MODEL.Scale(name);
        }

        if(scale == null && GROOVYSON_MODEL.Scale(name) == null) {
            this.scale = DefaultScale();
        }
    }

    GroovysonModel getAbstractModel() {
        return GROOVYSON_MODEL;
    }

    @Override
    double[] getRotation() {
        return rotation;
    }

    @Override
    double[] getTranslation() {
        return translation;
    }

    @Override
    double[] getScale() {
        return scale;
    }

    @Override
    boolean isAmbientOcclusion() {
        return false
    }

    @Override
    boolean isGui3d() {
        return false
    }

    @Override
    float[] bakePosition(double[] bakeIn) {
        return new float[0]
    }

    private static float[] DefaultRotation() {
        return [-80, -45, 170];
    }

    private static float[] DefaultTranslation() {
        return [0, 1.5F, -2.75F];
    }

    private static float[] DefaultScale() {
        return [0.375F, 0.375F, 0.375F];
    }
}
