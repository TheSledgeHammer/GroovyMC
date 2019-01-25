/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.tiles.traits


import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

trait TileTraits {

    boolean isGroovyTile(TileEntity other, EnumFacing face) {
        if(other.getPos().offset(face) instanceof GroovyTileBasic) {
            return true;
        }
        return false;
    }

    boolean isTileEntity(TileEntity other, EnumFacing face) {
        if(other.getPos().offset(face) instanceof TileEntity) {
            return true;
        }
        return false;
    }

    TileEntity getNeighbouringTileEntity(TileEntity other, EnumFacing face) {
        if(isGroovyTile(other, face) || isTileEntity(other, face)){
            return other;
        }
        return null;
    }
}