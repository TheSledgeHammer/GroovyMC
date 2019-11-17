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
/*
package com.thesledgehammer.groovymc.compat.modules.theoneprobe

import com.google.common.base.Function
import com.thesledgehammer.groovymc.GroovyMC
import com.thesledgehammer.groovymc.api.modules.BlankCompatModule
import com.thesledgehammer.groovymc.utils.Log
import mcjty.theoneprobe.api.ITheOneProbe
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
        //FMLInterModComms.sendFunctionMessage("theoneprobe", "TheOneProbeCompatibilityModule", "com.thesledgehammer.groovymc.integration.modules.theoneprobe.$TheOneProbeCompatibilityModule");
    }

    @Nullable
    @Override
    Void apply(ITheOneProbe theOneProbe) {
        this.theOneProbe = theOneProbe;
        theOneProbe.registerProvider(new MjEnergyProbeInfoProvider());
        return null;
    }
}
*/