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
package com.thesledgehammer.groovymc.integration.minecraftjoules

class MinecraftJoules extends MinecraftJoulesStorage {

    MinecraftJoules(long capacity) {
        super(capacity);
    }

    MinecraftJoules(long capacity, long maxTransfer) {
        super(capacity, maxTransfer);
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract, long mjEnergy) {
        super(capacity, maxReceive, maxExtract, mjEnergy);
    }
}