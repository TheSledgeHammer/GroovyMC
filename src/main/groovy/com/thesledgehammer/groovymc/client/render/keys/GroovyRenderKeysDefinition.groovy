package com.thesledgehammer.groovymc.client.render.keys

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel

//WIP: Can apply a various BlockRenderLayers to a model from the model's json file.
class GroovyRenderKeysDefinition {

    private CutoutKey cutoutKey;
    private CutoutMippedKey cutoutMippedKey;
    private SolidKey solidKey;
    private TranslucentKey translucentKey;
    private GroovyBaseModel groovyBaseModel;

    GroovyRenderKeysDefinition() {
        setGroovyBaseModel(null);
    }

    GroovyRenderKeysDefinition(GroovyBaseModel groovyBaseModel) {
        setGroovyBaseModel(groovyBaseModel);
    }

    GroovyBaseModel getGroovyBaseModel() {
        return groovyBaseModel;
    }

    CutoutKey getCutoutKey() {
        return cutoutKey;
    }

    CutoutMippedKey getCutoutMippedKey() {
        return cutoutMippedKey;
    }

    SolidKey getSolidKey() {
        return solidKey;
    }

    TranslucentKey getTranslucentKey() {
        return translucentKey;
    }

    void setGroovyBaseModel(GroovyBaseModel groovyBaseModel) {
        this.groovyBaseModel = groovyBaseModel;
    }

    void setCutoutKey(CutoutKey cutoutKey) {
        this.cutoutKey = cutoutKey;
    }

    void setCutoutKey(int element) {
        this.cutoutKey = new CutoutKey(groovyBaseModel, element);
    }

    void setCutoutMippedKey(CutoutMippedKey cutoutMippedKey) {
        this.cutoutMippedKey = cutoutMippedKey;
    }

    void setCutoutMippedKey(int element) {
        this.cutoutMippedKey = new CutoutMippedKey(groovyBaseModel, element);
    }

    void setSolidKey(SolidKey solidKey) {
        this.solidKey = solidKey;
    }

    void setSolidKey(int element) {
        this.solidKey = new SolidKey(groovyBaseModel, element);
    }

    void setTranslucentKey(TranslucentKey translucentKey) {
        this.translucentKey = translucentKey;
    }

    void setTranslucentKey(int element) {
        this.translucentKey = new TranslucentKey(groovyBaseModel, element);
    }

    List<String> getRenderKeyTypes(int element) {
        return getGroovyBaseModel().getGroovyModel().getRawModelPartRenderLayers(element);
    }

    boolean contains(int element, String renderKeyType) {
        if(getRenderKeyTypes(element).contains(renderKeyType)) {
            return true;
        }
        return false;
    }
}
