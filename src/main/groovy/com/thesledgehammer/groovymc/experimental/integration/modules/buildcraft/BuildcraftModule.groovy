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
package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import buildcraft.api.mj.MjAPI
import com.thesledgehammer.groovymc.api.integration.BlankModule
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class BuildcraftModule extends BlankModule {

    private static BuildcraftModule instance;

    BuildcraftModule() {
        super("buildcraft");
        instance = this;
    }

    static BuildcraftModule Instance() {
        if(instance != null) {
            return instance;
        }
        return null;
    }

    @Override
    void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    void init(FMLInitializationEvent event) {

    }

    @Override
    void postInit(FMLPostInitializationEvent event) {

    }

    static boolean isLoaded() {
        return MjAPI.CAP_CONNECTOR != null && MjAPI.CAP_RECEIVER != null && MjAPI.CAP_PASSIVE_PROVIDER != null && MjAPI.CAP_READABLE != null && MjAPI.CAP_REDSTONE_RECEIVER != null;
    }

    static boolean hasMjCapability(Capability<?> capability) {
        if(!isLoaded()) {
            return false;
        }
        if(capability == MjAPI.CAP_CONNECTOR) {
            return true;
        }
        if(capability == MjAPI.CAP_RECEIVER) {
            return true;
        }
        if(capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return true;
        }
        if(capability == MjAPI.CAP_READABLE) {
            return true;
        }
        if(capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return true;
        }
        return false;
    }
}