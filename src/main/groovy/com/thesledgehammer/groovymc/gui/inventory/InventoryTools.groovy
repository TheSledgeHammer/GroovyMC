/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Renamed from InventoryUtil & Converted to .groovy
 ******************************************************************************/

package com.thesledgehammer.groovymc.gui.inventory

import net.minecraft.entity.item.ItemEntity
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandler

class InventoryTools {

    static void dropInventory(IInventory inventory, World world, double x, double y, double z) {
        for(int slot = 0; slot < inventory.getSizeInventory(); slot++) {
            ItemStack itemstack = inventory.getStackInSlot(slot);
            dropItemStackFromInventory(itemstack, world, x, y, z);
            inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
        }
    }

    static void dropInventory(IInventory inventory, World world, BlockPos pos) {
        dropInventory(inventory, world, pos.getX(), pos.getY(), pos.getZ());
    }

    static void dropItemStackFromInventory(ItemStack itemStack, World world, double x, double y, double z) {
        if(itemStack.isEmpty()) {
            return;
        }

        double f = world.rand.nextFloat() * 0.8F + 0.1F;
        double f1 = world.rand.nextFloat() * 0.8F + 0.1F;
        double f2 = world.rand.nextFloat() * 0.8F + 0.1F;

        while (!itemStack.isEmpty()) {
            int stackPartial = world.rand.nextInt(21) + 10;
            if (stackPartial > itemStack.getCount()) {
                stackPartial = itemStack.getCount();
            }
            ItemStack drop = itemStack.split(stackPartial);
            ItemEntity entityitem = new ItemEntity(world, x + f, y + f1, z + f2, drop);
            float accel = 0.05F;
            entityitem.motion.x = (float) world.rand.nextGaussian() * accel;
            entityitem.motion.y = (float) world.rand.nextGaussian() * accel + 0.2F;
            entityitem.motion.z= (float) world.rand.nextGaussian() * accel;
          //  world.s(entityitem);
        }
    }

    static void addAll(IItemHandler src, NonNullList<ItemStack> dst) {
        for(int i = 0; i < src.getSlots(); i++) {
            ItemStack stack = src.getStackInSlot(i);
            if(!stack.isEmpty()) {
                dst.add(stack);
            }
        }
    }
}
