/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.blocks

import com.thesledgehammer.groovymc.api.IRegisterTileEntity
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class GroovyBlockTileBasic extends GroovyBlock implements BlockTileTraits, ITileEntityProvider, IRegisterTileEntity {

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
}
