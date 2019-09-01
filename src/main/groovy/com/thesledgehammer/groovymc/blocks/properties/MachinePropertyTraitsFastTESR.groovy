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
package com.thesledgehammer.groovymc.blocks.properties

import com.thesledgehammer.groovymc.tiles.GroovyTileBasic
import net.minecraft.block.Block
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
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
        Block block = this.getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
            Item item = Item.getItemFromBlock(block);
            if (item != Items.AIR) {
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
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
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
