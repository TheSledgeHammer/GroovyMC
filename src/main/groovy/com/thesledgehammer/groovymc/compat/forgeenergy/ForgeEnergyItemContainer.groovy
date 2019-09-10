/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: ItemEnergyContainer
 */
package com.thesledgehammer.groovymc.compat.forgeenergy

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class ForgeEnergyItemContainer extends Item implements IForgeEnergyItem {

    protected int maxCapacity;
    protected int maxExtract;
    protected int maxReceive;

    ForgeEnergyItemContainer() {

    }

    ForgeEnergyItemContainer(int maxCapacity) {
        this(maxCapacity, maxCapacity, maxCapacity);
    }

    ForgeEnergyItemContainer(int maxCapacity, int maxTransfer) {
        this(maxCapacity, maxTransfer, maxTransfer);
    }

    ForgeEnergyItemContainer(int maxCapacity, int maxReceive, int maxExtract) {
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
        if (!container.hasTagCompound()) {
            container.setTagCompound(new NBTTagCompound());
        }
        int energy = Math.min(container.getTagCompound().getInteger("rfEnergy"), getMaxEnergyStored(container));
        int receiver = Math.min(maxCapacity - energy, Math.min(this.maxReceive, maxReceive));
        if(!simulate) {
            energy += receiver;
            container.getTagCompound().setInteger("rfEnergy", energy);
        }
        return receiver;
    }

    @Override
    int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("rfEnergy")) {
            return 0;
        }
        int energy = Math.min(container.getTagCompound().getInteger("rfEnergy"), getMaxEnergyStored(container));
        int extractor = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if(!simulate) {
            energy += extractor;
            container.getTagCompound().setInteger("rfEnergy", energy);
        }
        return extractor;
    }

    @Override
    int getEnergyStored(ItemStack container) {
        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("rfEnergy")) {
            return 0;
        }
        return Math.min(container.getTagCompound().getInteger("rfEnergy"), getMaxEnergyStored(container));
    }

    @Override
    int getMaxEnergyStored(ItemStack container) {
        return maxCapacity;
    }
}
