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

import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorageItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class MinecraftJouleItemContainer extends Item implements IMjStorageItem {

    protected long maxCapacity;
    protected long maxExtract;
    protected long maxReceive;

    MinecraftJouleItemContainer() {

    }

    MinecraftJouleItemContainer(long maxCapacity) {
        this(maxCapacity, maxCapacity, maxCapacity);
    }

    MinecraftJouleItemContainer(long maxCapacity, long maxTransfer) {
        this(maxCapacity, maxTransfer, maxTransfer);
    }

    MinecraftJouleItemContainer(long maxCapacity, long maxReceive, long maxExtract) {
        setCapacity(maxCapacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
    }

    void setCapacity(long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    void setMaxReceive(long maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
    }

    @Override
    long extractPower(ItemStack container, long min, long max, boolean simulate) {
        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("mjEnergy")) {
            return 0;
        }
        long power = Math.min(container.getTagCompound().getLong("mjEnergy"), getCapacity(container));
        long extractor = Math.min(power, Math.min(this.maxExtract, max - min));
        if(!simulate) {
            power += extractor;
            container.getTagCompound().setLong("mjEnergy", power);
        }
        return extractor;
    }

    @Override
    long receivePower(ItemStack container, long microJoules, boolean simulate) {
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        long power = Math.min(container.getTagCompound().getLong("mjEnergy"), getCapacity(container));
        long receiver = Math.min(maxCapacity - power, Math.min(this.maxReceive, microJoules));
        if(!simulate) {
            power += receiver;
            container.getTagCompound().setLong("mjEnergy", power);
        }
        return receiver;
    }

    @Override
    long getStored(ItemStack container) {
        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("mjEnergy")) {
            return 0;
        }
        return Math.min(container.getTagCompound().getLong("mjEnergy"), getCapacity(container));
    }

    @Override
    long getCapacity(ItemStack container) {
        return maxCapacity;
    }
}
