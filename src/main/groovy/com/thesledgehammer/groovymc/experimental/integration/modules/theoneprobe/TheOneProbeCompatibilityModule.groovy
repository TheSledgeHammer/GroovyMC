package com.thesledgehammer.groovymc.experimental.integration.modules.theoneprobe

import com.google.common.base.Function
import com.thesledgehammer.groovymc.api.integration.BlankModule
import com.thesledgehammer.groovymc.utils.Log
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ITheOneProbe
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.world.World
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Level

import javax.annotation.Nullable

class TheOneProbeCompatibilityModule extends BlankModule implements Function<ITheOneProbe, Void> {

    private static TheOneProbeCompatibilityModule instance;
    private ITheOneProbe theOneProbe;

    TheOneProbeCompatibilityModule(ITheOneProbe theOneProbe) {
        super("theoneprobe");
        this.theOneProbe = theOneProbe;
        instance = this;
    }

    static TheOneProbeCompatibilityModule Instance() {
        if(instance != null) {
            return instance;
        }
        return null;
    }

    @Override
    void preInit(FMLPreInitializationEvent event) {
        Log.log(Level.INFO, "Enabled support for The One Probe");
    }

    @Override
    void init(FMLInitializationEvent event) {

    }

    @Override
    void postInit(FMLPostInitializationEvent event) {

    }

    @Nullable
    @Override
    Void apply(ITheOneProbe theOneProbe) {
        this.theOneProbe = theOneProbe;
        theOneProbe.registerProvider(new IProbeInfoProvider() {

            @Override
            String getID() {
                return "groovymc:TheOneProbeCompatibilityModule"
            }

            @Override
            void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
                if(blockState.getBlock() instanceof TheOneProbeInfoProvider) {
                    TheOneProbeInfoProvider blockProvider = (TheOneProbeInfoProvider) blockState.getBlock();
                    blockProvider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                }
                if(blockState.getBlock().hasTileEntity(blockState)) {
                    TileEntity tileEntity = world.getTileEntity(data.getPos());
                    if(tileEntity != null && tileEntity instanceof TheOneProbeInfoProvider) {
                        TheOneProbeInfoProvider teProvider = (TheOneProbeInfoProvider) tileEntity;
                        teProvider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                    }
                }
            }
        });
        return null;
    }
}
