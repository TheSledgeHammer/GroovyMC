package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

import buildcraft.api.mj.IMjConnector
import buildcraft.api.mj.MjAPI
import com.thesledgehammer.groovymc.items.GroovyItem
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

import javax.annotation.Nonnull
import javax.annotation.Nullable

class MinecraftJoulesItem extends GroovyItem implements IMinecraftJoulesStorage, ICapabilityProvider {

    private ItemStack container;
    protected MinecraftJoules MJ;

    MinecraftJoulesItem(ItemStack container, long capacity, long maxTransfer) {
        setItemContainer(container);
        MJ = new MinecraftJoules(capacity, maxTransfer);
    }

    MinecraftJoulesItem(long capacity, long maxTransfer) {
        setItemContainer(container);
        MJ = new MinecraftJoules(capacity, maxTransfer);
    }

    private void setItemContainer(ItemStack container) {
        this.container = container;
    }

    @Override
    boolean canExtract() {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.canExtract();
        }
        return false
    }

    @Override
    long extractPower(long min, long max, boolean simulate) {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.extractPower(min, max, simulate);
        }
        return 0
    }

    @Override
    long getStored() {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.getStored();
        }
        return 0
    }

    @Override
    long getCapacity() {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.getCapacity();
        }
        return 0
    }

    @Override
    long getPowerRequested() {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.getPowerRequested();
        }
        return 0
    }

    @Override
    long receivePower(long microJoules, boolean simulate) {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.receivePower(microJoules, simulate);
        }
        return 0
    }

    @Override
    boolean canReceive() {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.canReceive();
        }
        return false;
    }

    @Override
    boolean canConnect(@Nonnull IMjConnector other) {
        if(container instanceof IMinecraftJoulesStorage) {
            IMinecraftJoulesStorage mjItem = container as IMinecraftJoulesStorage;
            return mjItem.canConnect(other);
        }
        return false
    }

    @Override
    boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if(capability == MjAPI.CAP_CONNECTOR) {
            return true;
        }
        if(capability == MjAPI.CAP_RECEIVER) {
            return true;
        }
        if(capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return true;
        }
        if(capability == MjAPI.CAP_READABLE) {
            return true;
        }
        if(capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return true;
        }
        return false;
    }

    @Override
    <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == MjAPI.CAP_CONNECTOR) {
            return MjAPI.CAP_CONNECTOR.cast(this);
        }
        if(capability == MjAPI.CAP_RECEIVER) {
            return MjAPI.CAP_RECEIVER.cast(this);
        }
        if(capability == MjAPI.CAP_PASSIVE_PROVIDER) {
            return MjAPI.CAP_PASSIVE_PROVIDER.cast(this);
        }
        if(capability == MjAPI.CAP_READABLE) {
            return MjAPI.CAP_READABLE.cast(this);
        }
        if(capability == MjAPI.CAP_REDSTONE_RECEIVER) {
            return MjAPI.CAP_REDSTONE_RECEIVER.cast(this);
        }
        return null;
    }
}
