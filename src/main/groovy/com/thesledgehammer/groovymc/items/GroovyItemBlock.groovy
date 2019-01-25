/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.items

import com.thesledgehammer.groovymc.items.traits.ItemBlockTraits
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class GroovyItemBlock<B extends Block> extends ItemBlock implements ItemBlockTraits {

    GroovyItemBlock(Block block) {
        super(block);
        setBlock(block);
        setItemBlock(this);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    B getBlock() {
        return (B) getBlockFromItemBlockTrait();
    }

    @Override
    int getMetadata(int i) {
        return getMetadataFromItemBlockTrait(i);
    }

    @Override
    String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedNameFromItemBlockTrait(itemstack);
    }
}
