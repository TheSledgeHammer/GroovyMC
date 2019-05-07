/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.ISprite
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

class GroovyResourceDefinition {

    private String resourceDirectory;
    private String modelDirectory;
    private ResourceLocation resourceLocation;
    private TextureAtlasSprite textureAtlasSprite;
    private ModelResourceLocation modelResourceLocation;
    private List<ResourceLocation> resourceLocationList = new LinkedList<>();
    private List<TextureAtlasSprite> textureAtlasSpriteList = new LinkedList<>();
    private List<ModelResourceLocation> modelResourceLocationList = new LinkedList<>();

    ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    ModelResourceLocation getModelResourceLocation() {
        return modelResourceLocation;
    }

    TextureAtlasSprite getTextureAtlasSprite() {
        return textureAtlasSprite;
    }

    List<ResourceLocation> getResourceLocations() {
        return resourceLocationList;
    }

    List<ModelResourceLocation> getModelResourceLocations() {
        return modelResourceLocationList;
    }

    List<TextureAtlasSprite> getTextureAtlasSprites() {
        return textureAtlasSpriteList;
    }

    void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    void setModelResourceLocation(ModelResourceLocation modelResourceLocation) {
        this.modelResourceLocation = modelResourceLocation;
        modelResourceLocationList.add(this.modelResourceLocation);
    }

    void setTextureAtlasSprite(TextureAtlasSprite textureAtlasSprite) {
        this.textureAtlasSprite = textureAtlasSprite;
        textureAtlasSpriteList.add(this.textureAtlasSprite);
    }

    void setResourceLocation(String resource) {
        this.resourceLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), resource);
        resourceLocationList.add(this.resourceLocation);
    }

    void setResourceLocation(String modID, String resource) {
        this.resourceLocation = new ResourceLocation(modID, resource);
        resourceLocationList.add(this.resourceLocation);
    }

    void setModelResourceLocation(String modelLocation) {
        this.modelResourceLocation = new ModelResourceLocation(modelLocation, "inventory")
        modelResourceLocationList.add(this.modelResourceLocation);
    }

    void setTextureAtlasSprite(String sprite) {
        if(this.textureAtlasSprite instanceof ISprite) {
            this.textureAtlasSprite = GroovyISpriteDefinition.createForConfig(sprite);
        }
        this.textureAtlasSprite = GroovyAtlasSpriteDefinition.createForConfig(sprite);
        textureAtlasSpriteList.add(this.textureAtlasSprite);
    }

    void setTextureAtlasSprite(ResourceLocation spriteLocation) {
        if(this.textureAtlasSprite instanceof ISprite) {
            this.textureAtlasSprite = GroovyISpriteDefinition.createForConfig(spriteLocation);
        }
        this.textureAtlasSprite = GroovyAtlasSpriteDefinition.createForConfig(spriteLocation);
        textureAtlasSpriteList.add(this.textureAtlasSprite);
    }

    void setTextureAtlasSprite(String modID, String baseName) {
        if(this.textureAtlasSprite instanceof ISprite) {
            this.textureAtlasSprite = GroovyISpriteDefinition.createForConfig(modID, baseName);
        }
        this.textureAtlasSprite = GroovyAtlasSpriteDefinition.createForConfig(modID, baseName);
        textureAtlasSpriteList.add(this.textureAtlasSprite);
    }

    void onTextureStitchPre(TextureMap map) {
        GroovyISpriteDefinition.onTextureStitchPre(map, getTextureAtlasSprite(), getResourceLocation());
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
        resourceLocationList.add(this.resourceLocation);
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
        resourceLocationList.add(this.resourceLocation);
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
        modelResourceLocationList.add(this.modelResourceLocation);
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
}
