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

package com.thesledgehammer.groovymc.tiles.traits

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction

trait TileTraits {

    boolean isGroovyTile(TileEntity other, Direction face) {
        if(other.getPos().offset(face) instanceof GroovyTileBasic) {
            return true;
        }
        return false;
    }

    boolean isTileEntity(TileEntity other, Direction face) {
        if(other.getPos().offset(face) instanceof TileEntity) {
            return true;
        }
        return false;
    }

    TileEntity getNeighbouringTileEntity(TileEntity other, Direction face) {
        if(isGroovyTile(other, face) || isTileEntity(other, face)){
            return other;
        }
        return null;
    }
}