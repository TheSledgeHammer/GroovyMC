package com.thesledgehammer.groovymc.api.modules

import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.client.event.ModelBakeEvent

interface IEventBusModule {

    void onModelBake(ModelBakeEvent event);

    void registerTextureAtlasSprites(TextureMap textureMap);
}