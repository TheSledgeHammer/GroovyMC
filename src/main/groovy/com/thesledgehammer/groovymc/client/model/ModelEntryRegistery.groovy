package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.client.definitions.GroovyAtlasSpriteDefinition
import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.IRegistry
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelEntryRegistery  {

    private static final List<ModelEntry> MODEL_ENTRIES = new ArrayList<>();
    private static final List<TextureEntry> TEXTURE_ENTRIES = new ArrayList<>();

    static void preInit() {
        MinecraftForge.EVENT_BUS.register(ModelEntryRegistery.class);
    }

    static void init() {

    }

    static void postInit() {

    }

    static void onTextureStitchPre(TextureMap map) {
        Set<ResourceLocation> toStitch = new HashSet<>();
        for(TextureEntry entry : TEXTURE_ENTRIES) {
            entry.onTextureStitchPre(toStitch);
            //toStitch.add(entry.getResourceLocation());
        }
        for(ResourceLocation loc : toStitch) {
            map.setTextureEntry(GroovyAtlasSpriteDefinition.createForConfig(loc));
        }
    }

    static void registerTexture(TextureEntry index) {
        TEXTURE_ENTRIES.add(index);
    }

    static void registerModel(ModelEntry index) {
        MODEL_ENTRIES.add(index);
    }

    @SubscribeEvent
    static void onBakeModels(ModelBakeEvent event) {
        IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for(ModelEntry entry : MODEL_ENTRIES) {
            registry.putObject(entry.getModelResourceLocation(), entry.getIBakedModel());
        }
    }
}
