<<<<<<< HEAD
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
package com.thesledgehammer.groovymc.api

<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/api/INBTCompound.groovy
import net.minecraft.nbt.NBTTagCompound

interface INBTCompound {

    NBTTagCompound writeToNBT(NBTTagCompound tag)

    void readFromNBT(NBTTagCompound tag);
=======
import net.minecraft.util.IStringSerializable

enum EnumVoltage implements IStringSerializable {

    LOW(32L),
    MEDIUM(128L),
    HIGH(512L),
    ULTRA(2048L),
    SUPER(8192L),
    EXTREME(32768L),
    INSANE(131072L),
    INFINITE(Long.MAX_VALUE);

    static final EnumVoltage[] VALUES = values();
    private final String name;
    private final long voltage;

    EnumVoltage(long voltage) {
        this.name = toString().toLowerCase(Locale.ENGLISH);
        this.voltage = voltage;
    }

    long getVoltage() {
        return voltage;
    }

    @Override
    String getString() {
        return name
    }
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/api/EnumVoltage.groovy
=======
package com.thesledgehammer.groovymc.api

import net.minecraft.nbt.CompoundNBT

interface INBTCompound {

    void read(CompoundNBT tag);

    CompoundNBT write(CompoundNBT tag);
>>>>>>> 1.16.x
}