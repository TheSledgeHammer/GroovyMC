/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.AbstractModel
import com.thesledgehammer.groovymc.client.model.MutableGroovyModel
import com.thesledgehammer.groovymc.client.model.json.GroovyModelBuilder
import com.thesledgehammer.groovymc.client.model.json.GroovyResourcesBuilder
import com.thesledgehammer.groovymc.client.model.json.JsonTexture

/**
 * TODO: Models & Rendering
 * - AbstractModels creation of BakedModels and use of IBakedModels: Partially Complete: ModelTools creates a List of BakedQuads
 * - Rendering of AbstractModels: Mostly Complete within AbstractModels
 * - Registering AbstractModels
 * - Converting information from an AbstractModel to something Minecraft can understand and Interpret
 **/

//May work having said class implementing an IBakedModel that references to a static final AbstractModel

class GBlockBakedModel  {

    private AbstractModel Baked_Model;// = new AbstractModel("block", "gblock");
    private GroovyResourcesBuilder groovyResourcesBuilder;
    private GroovyModelBuilder groovyModelBuilder;

    GBlockBakedModel(String type, String fileName) {
        this.Baked_Model = new AbstractModel(type, fileName);
    }

    void setModelParts(String name) {
        Baked_Model.setRawModelParts(name);
    }

    private List<String> textureNames = new ArrayList<>();
    void setModelTextures(String textureName) {
        Baked_Model.setRawModelTextures(textureName);
        Baked_Model.JsonTextureMapping();
        textureNames.add(textureName);
    }

    List<String> getModelTexturesNames() {
        return textureNames;
    }

    HashMap<String, String> getModelTextures() {
        return Baked_Model.getRawModelTextures();
    }

//ModelTools
    void buildTextureMap(String resourceLoc, String modelLoc, String sprite) {
        this.groovyResourcesBuilder = new GroovyResourcesBuilder.Builder()
                .setResourceLocation(resourceLoc)
                .setModelResourceLocation(modelLoc)
                .setTextureAtlasSprite(sprite)
                .build();
    }

    void buildModelBaker(MutableGroovyModel mutableGroovyModel) {
        this.groovyModelBuilder = new GroovyModelBuilder.Builder()
                .setIBakedModel(mutableGroovyModel)
                .build();
    }

    GroovyResourcesBuilder getGroovyResourcesBuilder() {
        return groovyResourcesBuilder;
    }

    GroovyModelBuilder getGroovyModelBuilder() {
        return groovyModelBuilder;
    }

    AbstractModel getAbstractModel() {
        return Baked_Model;
    }
}
