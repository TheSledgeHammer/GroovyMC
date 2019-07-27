package com.thesledgehammer.groovymc.integration.modules.theoneprobe

import com.thesledgehammer.groovymc.GroovyMC
import mcjty.theoneprobe.api.*
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class EnergyProbeInfoProvider implements IProbeInfoProvider {

    EnergyProbeInfoProvider(ITheOneProbe theOneProbe) {
        theOneProbe.registerProvider(this);
    }

    @Override
    String getID() {
        return GroovyMC.MOD_ID + ":EnergyProbeInfo";
    }

    @Override
    void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        BlockPos pos = data.getPos();
        TileEntity te = world.getTileEntity(pos);
        if(EnergyTools.isMjEnergyHandler(te)) {
            long energy = EnergyTools.getMjStored(te);
            long capacity = EnergyTools.getMjCapacity(te);
            addMJInfo(probeInfo, energy, capacity);
        }
        if(EnergyTools.isFeEnergyHandler(te)) {
            int energy = EnergyTools.getFeStored(te);
            int capacity = EnergyTools.getFeCapacity(te);
            addFEInfo(probeInfo, energy, capacity);
        } else if(te.hasCapability(CapabilityEnergy.ENERGY, null)) {
            IEnergyStorage rf = te.getCapability(CapabilityEnergy.ENERGY, null);
            if(rf != null) {
                addFEInfo(probeInfo, rf.getEnergyStored(), rf.getMaxEnergyStored());
            }
        }
    }

    private static void addMJInfo(IProbeInfo probeInfo, long energy, long capacity) {
        probeInfo.progress(energy, capacity, probeInfo.defaultProgressStyle()
                .suffix("MJ")
                .filledColor(EnumColorType.MJ.getFilledColor())
                .alternateFilledColor(EnumColorType.MJ.getAlternateFilledColor())
                .borderColor(EnumColorType.MJ.getBorderColor())
                .backgroundColor(EnumColorType.MJ.getBackgroundColor())
                .numberFormat(NumberFormat.COMPACT)
        );
    }

    private static void addFEInfo(IProbeInfo probeInfo, int energy, int capacity) {
        probeInfo.progress(energy, capacity, probeInfo.defaultProgressStyle()
                .suffix("FE")
                .filledColor(EnumColorType.FE.getFilledColor())
                .alternateFilledColor(EnumColorType.FE.getAlternateFilledColor())
                .borderColor(EnumColorType.FE.getBorderColor())
                .backgroundColor(EnumColorType.FE.getBackgroundColor())
                .numberFormat(NumberFormat.COMPACT)
        );
    }
}
