/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Added INBTSerializable & Converted to .groovy
 *********************************************************************************/

package com.thesledgehammer.groovymc.gui.inventory

import net.minecraft.inventory.ISidedInventory
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.common.util.INBTSerializable

interface IInventoryAdaptor extends ISidedInventory, INBTSerializable<CompoundNBT> {

}
