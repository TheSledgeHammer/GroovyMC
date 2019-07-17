package com.thesledgehammer.groovymc


import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.IStringSerializable

enum EnumTileType implements IStringSerializable {
    TILE1(TileEntityTest1.class, "test");

    private TileEntityType<? extends TileEntity> tileEntityType
    private TileEntityType.Builder<? extends TileEntity> builder;

    EnumTileType(Class<TileEntityType<? extends TileEntity>> tileEntityType, String name) {
        this.tileEntityType = tileEntityType.newInstance();
        tileEntityType.setRegistryName(name);
    }

    @Override
    String getName() {
        return null
    }

    TileEntityType<? extends TileEntity> getTileEntityType() {
        return tileEntityType;
    }
}