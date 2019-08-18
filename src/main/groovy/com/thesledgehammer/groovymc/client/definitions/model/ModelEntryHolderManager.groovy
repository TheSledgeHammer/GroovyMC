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

package com.thesledgehammer.groovymc.client.definitions.model

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.api.client.ISprite
import net.minecraft.block.Block
import net.minecraft.client.renderer.model.IBakedModel
import net.minecraft.client.renderer.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.client.model.ModelLoader

@OnlyIn(Dist.CLIENT)
class ModelEntryHolderManager implements IModelEntryHolderManager {

    private static final ModelEntryHolderManager instance = new ModelEntryHolderManager();

    private final Set<IInitModel> initModels = new HashSet<>();

    private final List<ModelEntryHolder> HOLDERS = new ArrayList<>();
    private final List<ModelEntry> MODEL_ENTRIES = new ArrayList<>();
    final List<TextureEntry> TEXTURE_ENTRIES = new ArrayList<>();

    //private final ModelEntry MODEL_ENTRY = ModelEntry.Instance();
    //private final TextureEntry TEXTURE_ENTRY = TextureEntry.Instance();

    static ModelEntryHolderManager Instance() {
        return instance;
    }

    List<ModelEntryHolder> ENTRY_HOLDERS() {
        return HOLDERS;
    }

    @Override
    void registerModel(Item item, int meta, ModelResourceLocation modelResourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, meta, modelResourceLocation);
    }

    @Override
    void registerModel(Item item, int meta, String resourceLocation) {
        ModelLoader.setCustomModelResourceLocation(item, meta, getModelLocation(resourceLocation));
    }

    @Override
    void registerModel(Item item, int meta, String domain, String path) {
        ModelLoader.setCustomModelResourceLocation(item, meta, getModelLocation(domain, path));
    }

    @Override
    ModelResourceLocation getModelLocation(String domain, String path) {
        ResourceLocation location = new ResourceLocation(domain, path);
        ModelResourceLocation modelLocation = new ModelResourceLocation(location, "inventory");
        return modelLocation;
    }

    @Override
    ModelResourceLocation getModelLocation(String resourceLocation) {
        ResourceLocation location = new ResourceLocation(resourceLocation);
        ModelResourceLocation modelLocation = new ModelResourceLocation(location, "inventory");
        return modelLocation;
    }

    @OnlyIn(Dist.CLIENT)
    void initModels() {
        for(IInitModel initModel : initModels) {
            Item item = null;
            if(initModel instanceof Block) {
                item = Item.getItemFromBlock((Block) initModel);
            } else if(initModel instanceof Item) {
                item = (Item) initModel;
            }
            if(item != null) {
                //initModel.initModel(item, this);
                initModel.initModel();
            }
        }
    }

    void registerModelEntry(ModelEntry model) {
        this.MODEL_ENTRIES.add(model);
    }

    void registerTextureEntry(TextureEntry texture) {
        this.TEXTURE_ENTRIES.add(texture);
    }

    void onTextureStitchPre(TextureMap map) {
        Set<ResourceLocation> toStitch = new HashSet<>();
        if(hasEntries()) {
            for (ModelEntryHolder holder : ENTRY_HOLDERS()) {
                holder.onTextureStitchPre(toStitch);

                for(TextureEntry entry : TEXTURE_ENTRIES) {
                    if(entry.getTextureAtlasSprite() instanceof ISprite) {
                        entry.onTextureStitchPre();
                    }
                    map.setTextureEntry(entry.getTextureAtlasSprite());
                    map.registerSprite(entry.getSpriteResourceLocation());
                }
            }
        }
    }

    void onModelBake(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        if(hasEntries()) {
            for (ModelEntryHolder holder : ENTRY_HOLDERS()) {
                holder.onModelBake();

                for(final ModelEntry entry : MODEL_ENTRIES) {
                    registry.putObject(entry.getModelResourceLocation(), entry.getBakedModel());
                }
            }
        }
    }

    private boolean hasEntries() {
        if(!ENTRY_HOLDERS().isEmpty()) {
            return true;
        }
        return false;
    }
}
