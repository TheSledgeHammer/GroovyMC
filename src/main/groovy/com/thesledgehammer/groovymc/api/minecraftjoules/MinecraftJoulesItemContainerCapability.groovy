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
package com.thesledgehammer.groovymc.api.minecraftjoules

<<<<<<< HEAD
import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJouleItemContainer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
=======
<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/blocks/GroovyBlockTileMeta.groovy
package com.thesledgehammer.groovymc.blocks

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

abstract class GroovyBlockTileMeta extends GroovyBlockTileBasic implements IBlockMeta {

    GroovyBlockTileMeta(Material blockMaterialIn) {
        super(blockMaterialIn);
    }

    GroovyBlockTileMeta() {
        super(Material.IRON);
    }

    @Override
    void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for(IBlockState blockState : getBlockState().getValidStates()) {
            int meta = getMetaFromState(blockState);
            list.add(new ItemStack(this, 1, meta));
        }
=======
import com.thesledgehammer.groovymc.compat.minecraftjoules.MinecraftJouleItemContainer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
>>>>>>> 1.16.x
import net.minecraftforge.common.capabilities.ICapabilityProvider

class MinecraftJoulesItemContainerCapability extends MinecraftJouleItemContainer {

<<<<<<< HEAD
    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new MinecraftJoulesItemWrapper(stack, this);
=======
    MinecraftJoulesItemContainerCapability(Item.Properties properties) {
        super(properties)
    }

    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new MinecraftJoulesItemWrapper(stack, this);
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/api/minecraftjoules/MinecraftJoulesItemContainerCapability.groovy
>>>>>>> 1.16.x
    }
}
