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

package com.thesledgehammer.groovymc.compat.modules.theoneprobe

<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/client/render/IRenderEntry.groovy
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import com.thesledgehammer.groovymc.client.model.MutableQuad
=======
import com.thesledgehammer.groovymc.api.minecraftjoules.IMjStorage
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/compat/modules/theoneprobe/EnergyTools.groovy
import net.minecraft.tileentity.TileEntity

<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/client/render/IRenderEntry.groovy
@SideOnly(Side.CLIENT)
interface IRenderEntry<M extends ModelEntryHolder, T extends TileEntity> {

    void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha);

    MutableQuad[] getQuads(M modelEntryHolder, T tile, float partialTicks);
}
=======
class EnergyTools {

    static long getMjStored(TileEntity te) {
        IMjStorage handler = (IMjStorage) te;
        return handler.getStored();
    }

    static long getMjCapacity(TileEntity te) {
        IMjStorage handler = (IMjStorage) te;
        return handler.getCapacity();
    }

    static boolean isMjEnergyHandler(TileEntity te) {
        return te instanceof IMjStorage;
    }
}
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/compat/modules/theoneprobe/EnergyTools.groovy
