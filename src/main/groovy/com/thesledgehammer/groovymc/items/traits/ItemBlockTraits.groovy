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

    String getTranslationKeyFromItemBlockTrait(ItemStack itemstack) {
        Block block = getBlockFromItemBlockTrait();
        if (block instanceof IBlockMeta) {
            IBlockMeta blockMeta = (IBlockMeta) block;
            int meta = itemstack.getMetadata();
            return block.getTranslationKey() + "." + blockMeta.getNameFromMeta(meta);
        }
        return block.getTranslationKey();
    }
}