package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.IMjReceiver
import buildcraft.api.mj.IMjRedstoneReceiver
import net.minecraftforge.fml.common.Optional

@Optional.InterfaceList(
        value = [
            @Optional.Interface(iface = "buildcraft.api.mj.IMjConnector", modid = "buildcraft", striprefs = true),
            @Optional.Interface(iface = "buildcraft.api.mj.IMjReceiver", modid = "buildcraft", striprefs = true),
            @Optional.Interface(iface = "buildcraft.api.mj.IMjPassiveProvider", modid = "buildcraft", striprefs = true),
            @Optional.Interface(iface = "buildcraft.api.mj.IMjReadable", modid = "buildcraft", striprefs = true),
            @Optional.Interface(iface = "buildcraft.api.mj.IMjRedstoneReceiver", modid = "buildcraft", striprefs = true)
        ]
)
interface IMinecraftJoulesStorage extends IMjConnector, IMjReceiver, IMjPassiveProvider, IMjReadable, IMjRedstoneReceiver {

    boolean canExtract();
}