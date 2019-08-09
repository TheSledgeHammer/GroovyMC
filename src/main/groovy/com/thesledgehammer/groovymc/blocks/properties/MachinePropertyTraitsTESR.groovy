/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Change to a Groovy Trait Converted to .groovy
 *********************************************************************************/
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.ForgeHooksClient
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

trait MachinePropertyTraitsTESR<T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesTESR<T> {

    private String particleTextureLocation;

    @Nullable
    @SideOnly(Side.CLIENT)
    private TileEntitySpecialRenderer<? super T> renderer;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @SideOnly(Side.CLIENT)
    void setRenderer(TileEntitySpecialRenderer<? super T> renderer) {
        this.renderer = renderer;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }

    @Override
    void registerTileEntity() {
        super.registerTileEntity();
        Block block = this.getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && renderer != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), renderer);
            Item item = Item.getItemFromBlock(block);
            if(item != Items.AIR) {
                //ForgeHooksClient.registerTESRItemStack(item, 0, getTeClass());
                //ForgeHooksClient.renderTileItem(item, 0);
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                item.setTileEntityItemStackRenderer(TEISR.renderByItem(new ItemStack(item, 1, 0)));
            }
        }
    }

    @Override
    void registerTileEntity(String modID) {
        super.registerTileEntity(modID);
        Block block = this.getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && renderer != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), renderer);
            Item item = Item.getItemFromBlock(block);
            if (item != Items.AIR) {
                //ForgeHooksClient.registerTESRItemStack(item, 0, getTeClass());
                //ForgeHooksClient.renderTileItem(item, 0);
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                item.setTileEntityItemStackRenderer(TEISR.renderByItem(new ItemStack(item, 1, 0)));
            }
        }
    }
}