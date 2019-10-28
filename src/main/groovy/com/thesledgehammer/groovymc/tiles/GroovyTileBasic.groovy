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


import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.Direction

abstract class GroovyTileBasic extends TileEntity {

    GroovyTileBasic(TileEntityType tileEntityTypeIn) {
        super(tileEntityTypeIn)
    }
/*
    @Override
    CompoundNBT getUpdateTag() {
        CompoundNBT updateTag = super.getUpdateTag();
        write(updateTag);
        return updateTag;
    }

    @Override
    @Nullable
    SUpdateTileEntityPacket  getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        write(nbtTag);
        return new SUpdateTileEntityPacket(getPos(), 1, nbtTag);
    }
    */

    boolean isRedstoneActivated() {
        return world.getRedstonePowerFromNeighbors(getPos()) > 0;
    }

    protected boolean isSidePowered(Direction face) {
        return world.isSidePowered(getPos(), face);
    }

    void onRemoval() {

    }

    boolean isTileEntity(TileEntity other, Direction face) {
        if(other.getPos().offset(face) instanceof TileEntity) {
            return true;
        }
        return false;
    }

    TileEntity getNeighbouringTileEntity(TileEntity other, Direction face) {
        if(isTileEntity(other, face)){
            return other;
        }
        return null;
    }
}