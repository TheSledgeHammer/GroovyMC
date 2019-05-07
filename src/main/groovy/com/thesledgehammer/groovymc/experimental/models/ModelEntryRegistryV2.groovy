package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.api.ISprite
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelEntryRegistryV2 {

    private static final ModelEntryV2 MODEL_ENTRIES = new ModelEntryV2.Register().build();
    private static final TextureEntryV2 TEXTURE_ENTRIES = new TextureEntryV2.Register().build();

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(ModelEntryRegistryV2.class);
    }

    static void init() {

    }

    static void postInit() {

    }

    static void onTextureStitchPre(TextureMap map) {
        for(int i = 0; i < TEXTURE_ENTRIES.getTextureAtlasSprites().size(); i++) {
            if(TEXTURE_ENTRIES.getTextureAtlasSprites().get(i) instanceof ISprite) {
                TEXTURE_ENTRIES.GroovyDefinitionContext().onTextureStitchPre(map);
            }
        }

        for(int i = 0; i < TEXTURE_ENTRIES.getResourceLocations().size(); i++) {
            map.registerSprite(TEXTURE_ENTRIES.getResourceLocations().get(i));
        }

        for(int i = 0; i < TEXTURE_ENTRIES.getTextureAtlasSprites().size(); i++) {
            map.setTextureEntry(TEXTURE_ENTRIES.getTextureAtlasSprites().get(i));
        }
    }

    @SubscribeEvent
    static void onBakeModels(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        ModelResourceLocation resourceLocation = null;
        IBakedModel bakedModel = null;

        for(ModelResourceLocation rec : MODEL_ENTRIES.getModelResourceLocations()) {
            resourceLocation = rec;
        }

        for(IBakedModel bake : MODEL_ENTRIES.getIBakedModels()) {
            bakedModel = bake;
        }
        registry.putObject(resourceLocation, bakedModel);
    }
}
