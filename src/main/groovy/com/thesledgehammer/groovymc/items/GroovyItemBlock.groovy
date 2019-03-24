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

import com.thesledgehammer.groovymc.items.traits.ItemBlockTraits
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class GroovyItemBlock<B extends Block> extends ItemBlock implements ItemBlockTraits {


    GroovyItemBlock(Block block, Properties builder) {
        super(block, builder);
        setBlock(block);
        setItemBlock(this);
        builder.defaultMaxDamage(0);
        //setHasSubtypes(true);
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
