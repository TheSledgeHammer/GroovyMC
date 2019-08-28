package com.thesledgehammer.groovymc.utils

import com.thesledgehammer.groovymc.api.GroovyLoader
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

class TileEntityTools {

    static void registerTileEntity(Class<? extends TileEntity> tileEntity, String modID, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(modID, tileName));
    }

    static void registerTileEntity(Class<? extends TileEntity> tileEntity, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(GroovyLoader.Instance().getModID(), tileName));
    }
}
