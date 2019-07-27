package com.thesledgehammer.groovymc.integration.modules.theoneprobe

import buildcraft.api.mj.IMjReadable
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import com.thesledgehammer.groovymc.api.minecraftjoules.MjTools
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.energy.IEnergyStorage

class EnergyTools {

    static long getMjStored(TileEntity te) {
        if(te instanceof IMjReadable) {
            IMjReadable handler = (IMjReadable) te;
            return MjTools.formatMj(handler.getStored());
        } else if(te instanceof IMjStorage) {
            IMjStorage storage = (IMjStorage) te;
            return MjTools.formatMj(storage.getStored());
        }
        return 0;
    }

    static long getMjCapacity(TileEntity te) {
        if(te instanceof IMjReadable) {
            IMjReadable handler = (IMjReadable) te;
            return MjTools.formatMj(handler.getCapacity());
        } else if(te instanceof IMjStorage) {
            IMjStorage storage = (IMjStorage) te;
            return MjTools.formatMj(storage.getCapacity());
        }
        return 0;
    }

    static boolean isMjEnergyHandler(TileEntity te) {
        if(te instanceof IMjReadable) {
            return te instanceof IMjReadable;
        } else if(te instanceof IMjStorage) {
            return te instanceof IMjStorage;
        }
        return false;
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
