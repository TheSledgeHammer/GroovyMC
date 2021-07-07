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

<<<<<<< HEAD
import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolderManager
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class GroovyBlock extends Block implements IInitModel {
=======
import net.minecraft.block.Block
import net.minecraft.block.material.Material

class GroovyBlock extends Block {
>>>>>>> 1.16.x

    GroovyBlock(Properties properties) {
        super(properties.hardnessAndResistance(1.5F));
    }

<<<<<<< HEAD
    @Override
    void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
        if (world.isRemote) {
            return;
        }
    }

    @Override
    void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbour) {
        super.onNeighborChange(world, pos, neighbour);
    }

    @Override
    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelEntryHolderManager.Instance().initModel(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
=======
    GroovyBlock(Material material) {
        super(Properties.create(material).hardnessAndResistance(1.5F));
>>>>>>> 1.16.x
    }
}
