/*********************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 * Modified by TheSledgeHammer 2018: Renamed from MachineStateMapper & Converted to .groovy
 *********************************************************************************/

package com.thesledgehammer.groovymc.utils

import com.google.common.collect.Maps
import com.thesledgehammer.groovymc.blocks.GroovyBlockTileAdvanced
import com.thesledgehammer.groovymc.blocks.GroovyBlockTileBasic
import com.thesledgehammer.groovymc.blocks.properties.IBlockType
import com.thesledgehammer.groovymc.blocks.properties.MachinePropertyTraits
import net.minecraft.block.Block
import net.minecraft.block.properties.IProperty
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.IStateMapper
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class GroovyMachineStateMapper<T extends Enum<T> & IBlockType & IStringSerializable> implements IStateMapper {

    private final T type;
    protected final Map<IBlockState, ModelResourceLocation> mapStateModelLocations = Maps.newLinkedHashMap();

    GroovyMachineStateMapper(T type) {
        this.type = type;
    }

    @Override
    Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block) {
        if (!(type.getGroovyMachineProperties() instanceof MachinePropertyTraits)) {
            for (EnumFacing facing : EnumFacing.values()) {
                if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                    continue;
                }
                IBlockState state;
                if(block instanceof GroovyBlockTileBasic) {
                  state = block.getDefaultState().withProperty(GroovyBlockTileBasic.FACING, facing);
                }
                if(block instanceof GroovyBlockTileAdvanced) {
                  state = block.getDefaultState().withProperty(GroovyBlockTileAdvanced.FACING, facing);
                }
                LinkedHashMap<IProperty, Comparable> linkedhashmap = Maps.newLinkedHashMap(state.getProperties());
                ResourceLocation blockLocation = Block.REGISTRY.getNameForObject(block);
                String s = String.format("%s:%s", blockLocation.getResourceDomain(), blockLocation.getResourcePath());
                mapStateModelLocations.put(state, new ModelResourceLocation(s, getPropertyString(linkedhashmap)));
            }
        }
        return this.mapStateModelLocations;
    }
}
