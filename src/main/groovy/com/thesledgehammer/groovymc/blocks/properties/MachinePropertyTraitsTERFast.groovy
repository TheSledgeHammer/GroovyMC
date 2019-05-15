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
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.model.animation.TileEntityRendererFast

import javax.annotation.Nullable

trait MachinePropertyTraitsTERFast<T extends GroovyTileBasic> extends MachinePropertyTraits<T> implements IMachinePropertiesTERFast<T> {

    private String particleTextureLocation;

    @Nullable
    @OnlyIn(Dist.CLIENT)
    private TileEntityRendererFast<? super T> rendererFast;

    @Override
    void setParticleTextureLocation(String particleTextureLocation) {
        this.particleTextureLocation = particleTextureLocation;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    void setRenderer(TileEntityRendererFast<? super T> rendererFast) {
        this.rendererFast = rendererFast;
    }

    @Override
    String getParticleTextureLocation() {
        return particleTextureLocation;
    }
/*
    @Override
    void registerTileEntity() {
        super.registerTileEntity();
        Block block = this.getBlock();
        if(FMLCommonHandler.instance().getSide() == Dist.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
            Item item = Item.BLOCK_TO_ITEM.get(block);
            if (item != Items.AIR) {
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                TEISR.renderByItem(new ItemStack(item));
            }
        }
    }

    @Override
    void registerTileEntity(String modID) {
        super.registerTileEntity(modID);
        Block block = this.getBlock();
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT && rendererFast != null && block != null) {
            ClientRegistry.bindTileEntitySpecialRenderer(getTeClass(), rendererFast);
            Item item = Item.BLOCK_TO_ITEM.get(block);
            if (item != Items.AIR) {
                TileEntityItemStackRenderer TEISR = TileEntityItemStackRenderer.instance;
                TEISR.renderByItem(new ItemStack(item));
            }
        }
    }
 */
}
