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
package com.thesledgehammer.groovymc.integration

import com.thesledgehammer.groovymc.api.integration.BlankModule
import com.thesledgehammer.groovymc.integration.modules.buildcraft.BuildcraftModule
import com.thesledgehammer.groovymc.integration.modules.industrialcraft.IndustrialcraftModule
import com.thesledgehammer.groovymc.integration.modules.tesla.TeslaModule
import com.thesledgehammer.groovymc.integration.modules.theoneprobe.TheOneProbeCompatibilityModule
import com.thesledgehammer.groovymc.utils.Log
import mcjty.theoneprobe.TheOneProbe
import net.minecraftforge.fml.common.Loader

class ModuleContainer {

    private static List<BlankModule> CONTAINER = new LinkedList<>();

    static void init() {
        for(BlankModule module : CONTAINER) {
            if (Loader.isModLoaded(module.getModID())) {
                module.init();
                Log.logInfo("${module.getModID()} has been loaded")
            }
        }
    }

    static List<BlankModule> MODULES_CONTAINER() {
        return CONTAINER;
    }

    private static void preRegisteredModules() {
        BuildcraftModule BC = new BuildcraftModule();
        IndustrialcraftModule IC2 = new IndustrialcraftModule();
        TeslaModule TESLA = new TeslaModule();
        TheOneProbeCompatibilityModule TOP = new TheOneProbeCompatibilityModule(TheOneProbe.theOneProbeImp);
    }
}
