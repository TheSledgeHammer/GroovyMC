package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.ModelEntryBakery
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import com.thesledgehammer.groovymc.client.model.MutableQuad
import net.minecraft.util.ResourceLocation

//Work In Progress
class ModelEntryVariable extends ModelEntryBakery<ModelEntry, TextureEntry>  {

    private GroovyVariableModel groovyVariableModel;
    private boolean unseen = true;

    ModelEntryVariable(GroovyVariableModel groovyVariableModel) {
        this.groovyVariableModel = groovyVariableModel;
        groovyVariableModel.setRenderKeysDefintion(groovyVariableModel.getGroovysonModel());
    }

    @Override
    boolean hasBakedQuads() {
        return groovyVariableModel != null;
    }

    @Override
    void onTextureStitchPre(Set<ResourceLocation> toRegisterSprites) {

    }

    @Override
    protected void onModelBake() {

    }

    MutableQuad[] getCutoutQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return null;
    }

    MutableQuad[] getTranslucentQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return null;
    }

    MutableQuad[] getSolidQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return null;
    }

    MutableQuad[] getCutoutMippedQuads() {
        if(groovyVariableModel == null) {
            return MutableQuad.EMPTY_ARRAY;
        }
        return null;
    }
}
