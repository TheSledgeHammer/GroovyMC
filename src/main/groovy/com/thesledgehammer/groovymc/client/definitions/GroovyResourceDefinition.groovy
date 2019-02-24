/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

class GroovyResourceDefinition {

    private ResourceLocation resourceLocation;
    private ModelResourceLocation modelResourceLocation;
    private TextureAtlasSprite textureAtlasSprite;
    private String resourceDirectory;
    private String modelDirectory;

    ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    ModelResourceLocation getModelResourceLocation() {
        return modelResourceLocation;
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return textureAtlasSprite;
    }

    void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    void setModelResourceLocation(ModelResourceLocation modelResourceLocation) {
        this.modelResourceLocation = modelResourceLocation;
    }

    void setTextureAtlasSprite(TextureAtlasSprite textureAtlasSprite) {
        this.textureAtlasSprite = textureAtlasSprite;
    }

    void setResourceLocation(String resource) {
        this.resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), resource);
    }

    void setResourceLocation(String modID, String resource) {
        this.resourceLocation = new ResourceLocation(modID, resource);
    }

    void setModelResourceLocation(String modelLocation) {
        this.modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory")
    }

    void setTextureAtlasSprite(String sprite) {
        this.textureAtlasSprite = TextureAtlasDefintion.createForConfig(sprite);
    }

    void setTextureAtlasSprite(ResourceLocation spriteLocation) {
        this.textureAtlasSprite = TextureAtlasDefintion.createForConfig(spriteLocation);
    }

    void setTextureAtlasSprite(String modID, String baseName) {
        this.textureAtlasSprite = TextureAtlasDefintion.createForConfig(modID, baseName);
    }

    void setCustomResourceLocation(String type, String fileName) {
        setResourceDirectory(type, fileName);
        String resource = "";
        for (int i = 0; i < resourceDirectory.length(); i++) {
            if (resourceDirectory.contains(type)) {
                int idx = resourceDirectory.indexOf(type);
                resource = resourceDirectory.substring(idx);
            }
            if (type.isEmpty() || type == "") {
                int idx = resourceDirectory.indexOf(fileName);
                resource = resourceDirectory.substring(idx);
            }
        }
        this.resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), resource);
    }

    void setCustomResourceLocation(String modID, String type, String fileName) {
        setResourceDirectory(type, fileName);
        String resource = "";
        for (int i = 0; i < resourceDirectory.length(); i++) {
            if (resourceDirectory.contains(type)) {
                int idx = resourceDirectory.indexOf(type);
                resource = resourceDirectory.substring(idx);
            }
            if (type.isEmpty() || type == "") {
                int idx = resourceDirectory.indexOf(fileName);
                resource = resourceDirectory.substring(idx);
            }
        }
        this.resourceLocation = new ResourceLocation(modID, resource);
    }

    void setCustomModelResourceLocation(String type, String fileName) {
        setModelResourceDirectory(type, fileName);
        String modelLocation = "";
        for (int i = 0; i < modelDirectory.length(); i++) {
            if (modelDirectory.contains(type)) {
                int idx = modelDirectory.indexOf(type);
                modelLocation = modelDirectory.substring(idx);
            }
            if (type.isEmpty() || type == "") {
                int idx = modelDirectory.indexOf(fileName);
                modelLocation = modelDirectory.substring(idx);
            }
        }
        this.modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory");
    }

    private void setResourceDirectory(String type, String fileName) {
        this.resourceDirectory = new File(GroovyLoader.Instance().getModPath() + "/" + GroovyLoader.Instance().getModResourcePath() + "/" + GroovyLoader.Instance().getModID() + "/" + type + "/" + fileName).absolutePath;
        if (type.isEmpty() || type == "") {
            this.resourceDirectory = new File(GroovyLoader.Instance().getModPath() + "/" + GroovyLoader.Instance().getModResourcePath() + "/" + GroovyLoader.Instance().getModID() + "/" + fileName).absolutePath;
        }
    }

    private void setModelResourceDirectory(String type, String fileName) {
        this.modelDirectory = new File(GroovyLoader.Instance().getModPath() + "/" + GroovyLoader.Instance().getModResourcePath() + "/" + GroovyLoader.Instance().getModID() + "/models/" + type + "/" + fileName).absolutePath;
        if (type.isEmpty() || type == "") {
            this.modelDirectory = new File(GroovyLoader.Instance().getModPath() + "/" + GroovyLoader.Instance().getModResourcePath() + "/" + GroovyLoader.Instance().getModID() + "/models/" + fileName).absolutePath;
        }
    }

    private static class TextureAtlasDefintion extends TextureAtlasSprite {

        protected TextureAtlasDefintion(String spriteName) {
            super(spriteName)
        }

        static TextureAtlasSprite createForConfig(ResourceLocation baseName) {
            return makeAtlasSprite(baseName);
        }

        static TextureAtlasSprite createForConfig(String modID, String baseName) {
            ResourceLocation resourceLocation = new ResourceLocation(modID, baseName);
            return makeAtlasSprite(resourceLocation);
        }

        static TextureAtlasSprite createForConfig(String baseName) {
            ResourceLocation resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), baseName);
            return makeAtlasSprite(resourceLocation);
        }
    }
}
