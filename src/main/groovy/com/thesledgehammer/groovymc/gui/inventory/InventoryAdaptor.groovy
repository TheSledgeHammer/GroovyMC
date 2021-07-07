/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Added INBTSerializable & Converted to .groovy
 *********************************************************************************/

package com.thesledgehammer.groovymc.gui.inventory

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction

class InventoryAdaptor implements IInventoryAdaptor {

    InventoryAdaptor() {

    }

    @Override
    int[] getSlotsForFace(Direction side) {
        return new int[0]
    }

    @Override
    boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return false
    }

    @Override
    boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return false
    }

    @Override
    int getSizeInventory() {
        return 0
    }

    @Override
    boolean isEmpty() {
        return true;
    }

    @Override
    ItemStack getStackInSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    ItemStack decrStackSize(int index, int count) {
        return ItemStack.EMPTY;
    }

    @Override
    ItemStack removeStackFromSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    int getInventoryStackLimit() {
        return 0
    }

    @Override
    void markDirty() {

    }

    @Override
    boolean isUsableByPlayer(PlayerEntity player) {
        return false
    }

    @Override
    void openInventory(PlayerEntity player) {

    }

    @Override
    void closeInventory(PlayerEntity player) {

    }

    @Override
    boolean isItemValidForSlot(int index, ItemStack stack) {
        return false
    }

    @Override
    void clear() {

    }

    @Override
    CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        return nbt;
    }

    @Override
    void deserializeNBT(CompoundNBT nbt) {
        this.deserializeNBT(nbt);
    }
}
