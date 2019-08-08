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

package com.thesledgehammer.groovymc.init

import com.thesledgehammer.groovymc.api.modules.BlankCompatModule
import com.thesledgehammer.groovymc.compat.modules.buildcraft.BuildcraftModule
import com.thesledgehammer.groovymc.compat.modules.theoneprobe.TheOneProbeCompatibilityModule
import com.thesledgehammer.groovymc.utils.Log
import net.minecraftforge.fml.common.Loader

class CompatModuleContainer {

    private static List<BlankCompatModule> CONTAINER = new LinkedList<>();

    static void preInit() {
        registerModules();
        for(BlankCompatModule module : CONTAINER) {
            if (isRegistered(module)) {
                module.init();
                Log.logInfo("${module.getModID()} has been loaded")
            }
        }
    }

    static List<BlankCompatModule> MODULES_CONTAINER() {
        return CONTAINER;
    }

    private static boolean isRegistered(BlankCompatModule module) {
        if(module != null) {
            if(Loader.isModLoaded(module.getModID())) {
                return true;
            }
        }
        return false;
    }

    private static void registerModules() {
        BuildcraftModule BC = new BuildcraftModule();
        TheOneProbeCompatibilityModule TOP = new TheOneProbeCompatibilityModule();
    }
}
