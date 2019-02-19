/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.ForgeHooksClient
import net.minecraftforge.client.model.animation.FastTESR
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraitsFastTESR <T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesFastTESR<T> {

    private String particleTextureLocation;

    @Nullable
    @SideOnly(Side.CLIENT)
    private FastTESR<? super T> rendererFast;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @SideOnly(Side.CLIENT)
    void setRenderer(FastTESR<? super T> rendererFast) {
        this.rendererFast = rendererFast;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }

    @Override
    void registerTileEntity() {
        super.registerTileEntity();
        Block block = getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
            Item item = Item.getItemFromBlock(block);
            if (item != Items.AIR) {
                //ForgeHooksClient.registerTESRItemStack(item, 0, getTeClass());
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                item.setTileEntityItemStackRenderer(TEISR.renderByItem(new ItemStack(item, 1, 0)));
            }
        }
    }

    @Override
    void registerTileEntity(String modID) {
        super.registerTileEntity(modID);
        Block block = getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
            Item item = Item.getItemFromBlock(block);
            if (item != Items.AIR) {
                //ForgeHooksClient.registerTESRItemStack(item, 0, getTeClass());
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                item.setTileEntityItemStackRenderer(TEISR.renderByItem(new ItemStack(item, 1, 0)));
            }
        }
    }
}
