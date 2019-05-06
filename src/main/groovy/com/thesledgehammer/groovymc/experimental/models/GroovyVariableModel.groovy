package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonModel
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey

//Work In Progress
class GroovyVariableModel {

    private GroovysonModel GROOVY_MODEL;
    private GroovyDefinitionContext GDC;
    private JsonRule[] rules;

    GroovyVariableModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonModel(resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovyVariableModel(GroovysonModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovyDefinitionContext GroovyDefinitionContext() {
        return GDC;
    }

    GroovysonModel getGroovysonModel() {
        return GROOVY_MODEL;
    }

    void setRenderKeysDefintion(GroovysonModel GROOVY_MODEL) {
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
    }

    MutableQuad[] bakePart(ArrayList<GroovysonObjectPart > modelParts) {
        List<MutableQuad> list = new ArrayList<>();
        for (GroovysonObjectPart part : modelParts) {

        }
        for(JsonRule rule : rules) {

        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    MutableQuad[] getCutoutQuads() {
        return bakePart(GDC.getCutoutKey().getCutoutModelElements());
    }

    MutableQuad[] getTranslucentQuads() {
        return bakePart(GDC.getTranslucentKey().getTranslucentModelElements());
    }

    MutableQuad[] getSolidQuads() {
        return bakePart(GDC.getSolidKey().getSolidModelElements());
    }

    MutableQuad[] getCutoutMippedQuads() {
        return bakePart(GDC.getCutoutMippedKey().getCutoutMippedModelElements());
    }
}
