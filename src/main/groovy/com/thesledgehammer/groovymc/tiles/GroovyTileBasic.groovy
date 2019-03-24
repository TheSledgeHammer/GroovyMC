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

package com.thesledgehammer.groovymc.tiles

import com.thesledgehammer.groovymc.tiles.traits.TileTraits
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType

import javax.annotation.Nullable

abstract class GroovyTileBasic extends TileEntity implements TileTraits {


    GroovyTileBasic(TileEntityType<?> tileEntityType) {
        super(tileEntityType)
    }

    @Override
    NBTTagCompound getUpdateTag() {
        NBTTagCompound updateTag = super.getUpdateTag();
        write(updateTag);
        return updateTag;
    }

    @Override
    @Nullable
    SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        write(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    boolean isRedstoneActivated() {
        return world.isBlockPowered(getPos());
    }

    void onRemoval() {

    }
}
