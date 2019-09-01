/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thesledgehammer.groovymc.compat.modules.theoneprobe

import com.thesledgehammer.groovymc.GroovyMC
import mcjty.theoneprobe.api.*
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import java.awt.*

class MjEnergyProbeInfoProvider implements IProbeInfoProvider {

    MjEnergyProbeInfoProvider(ITheOneProbe theOneProbe) {
        theOneProbe.registerProvider(this);
    }

    @Override
    String getID() {
        return GroovyMC.MOD_ID + ":MjEnergyProbeInfo";
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
    }

    private static void addMJInfo(IProbeInfo probeInfo, long energy, long capacity) {
        probeInfo.progress(energy, capacity, probeInfo.defaultProgressStyle()
                .suffix("MJ")
                .filledColor(new Color(0x00CD66).getRGB())
                .alternateFilledColor(new Color(0x009A33).getRGB())
                .borderColor(new Color(0xff555555).getRGB())
                .backgroundColor(new Color(0x000000).getRGB())
                .numberFormat(NumberFormat.COMPACT)
        );
    }
/*
    private static void addFEInfo(IProbeInfo probeInfo, int energy, int capacity) {
        probeInfo.progress(energy, capacity, probeInfo.defaultProgressStyle()
                .suffix("FE")
                .filledColor(new Color(0xEE2C2C).getRGB())
                .alternateFilledColor(new Color(0xff4300).getRGB())
                .borderColor(new Color(0xff555555).getRGB())
                .backgroundColor(new Color(0x000000).getRGB())
                .numberFormat(NumberFormat.COMPACT)
        );
    }*/
}
