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

package com.thesledgehammer.groovymc.modules

import com.thesledgehammer.groovymc.api.modules.BlankCompatModule
import com.thesledgehammer.groovymc.utils.Log
<<<<<<< HEAD
import net.minecraftforge.fml.common.Loader
=======
import net.minecraftforge.fml.ModList
>>>>>>> 1.16.x

class CompatModuleContainer {

    private static List<BlankCompatModule> CONTAINER = new LinkedList<>();

    static List<BlankCompatModule> MODULES_CONTAINER() {
        return CONTAINER;
    }

    static void preInit() {
        for(BlankCompatModule module : CONTAINER) {
            if (isRegistered(module)) {
                Log.logInfo("preInit() ${module.getModID()} ${module.getModuleName()}...")
                module.preInit();
                Log.logInfo("${module.getModID()}'s ${module.getModuleName()} has been loaded")
            }
        }
    }

    static void Init() {
        for(BlankCompatModule module : CONTAINER) {
            if (isRegistered(module)) {
                Log.logInfo("Init() ${module.getModID()} ${module.getModuleName()}...")
                module.Init()
                Log.logInfo("${module.getModID()}'s ${module.getModuleName()} has been loaded")
            }
        }
    }

    static void postInit() {
        for(BlankCompatModule module : CONTAINER) {
            if (isRegistered(module)) {
                Log.logInfo("postInit() ${module.getModID()} ${module.getModuleName()}...")
                module.postInit()
                Log.logInfo("${module.getModID()}'s ${module.getModuleName()} has been loaded")
            }
        }
    }

    private static boolean isRegistered(BlankCompatModule module) {
<<<<<<< HEAD
        if(module != null) {
            if(Loader.isModLoaded(module.getModID())) {
=======
        ModList modList = ModList.get();
        if(module != null) {
            if(modList.isLoaded(module.getModID())) {
>>>>>>> 1.16.x
                return true;
            }
        }
        return false;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1.16.x
