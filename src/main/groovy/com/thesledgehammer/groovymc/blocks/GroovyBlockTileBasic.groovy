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

package com.thesledgehammer.groovymc.blocks

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.api.IRegisterTileEntity
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import com.thesledgehammer.groovymc.energy.ForgeEnergyTile
import com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft.MinecraftJoulesTile
import com.thesledgehammer.groovymc.experimental.integration.modules.industrialcraft.EnergyUnitTile
import com.thesledgehammer.groovymc.experimental.integration.modules.theoneprobe.EnumColorType
import com.thesledgehammer.groovymc.experimental.integration.modules.theoneprobe.ITheOneProbeInfoProvider
import mcjty.theoneprobe.api.ElementAlignment
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class GroovyBlockTileBasic extends GroovyBlock implements BlockTileTraits, ITileEntityProvider, IRegisterTileEntity, ITheOneProbeInfoProvider {

    GroovyBlockTileBasic(Material blockMaterialIn) {
        super(blockMaterialIn);
    }

    GroovyBlockTileBasic() {
        super(Material.IRON);
    }

    @Override
    int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    @Override
    BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    void registerTileEntity(Class<? extends TileEntity> tileEntity, String modId, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(modId, tileName));
    }

    @Override
    void registerTileEntity(Class<? extends TileEntity> tileEntity, String tileName) {
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(GroovyLoader.Instance().getModID(), tileName));
    }

    @Override
    void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
        TileEntity te = world.getTileEntity(data.getPos());
        if(te instanceof MinecraftJoulesTile) {
            MinecraftJoulesTile mjTile = (MinecraftJoulesTile) te;
            long energyStored = mjTile.getStored();
            long capacity = mjTile.getCapacity();
            horizontalPane.text(TextFormatting.GREEN + "Energy Stored: " + capacity);
            horizontalPane.progress(energyStored, capacity, probeInfo.defaultProgressStyle()
                    .suffix("/" + capacity + " MJ")
                    .borderColor(EnumColorType.MJ.getBorderColor())
                    .backgroundColor(EnumColorType.MJ.getBackgroundColor())
                    .filledColor(EnumColorType.MJ.getFilledColor())
                    .alternateFilledColor(EnumColorType.MJ.getAlternateFilledColor()));
        }
        if(te instanceof EnergyUnitTile) {
            EnergyUnitTile euTile = (EnergyUnitTile) te;
            long energyStored = euTile.getOfferedEnergy() as long;
            long capacity = euTile.getMaxEnergyStored() as long;
            horizontalPane.text(TextFormatting.GREEN + "Energy Stored: " + capacity);
            horizontalPane.progress(energyStored, capacity, probeInfo.defaultProgressStyle()
                    .suffix("/" + capacity + " EU")
                    .borderColor(EnumColorType.EU.getBorderColor())
                    .backgroundColor(EnumColorType.EU.getBackgroundColor())
                    .filledColor(EnumColorType.EU.getFilledColor())
                    .alternateFilledColor(EnumColorType.EU.getAlternateFilledColor()));
        }
        if(te instanceof ForgeEnergyTile) {
            ForgeEnergyTile feTile = (ForgeEnergyTile) te;
            int energyStored = feTile.getEnergyStored();
            int capacity = feTile.getMaxEnergyStored();
            horizontalPane.text(TextFormatting.GREEN + "Energy Stored: " + capacity);
            horizontalPane.progress(energyStored, capacity, probeInfo.defaultProgressStyle()
                    .suffix("/" + capacity + " FE")
                    .borderColor(EnumColorType.FE.getBorderColor())
                    .backgroundColor(EnumColorType.FE.getBackgroundColor())
                    .filledColor(EnumColorType.FE.getFilledColor())
                    .alternateFilledColor(EnumColorType.FE.getAlternateFilledColor()));
        }
    }
}
