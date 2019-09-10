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
package com.thesledgehammer.groovymc.compat.minecraftjoules

import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

import javax.annotation.Nonnull

class MinecraftJoulesItemWrapper implements ICapabilityProvider {

    private ItemStack container;
    private IMjStorageItem mjContainer;

    private final boolean canExtract;
    private final boolean canReceive;
    private final IMjStorage mjStorage;

    MinecraftJoulesItemWrapper(ItemStack container, IMjStorageItem mjContainer, boolean canReceive, boolean canExtract) {
        this.container = container;
        this.mjContainer = mjContainer;
        this.canReceive = canReceive;
        this.canExtract = canExtract;
        this.mjStorage = new IMjStorage() {
            @Override
            long getPowerRequested() {
                return Math.min(getStored(), getCapacity());
            }

            @Override
            long extractPower(long min, long max, boolean simulate) {
                return mjContainer.extractPower(container, min, max, simulate);
            }

            @Override
            long receivePower(long microJoules, boolean simulate) {
                return mjContainer.receivePower(container, microJoules, simulate);
            }

            @Override
            long getStored() {
                return mjContainer.getStored(container);
            }

            @Override
            long getCapacity() {
                return mjContainer.getCapacity(container);
            }

            @Override
            boolean canExtract() {
                return canExtract;
            }

            @Override
            boolean canReceive() {
                return canReceive;
            }

            @Override
            boolean canConnect(@Nonnull IMjStorage other) {
                return true;
            }
        }
    }

    MinecraftJoulesItemWrapper(ItemStack container, IMjStorageItem mjContainer) {
        this(container, mjContainer, true, true);
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityMj.MJ_STORAGE) {
            return CapabilityMj.MJ_STORAGE.cast(mjStorage);
        }
        return null;
    }
}
