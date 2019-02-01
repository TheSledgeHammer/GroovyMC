package com.thesledgehammer.groovymc.api

import net.minecraft.tileentity.TileEntity

interface IRegisterTileEntity {

    void registerTileEntity(Class<? extends TileEntity> tileEntity, String tileName);

    void registerTileEntity(Class<? extends TileEntity> tileEntity, String modId, String tileName);
}