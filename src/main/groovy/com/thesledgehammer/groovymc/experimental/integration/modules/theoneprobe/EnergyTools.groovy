package com.thesledgehammer.groovymc.experimental.integration.modules.theoneprobe

import buildcraft.api.mj.IMjReadable
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.energy.IEnergyStorage

class EnergyTools {

    static long getMjStored(TileEntity te) {
        IMjReadable handler = (IMjReadable) te;
        return handler.getStored();
    }

    static long getMjCapacity(TileEntity te) {
        IMjReadable handler = (IMjReadable) te;
        return handler.getCapacity();
    }

    static boolean isMjEnergyHandler(TileEntity te) {
        return te instanceof IMjReadable;
    }

    static long getEuStored(TileEntity te) {
        IMjReadable handler = (IMjReadable) te;
        return handler.getStored();
    }

    static long getEuCapacity(TileEntity te) {
        IMjReadable handler = (IMjReadable) te;
        return handler.getCapacity();
    }

    static boolean isEuEnergyHandler(TileEntity te) {
        return te instanceof IMjReadable;
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
