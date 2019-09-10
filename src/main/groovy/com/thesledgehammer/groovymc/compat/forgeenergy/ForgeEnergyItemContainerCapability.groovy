/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: From ItemEnergyContainerCap
 */
package com.thesledgehammer.groovymc.compat.forgeenergy

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.capabilities.ICapabilityProvider

class ForgeEnergyItemContainerCapability extends ForgeEnergyItemContainer {

    @Override
    ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new ForgeEnergyItemWrapper(stack, this);
    }
}
