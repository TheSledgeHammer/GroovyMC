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
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.animation.TileEntityRendererAnimation
import net.minecraftforge.fml.client.registry.ClientRegistry

import javax.annotation.Nullable

trait MachinePropertyTraitsTERAnimation<T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesTERAnimation<T> {

    private String particleTextureLocation;

    @Nullable
    @OnlyIn(Dist.CLIENT)
    private TileEntityRendererAnimation<? super T> rendererAnimation;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void setRenderer(TileEntityRendererAnimation< ? super T > rendererAnimation) {
        this.rendererAnimation = rendererAnimation;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }


    @Override
    void registerTileEntity() {
        super.registerTileEntity();
        Block block = this.getBlock();
        if(Dist.CLIENT && rendererAnimation != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererAnimation);
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
        if(Dist.CLIENT && rendererAnimation != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererAnimation);
            Item item = Item.BLOCK_TO_ITEM.get(block);
            if (item != Items.AIR) {
                ItemStackTileEntityRenderer TEISR = item.getTileEntityItemStackRenderer();
                TEISR.renderByItem(new ItemStack(item));
            }
        }
    }
}