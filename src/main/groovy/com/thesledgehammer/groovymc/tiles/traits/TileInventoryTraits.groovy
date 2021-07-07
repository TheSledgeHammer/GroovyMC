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

package com.thesledgehammer.groovymc.tiles.traits

import com.thesledgehammer.groovymc.gui.inventory.IInventoryAdaptor
<<<<<<< HEAD
import net.minecraft.entity.player.EntityPlayer
=======
import net.minecraft.entity.player.PlayerEntity
>>>>>>> 1.16.x
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction

trait TileInventoryTraits implements IInventory, ISidedInventory {

    private TileEntity tileEntity;
    private IInventoryAdaptor inventoryAdapter;

    void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    void setIInventory(IInventoryAdaptor inventoryAdapter) {
        this.inventoryAdapter = inventoryAdapter;
    }

    TileEntity getTileEntityFromTrait() {
        return tileEntity;
    }

    IInventoryAdaptor getIInventoryAdaptor() {
        return inventoryAdapter;
    }

    @Override
    int getSizeInventory() {
        return inventoryAdapter.getSizeInventory();
    }

    @Override
    ItemStack getStackInSlot(int index) {
        return inventoryAdapter.getStackInSlot(index);
    }

    @Override
    ItemStack decrStackSize(int index, int count) {
        return inventoryAdapter.decrStackSize(index, count);
    }

    @Override
    ItemStack removeStackFromSlot(int index) {
        return inventoryAdapter.removeStackFromSlot(index);
    }

    @Override
    void setInventorySlotContents(int index, ItemStack stack) {
        inventoryAdapter.setInventorySlotContents(index, stack);
    }

    @Override
    int getInventoryStackLimit() {
        return inventoryAdapter.getInventoryStackLimit();
    }

    @Override
<<<<<<< HEAD
    boolean isUsableByPlayer(EntityPlayer player) {
=======
    boolean isUsableByPlayer(PlayerEntity player) {
>>>>>>> 1.16.x
        return inventoryAdapter.isUsableByPlayer(player);
    }

    @Override
<<<<<<< HEAD
    void openInventory(EntityPlayer player) {
=======
    void openInventory(PlayerEntity player) {
>>>>>>> 1.16.x
        inventoryAdapter.openInventory(player);
    }

    @Override
<<<<<<< HEAD
    void closeInventory(EntityPlayer player) {
=======
    void closeInventory(PlayerEntity player) {
>>>>>>> 1.16.x
        inventoryAdapter.closeInventory(player);
    }

    @Override
    boolean isItemValidForSlot(int index, ItemStack stack) {
        return inventoryAdapter.isItemValidForSlot(index, stack);
<<<<<<< HEAD
    }

    @Override
    int getField(int id) {
        return 0;
    }

    @Override
    void setField(int id, int value) {

    }

    @Override
    int getFieldCount() {
        return 0;
=======
>>>>>>> 1.16.x
    }

    @Override
    void clear() {

    }

    @Override
    int[] getSlotsForFace(Direction side) {
        return inventoryAdapter.getSlotsForFace(side);
    }

    @Override
    boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return inventoryAdapter.canInsertItem(index, itemStackIn, direction);
    }

    @Override
    boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return inventoryAdapter.canExtractItem(index, stack, direction);
    }

    @Override
    boolean isEmpty() {
        return inventoryAdapter.isEmpty();
    }
}