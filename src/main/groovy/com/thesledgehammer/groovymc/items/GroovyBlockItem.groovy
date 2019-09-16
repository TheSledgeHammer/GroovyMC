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

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack

class GroovyBlockItem<B extends Block> extends BlockItem {

    GroovyBlockItem(B block, Item.Properties properties) {
        super(block, properties);
    }

    GroovyBlockItem(B block, Item.Properties properties, ItemGroup itemGroup) {
        super(block, properties.group(itemGroup));
    }

    @Override
    String getTranslationKey(ItemStack itemstack) {
        Block block = this.block;
        if (block instanceof IBlockMeta) {
            IBlockMeta blockMeta = (IBlockMeta) block;
            //int meta = itemstack.getItem()
            return block.getTranslationKey();// + "." + blockMeta.getNameFromMeta(meta);
        }
        return block.getTranslationKey();
    }
}
