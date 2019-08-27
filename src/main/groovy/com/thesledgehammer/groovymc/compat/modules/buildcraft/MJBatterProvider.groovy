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
package com.thesledgehammer.groovymc.compat.modules.buildcraft

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.IMjPassiveProvider
import buildcraft.api.mj.IMjReadable
import buildcraft.api.mj.MjBattery
import net.minecraftforge.fml.common.Optional

import javax.annotation.Nonnull

@Optional.InterfaceList(
        value = [
                @Optional.Interface(iface = "buildcraft.api.mj.IMjPassiveProvider", modid = "buildcraft"),
                @Optional.Interface(iface = "buildcraft.api.mj.IMjReadable", modid = "buildcraft")
        ]
)
class MJBatterProvider implements IMjPassiveProvider, IMjReadable {

    private final MjBattery battery;

    MJBatterProvider(MjBattery battery) {
        this.battery = battery;
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        return battery.extractPower(min, max);
    }

    @Override
    long getStored() {
        return battery.getStored();
    }

    @Override
    long getCapacity() {
        return battery.getCapacity();
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        return true
    }
}
