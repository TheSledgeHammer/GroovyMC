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
<<<<<<< HEAD
package com.thesledgehammer.groovymc.api.minecraftjoules

import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJoulesStorage
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagLong
import net.minecraft.util.EnumFacing
=======

package com.thesledgehammer.groovymc.api.minecraftjoules

import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJoulesStorage
import net.minecraft.nbt.INBT
import net.minecraft.nbt.LongNBT
import net.minecraft.util.Direction
>>>>>>> 1.16.x
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

class CapabilityMj {
<<<<<<< HEAD

=======
>>>>>>> 1.16.x
    @CapabilityInject(IMjStorage.class)
    static Capability<IMjStorage> MJ_STORAGE = null;

    static void register() {
        CapabilityManager.INSTANCE.register(IMjStorage.class, new Capability.IStorage<IMjStorage>() {
<<<<<<< HEAD

            @Override
            NBTBase writeNBT(Capability<IMjStorage> capability, IMjStorage instance, EnumFacing side) {
                return new NBTTagLong(instance.getStored());
            }

            @Override
            void readNBT(Capability<IMjStorage> capability, IMjStorage instance, EnumFacing side, NBTBase nbt) {
                if (!(instance instanceof MinecraftJoulesStorage))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                ((MinecraftJoulesStorage) instance).powerStored = ((NBTTagLong) nbt).getLong();
=======
            @Override
            INBT writeNBT(Capability<IMjStorage> capability, IMjStorage instance, Direction side) {
                return LongNBT.valueOf(instance.getStored());
            }

            @Override
            void readNBT(Capability<IMjStorage> capability, IMjStorage instance, Direction side, INBT nbt) {
                if (!(instance instanceof MinecraftJoulesStorage)) {
                    throw new  IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                }
                ((MinecraftJoulesStorage) instance).mjEnergy = ((LongNBT) nbt).getLong();
>>>>>>> 1.16.x
            }
        }, { -> new MinecraftJoulesStorage(1000 * MjTools.MJ) });
    }
}
