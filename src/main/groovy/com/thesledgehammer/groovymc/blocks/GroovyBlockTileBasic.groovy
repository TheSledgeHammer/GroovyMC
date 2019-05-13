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

import com.thesledgehammer.groovymc.api.IRegisterTileEntity
import com.thesledgehammer.groovymc.blocks.traits.BlockTileTraits
import com.thesledgehammer.groovymc.api.GroovyLoader
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.chunk.BlockStateContainer
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class GroovyBlockTileBasic extends GroovyBlock implements BlockTileTraits, ITileEntityProvider, IRegisterTileEntity {

    GroovyBlockTileBasic(Properties properties) {
        super(properties);
    }

    GroovyBlockTileBasic() {
        super();
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
