/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: ItemEnergyContainer
 */
package com.thesledgehammer.groovymc.compat.forgeenergy

import com.thesledgehammer.groovymc.api.forgeenergy.IForgeEnergyItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT

class ForgeEnergyItemContainer extends Item implements IForgeEnergyItem {

    protected int maxCapacity;
    protected int maxExtract;
    protected int maxReceive;

    ForgeEnergyItemContainer(Item.Properties properties) {
        super(properties);
    }

    ForgeEnergyItemContainer(Item.Properties properties, int maxCapacity) {
        this(properties, maxCapacity, maxCapacity, maxCapacity);
    }

    ForgeEnergyItemContainer(Item.Properties properties, int maxCapacity, int maxTransfer) {
        this(properties, maxCapacity, maxTransfer, maxTransfer);
    }

    ForgeEnergyItemContainer(Item.Properties properties, int maxCapacity, int maxReceive, int maxExtract) {
        super(properties);
        setCapacity(maxCapacity);
        setMaxReceive(maxReceive);
        setMaxExtract(maxExtract);
    }

    void setCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    @Override
    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (!container.hasTag()) {
            container.setTag(new CompoundNBT());
        }
        int energy = Math.min(container.getTag().getInt("rfEnergy"), getMaxEnergyStored(container));
        int receiver = Math.min(maxCapacity - energy, Math.min(this.maxReceive, maxReceive));
        if(!simulate) {
            energy += receiver;
            container.getTag().putInt("rfEnergy", energy);
        }
        return receiver;
    }

    @Override
    int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.getTag() == null || !container.getTag().hasUniqueId("rfEnergy")) {
            return 0;
        }
        int energy = Math.min(container.getTag().getInt("rfEnergy"), getMaxEnergyStored(container));
        int extractor = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if(!simulate) {
            energy += extractor;
            container.getTag().putInt("rfEnergy", energy);
        }
        return extractor;
    }

    @Override
    int getEnergyStored(ItemStack container) {
        if (container.getTag() == null || !container.getTag().hasUniqueId("rfEnergy")) {
            return 0;
        }
        return Math.min(container.getTag().getInt("rfEnergy"), getMaxEnergyStored(container));
    }

    @Override
    int getMaxEnergyStored(ItemStack container) {
        return maxCapacity;
    }
}
