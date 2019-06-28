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
package com.thesledgehammer.groovymc.integration.modules.theoneprobe

import com.google.common.base.Function
import com.thesledgehammer.groovymc.api.integration.BlankModule
import com.thesledgehammer.groovymc.utils.Log
import mcjty.theoneprobe.TheOneProbe
import mcjty.theoneprobe.api.*
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
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
    void init() {
        Log.log(Level.INFO, "Enabled support for The One Probe");
        TheOneProbe.theOneProbeImp.registerProvider(new EnergyProbeInfoProvider());
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
                if(blockState.getBlock() instanceof ITheOneProbeInfoProvider) {
                    ITheOneProbeInfoProvider blockProvider = (ITheOneProbeInfoProvider) blockState.getBlock();
                    blockProvider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                }
            }
        });
        return null;
    }
}
