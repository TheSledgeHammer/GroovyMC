/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018: EnergyContainerItemWrapper
 */
package com.thesledgehammer.groovymc.compat.forgeenergy

import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class ForgeEnergyItemWrapper implements ICapabilityProvider {
    private ItemStack container;
    private IForgeEnergyItem feContainer;

    private final boolean canExtract;
    private final boolean canReceive;
    private final IEnergyStorage feStorage;

    ForgeEnergyItemWrapper(ItemStack container, IForgeEnergyItem feContainer, boolean canReceive, boolean canExtract) {
        this.container = container;
        this.feContainer = feContainer;
        this.canReceive = canReceive;
        this.canExtract = canExtract;

        this.feStorage = new IEnergyStorage() {

            @Override
            int receiveEnergy(int maxReceive, boolean simulate) {
                return feContainer.receiveEnergy(container, maxReceive, simulate);
            }

            @Override
            int extractEnergy(int maxExtract, boolean simulate) {
                return feContainer.extractEnergy(container, maxExtract, simulate);
            }

            @Override
            int getEnergyStored() {
                return feContainer.getEnergyStored(container);
            }

            @Override
            int getMaxEnergyStored() {
                return feContainer.getMaxEnergyStored(container);
            }

            @Override
            boolean canExtract() {
                return canExtract;
            }

            @Override
            boolean canReceive() {
                return canReceive;
            }
        }
    }

    ForgeEnergyItemWrapper(ItemStack container, IForgeEnergyItem feContainer) {
        this(container, feContainer, true, true);
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(feStorage);
        }
        return null;
    }
}
