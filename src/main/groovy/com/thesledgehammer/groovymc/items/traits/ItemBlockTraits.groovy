/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.items.traits


import com.thesledgehammer.groovymc.items.IBlockMeta
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

trait ItemBlockTraits {

    private ItemBlock itemBlock;
    private Block block;

    void setItemBlock(ItemBlock itemBlock) {
        this.itemBlock = itemBlock;
    }

    ItemBlock getItemBlockFromItemBlockTrait() {
        return itemBlock;
    }

    void setBlock(Block block) {
        this.block = block;
    }

    Block getBlockFromItemBlockTrait() {
        return block;
    }

    int getMetadataFromItemBlockTrait(int i) {
        return i;
    }

    String getUnlocalizedNameFromItemBlockTrait(ItemStack itemstack) {
        Block block = getBlockFromItemBlockTrait();
        if (block instanceof IBlockMeta) {
            IBlockMeta blockMeta = (IBlockMeta) block;
            int meta = itemstack.getMetadata();
            return block.getUnlocalizedName() + "." + blockMeta.getNameFromMeta(meta);
        }
        return block.getUnlocalizedName();
    }
}