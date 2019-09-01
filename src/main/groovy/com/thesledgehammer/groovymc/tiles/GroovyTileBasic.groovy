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

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

import javax.annotation.Nullable

abstract class GroovyTileBasic extends TileEntity {

    @Override
    NBTTagCompound getUpdateTag() {
        NBTTagCompound updateTag = super.getUpdateTag();
        writeToNBT(updateTag);
        return updateTag;
    }

    @Override
    @Nullable
    SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    protected boolean isRedstoneActivated() {
        return world.isBlockIndirectlyGettingPowered(getPos()) > 0;
    }

    void onRemoval() {

    }

    boolean isTileEntity(TileEntity other, EnumFacing face) {
        if(other.getPos().offset(face) instanceof TileEntity) {
            return true;
        }
        return false;
    }

    TileEntity getNeighbouringTileEntity(TileEntity other, EnumFacing face) {
        if(isTileEntity(other, face)){
            return other;
        }
        return null;
    }
}
