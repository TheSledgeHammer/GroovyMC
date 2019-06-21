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
package com.thesledgehammer.groovymc.experimental.integration.modules.tesla

import com.thesledgehammer.groovymc.api.integration.BlankModule
import net.darkhax.tesla.capability.TeslaCapabilities
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class TeslaModule extends BlankModule {

    private static TeslaModule instance;

    TeslaModule() {
        super("tesla");
        instance = this;
    }

    static TeslaModule Instance() {
        if(instance != null) {
            return instance;
        }
        return null;
    }

    @Override
    void init() {

    }

    static boolean isLoaded() {
        return TeslaCapabilities.CAPABILITY_CONSUMER != null && TeslaCapabilities.CAPABILITY_PRODUCER != null && TeslaCapabilities.CAPABILITY_HOLDER != null;
    }

    static boolean hasTeslaCapability(Capability<?> capability) {
        if(!isLoaded()) {
            return false;
        }
        if(capability == TeslaCapabilities.CAPABILITY_CONSUMER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_PRODUCER) {
            return true;
        }
        if(capability == TeslaCapabilities.CAPABILITY_HOLDER) {
            return true;
        }
        return false;
    }
}
