package com.thesledgehammer.groovymc.experimental.models.testing

import com.thesledgehammer.groovymc.api.ISprite
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge

class ModelEntryHolderRegistry {

    static final List<ModelEntryHolder> HOLDERS = new ArrayList<>();

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(ModelEntryHolderRegistry.class);
    }

    static void init() {

    }

    static void postInit() {

    }

    static void onTextureStitchPre(TextureMap map) {
        Set<ResourceLocation> toStitch = new HashSet<>();
        for(ModelEntryHolder holder : HOLDERS) {
            holder.onTextureStitchPre(toStitch);
            TextureEntryV2 textureEntry = holder.TextureEntry();

            for(TextureAtlasSprite sprite : textureEntry.getTextureAtlasSprites()) {
                if(textureEntry.getTextureAtlasSprite(sprite) instanceof ISprite) {
                    textureEntry.GroovyDefinitionContext().onTextureStitchPre(map);
                }
                map.setTextureEntry(textureEntry.getTextureAtlasSprite(sprite));
            }
            for(ResourceLocation location : textureEntry.getResourceLocations()) {
                map.registerSprite(textureEntry.getResourceLocation(location));
            }
        }
    }

    static void onModelBake(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for(ModelEntryHolder holder : HOLDERS) {
            holder.onModelBake();
            ModelEntryV2 modelEntry = holder.ModelEntry();

            for(ModelResourceLocation modelResource : modelEntry.getModelResourceLocations()) {
                for(IBakedModel bakedModel : modelEntry.getIBakedModels()) {
                    registry.putObject(modelEntry.getModelResourceLocation(modelResource), modelEntry.getIBakedModel(bakedModel));
                }
            }
        }
    }
}
