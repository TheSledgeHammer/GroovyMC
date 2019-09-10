/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: IEnergyContainerItem
 */
package com.thesledgehammer.groovymc.compat.forgeenergy

import net.minecraft.item.ItemStack

interface IForgeEnergyItem {

    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getEnergyStored(ItemStack container);

    int getMaxEnergyStored(ItemStack container);
}