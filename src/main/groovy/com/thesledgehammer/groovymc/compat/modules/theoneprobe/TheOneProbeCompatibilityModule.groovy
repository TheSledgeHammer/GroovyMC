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
<<<<<<< HEAD
=======
/*
>>>>>>> 1.16.x
package com.thesledgehammer.groovymc.compat.modules.theoneprobe

import com.google.common.base.Function
import com.thesledgehammer.groovymc.GroovyMC
import com.thesledgehammer.groovymc.api.modules.BlankCompatModule
import com.thesledgehammer.groovymc.utils.Log
import mcjty.theoneprobe.api.ITheOneProbe
<<<<<<< HEAD
import net.minecraftforge.fml.common.event.FMLInterModComms
=======
>>>>>>> 1.16.x
import org.apache.logging.log4j.Level

import javax.annotation.Nullable

class TheOneProbeCompatibilityModule extends BlankCompatModule implements Function<ITheOneProbe, Void> {

    private static boolean registered;
    private ITheOneProbe theOneProbe;

    TheOneProbeCompatibilityModule() {
        super(GroovyMC.MOD_ID, "theoneprobe");
    }

    @Override
    void preInit() {
        TheOneProbeCompatibilityModule TOP = new TheOneProbeCompatibilityModule();
        Log.log(Level.INFO, "Enabled support for The One Probe");
        register();
    }

    @Override
    void Init() {

    }

    @Override
    void postInit() {

    }

    static void register() {
        if(registered) {
            return;
        }
        registered = true;
<<<<<<< HEAD
        FMLInterModComms.sendFunctionMessage("theoneprobe", "TheOneProbeCompatibilityModule", "com.thesledgehammer.groovymc.integration.modules.theoneprobe.$TheOneProbeCompatibilityModule");
=======
        //FMLInterModComms.sendFunctionMessage("theoneprobe", "TheOneProbeCompatibilityModule", "com.thesledgehammer.groovymc.integration.modules.theoneprobe.$TheOneProbeCompatibilityModule");
>>>>>>> 1.16.x
    }

    @Nullable
    @Override
    Void apply(ITheOneProbe theOneProbe) {
        this.theOneProbe = theOneProbe;
        theOneProbe.registerProvider(new MjEnergyProbeInfoProvider());
<<<<<<< HEAD
       /* theOneProbe.registerProvider(new IProbeInfoProvider() {

            @Override
            String getID() {
                return "groovymc:TheOneProbeCompatibilityModule"
            }

            @Override
            void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
                if(blockState.getBlock() instanceof IProbeInfoProvider) {
                    IProbeInfoProvider blockProvider = (IProbeInfoProvider) blockState.getBlock();
                    blockProvider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                }
            }
        });*/
        return null;
    }
}
=======
        return null;
    }
}
*/
>>>>>>> 1.16.x
