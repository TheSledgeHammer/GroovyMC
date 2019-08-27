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

package com.thesledgehammer.groovymc.items

import com.thesledgehammer.groovymc.blocks.IBlockMeta
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class GroovyItemBlock<B extends Block> extends ItemBlock {

    GroovyItemBlock(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    B getBlock() {
        return (B) super.getBlock();
    }

    @Override
    int getMetadata(int i) {
        return i;
    }

    @Override
    String getUnlocalizedName(ItemStack itemstack) {
        Block block = getBlock();
        if (block instanceof IBlockMeta) {
            IBlockMeta blockMeta = (IBlockMeta) block;
            int meta = itemstack.getMetadata();
            return block.getUnlocalizedName() + "." + blockMeta.getNameFromMeta(meta);
        }
        return block.getUnlocalizedName();
    }
}
