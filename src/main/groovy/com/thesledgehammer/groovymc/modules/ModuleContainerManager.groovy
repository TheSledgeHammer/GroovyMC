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

<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/api/minecraftjoules/MinecraftJoulesItemContainerCapability.groovy
import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJouleItemContainer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.capabilities.ICapabilityProvider

class MinecraftJoulesItemContainerCapability extends MinecraftJouleItemContainer {

    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new MinecraftJoulesItemWrapper(stack, this);
=======
package com.thesledgehammer.groovymc.modules

import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj

class ModuleContainerManager {

    static void preInit() {
        //CapabilityMj.register();
        CompatModuleContainer.preInit();
        RenderEventModuleContainer.preInit();
    }

    static void Init() {
        CompatModuleContainer.Init();
        RenderEventModuleContainer.Init();
    }

    static void postInit() {
        CompatModuleContainer.postInit();
        RenderEventModuleContainer.postInit();
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/modules/ModuleContainerManager.groovy
    }
}
