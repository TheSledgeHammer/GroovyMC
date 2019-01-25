/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.items.traits

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

trait ItemTraits {

    private Item item;

    void setItem(Item item) {
        this.item = item;
    }

    Item getItemFromItemTrait() {
        return item;
    }

    ItemStack getWildcard() {
        return new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE);
    }
}