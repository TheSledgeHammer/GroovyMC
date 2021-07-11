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
package com.thesledgehammer.groovymc.api.minecraftjoules

import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJouleItemContainer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.common.capabilities.ICapabilityProvider

class MinecraftJoulesItemContainerCapability extends MinecraftJouleItemContainer {

    MinecraftJoulesItemContainerCapability(Properties properties) {
        super(properties)
    }

    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new MinecraftJoulesItemWrapper(stack, this);
    }
}
