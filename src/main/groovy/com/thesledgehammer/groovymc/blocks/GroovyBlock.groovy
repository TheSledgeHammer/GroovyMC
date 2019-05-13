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

package com.thesledgehammer.groovymc.blocks

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.blocks.traits.BlockTraits
import net.minecraft.block.Block
import net.minecraft.client.renderer.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.ModelLoader

class GroovyBlock extends Block implements BlockTraits, IInitModel {

    GroovyBlock(Properties properties) {
        super(properties);
        properties.hardnessAndResistance(1.5F);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
