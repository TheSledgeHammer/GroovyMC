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
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer
import net.minecraft.client.renderer.tileentity.TileEntityRenderer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.client.registry.ClientRegistry

import javax.annotation.Nullable

trait MachinePropertyTraitsTER<T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesTER<T> {

    private String particleTextureLocation;

    @Nullable
    @OnlyIn(Dist.CLIENT)
    private TileEntityRenderer<? super T> renderer;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void setRenderer(TileEntityRenderer<? super T> renderer) {
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
        if(Dist.CLIENT && renderer != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), renderer);
            Item item = Item.BLOCK_TO_ITEM.get(block);
            if(item != Items.AIR) {
                ItemStackTileEntityRenderer TEISR = item.getTileEntityItemStackRenderer();
                TEISR.renderByItem(new ItemStack(item));
            }
        }
    }

    @Override
    void registerTileEntity(String modID) {
        super.registerTileEntity(modID);
        Block block = this.getBlock();
        if(Dist.CLIENT && renderer != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), renderer);
            Item item = Item.BLOCK_TO_ITEM.get(block);
            if (item != Items.AIR) {
                ItemStackTileEntityRenderer TEISR = item.getTileEntityItemStackRenderer();
                TEISR.renderByItem(new ItemStack(item));
            }
        }
    }
}