package com.thesledgehammer.groovymc.compat.modules.theoneprobe


import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.energy.IEnergyStorage

class EnergyTools {

    static long getMjStored(TileEntity te) {
        IMjStorage handler = (IMjStorage) te;
        return handler.getStored();
    }

    static long getMjCapacity(TileEntity te) {
        IMjStorage handler = (IMjStorage) te;
        return handler.getCapacity();
    }

    static boolean isMjEnergyHandler(TileEntity te) {
        return te instanceof IMjStorage;
    }

    static int getFeStored(TileEntity te) {
        IEnergyStorage handler = (IEnergyStorage) te;
        return handler.getEnergyStored();
    }

    static int getFeCapacity(TileEntity te) {
        IEnergyStorage handler = (IEnergyStorage) te;
        return handler.getMaxEnergyStored();
    }

    static boolean isFeEnergyHandler(TileEntity te) {
        return te instanceof IEnergyStorage;
    }
}
