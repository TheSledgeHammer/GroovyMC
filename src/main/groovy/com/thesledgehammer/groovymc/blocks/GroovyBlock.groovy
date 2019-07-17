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
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

/**
 * To Note: Must use full reference: "Block.Properties". Causes Game Crash (Throws null or constructor invalid).
 */

class GroovyBlock extends Block implements IInitModel {

    GroovyBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(1.5F)
        );
    }

    GroovyBlock(Material material) {
        super(Block.Properties.create(material)
                .hardnessAndResistance(1.5F)
        );
    }

    GroovyBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (world.isRemote) {
            return;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void initModel() {
        //ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
