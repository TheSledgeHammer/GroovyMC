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

import com.thesledgehammer.groovymc.utils.IInventoryAdaptor
import com.thesledgehammer.groovymc.utils.InventoryAdaptor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing

trait TileInventoryTraits implements IInventory, ISidedInventory {

    private TileEntity tileEntity;
    private IInventory inventory;
    private IInventoryAdaptor inventoryAdapter = new InventoryAdaptor();

    void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    void setIInventory(IInventory inventory) {
        this.inventory = inventory;
    }

    void setIInventoryAdaptor(IInventoryAdaptor inventoryAdapter) {
        this.inventoryAdapter = inventoryAdapter;
    }

    TileEntity getTileEntityFromTrait() {
        return tileEntity;
    }

    IInventory getIInventoryFromTait() {
        return inventory;
    }

    IInventoryAdaptor getIInventoryAdaptor() {
        return inventoryAdapter;
    }

    @Override
    int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    ItemStack getStackInSlot(int index) {
        return inventory.getStackInSlot(index);
    }

    @Override
    ItemStack decrStackSize(int index, int count) {
        return inventory.decrStackSize(index, count);
    }

    @Override
    ItemStack removeStackFromSlot(int index) {
        return inventory.removeStackFromSlot(index);
    }

    @Override
    void setInventorySlotContents(int index, ItemStack stack) {
        inventory.setInventorySlotContents(index, stack);
    }

    @Override
    int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    boolean isUsableByPlayer(EntityPlayer player) {
        return inventory.isUsableByPlayer(player);
    }

    @Override
    void openInventory(EntityPlayer player) {
        inventory.openInventory(player);
    }

    @Override
    void closeInventory(EntityPlayer player) {
        inventory.closeInventory(player);
    }

    @Override
    boolean isItemValidForSlot(int index, ItemStack stack) {
        return inventory.isItemValidForSlot(index, stack);
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
    }

    @Override
    void clear() {

    }

    @Override
    int[] getSlotsForFace(EnumFacing side) {
        return inventoryAdapter.getSlotsForFace(side);
    }

    @Override
    boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return inventoryAdapter.canInsertItem(index, itemStackIn, direction);
    }

    @Override
    boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return inventoryAdapter.canExtractItem(index, stack, direction);
    }

    @Override
    boolean isEmpty() {
        return inventoryAdapter.isEmpty();
    }

    @Override
    boolean hasCustomName() {
        return inventoryAdapter.hasCustomName();
    }

    @Override
    String getName() {
        String blockUnlocalizedName = tileEntity.getBlockType().getUnlocalizedName();
        return blockUnlocalizedName + '.' + tileEntity.getBlockMetadata() + ".name";
    }
}