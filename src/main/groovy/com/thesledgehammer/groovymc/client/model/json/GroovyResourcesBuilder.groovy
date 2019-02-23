/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.ResourceLocation

//TODO:
//- TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
//- TextureAtlasSprite from resourceLocation
//- Dealing with situations that use Custom & Default together

class GroovyResourcesBuilder {

    private ResourceLocation resourceLocation;
    private ModelResourceLocation modelResourceLocation;
    private TextureAtlasSprite textureAtlasSprite;

    private GroovyResourcesBuilder(Builder builder) {
        this(builder.resourceLocation, builder.modelResourceLocation, builder.textureAtlasSprite);
    }

    private GroovyResourcesBuilder(ResourceLocation resourceLocation, ModelResourceLocation modelResourceLocation, TextureAtlasSprite textureAtlasSprite) {
        this.resourceLocation = resourceLocation;
        this.modelResourceLocation = modelResourceLocation;
        this.textureAtlasSprite = textureAtlasSprite;
    }

    ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    ModelResourceLocation getModelResourceLocation() {
        return modelResourceLocation;
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return textureAtlasSprite;
    }

    static class Builder {

        private ResourceLocation resourceLocation;
        private ModelResourceLocation modelResourceLocation;
        private TextureAtlasSprite textureAtlasSprite;
        private String resourceDirectory;
        private String modelDirectory;

        Builder() {

        }

        Builder(ResourceLocation resourceLocation, ModelResourceLocation modelResourceLocation, TextureAtlasSprite textureAtlasSprite) {
            this.resourceLocation = resourceLocation;
            this.modelResourceLocation = modelResourceLocation;
            this.textureAtlasSprite = textureAtlasSprite;
        }

        Builder setResourceLocation(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
            return this;
        }

        Builder setModelResourceLocation(ModelResourceLocation modelResourceLocation) {
            this.modelResourceLocation = modelResourceLocation;
            return this;
        }

        Builder setTextureAtlasSprite(TextureAtlasSprite textureAtlasSprite) {
            this.textureAtlasSprite = textureAtlasSprite;
            return this;
        }

        Builder setResourceLocation(String resource) {
            this.resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), resource);
            return this;
        }

        Builder setResourceLocation(String modID, String resource) {
            this.resourceLocation = new ResourceLocation(modID, resource);
            return this;
        }

        Builder setModelResourceLocation(String modelLocation) {
            this.modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory")
            return this;
        }

        Builder setTextureAtlasSprite(String sprite) {
            this.textureAtlasSprite = TextureAtlas.createForConfig(sprite);
            return this;
        }

        Builder setTextureAtlasSprite(ResourceLocation spriteLocation) {
            this.textureAtlasSprite = TextureAtlas.createForConfig(spriteLocation);
            return this;
        }

        Builder setTextureAtlasSprite(String modID, String baseName) {
            this.textureAtlasSprite = TextureAtlas.createForConfig(modID, baseName);
            return this;
        }

        Builder setCustomResourceLocation(String type, String fileName) {
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
            return this;
        }

        Builder setCustomResourceLocation(String modID, String type, String fileName) {
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
            return this;
        }

        Builder setCustomModelResourceLocation(String type, String fileName) {
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
            return this;
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

        GroovyResourcesBuilder build() {
            return new GroovyResourcesBuilder(this);
        }

        private static class TextureAtlas extends TextureAtlasSprite {

            protected TextureAtlas(String spriteName) {
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
}
