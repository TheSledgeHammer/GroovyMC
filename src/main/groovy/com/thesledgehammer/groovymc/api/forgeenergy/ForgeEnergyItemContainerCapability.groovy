/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: From ItemEnergyContainerCap
 */
package com.thesledgehammer.groovymc.api.forgeenergy

import com.thesledgehammer.groovymc.compat.forgeenergy.ForgeEnergyItemContainer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.common.capabilities.ICapabilityProvider

class ForgeEnergyItemContainerCapability extends ForgeEnergyItemContainer {

    ForgeEnergyItemContainerCapability(Item.Properties properties) {
        super(properties);
    }

    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new ForgeEnergyItemWrapper(stack, this);
    }
}
