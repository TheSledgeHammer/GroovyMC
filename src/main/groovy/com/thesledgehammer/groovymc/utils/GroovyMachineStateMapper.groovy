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
/*
@OnlyIn(Dist.CLIENT)
class GroovyMachineStateMapper<T extends Enum<T> & IBlockType & IStringSerializable> implements IStateContainer {

    private final T type;
    protected final Map<BlockState, ModelResourceLocation> mapStateModelLocations = Maps.newLinkedHashMap();

    GroovyMachineStateMapper(T type) {
        this.type = type;
    }

    @Override
    Map<BlockState, ModelResourceLocation> putStateModelLocations(Block block) {
        if (!(type.getGroovyMachineProperties() instanceof MachinePropertyTraits)) {
            for (Direction facing : Direction.values()) {
                if (facing == Direction.DOWN || facing == Direction.UP) {
                    continue;
                }
                BlockState state;
                if(block instanceof GroovyBlockTileBasic) {
                  state = block.getDefaultState().with(GroovyBlockTileBasic.FACING, facing);
                }
                if(block instanceof GroovyBlockTileAdvanced) {
                  state = block.getDefaultState().with(GroovyBlockTileAdvanced.FACING, facing);
                }
                LinkedHashMap<IProperty, Comparable> linkedhashmap = Maps.newLinkedHashMap();
                ResourceLocation blockLocation = Block.getStateId()  REGISTRY.getNameForObject(block);
                String s = String.format("%s:%s", blockLocation .getResourceDomain(), blockLocation.getResourcePath());
                mapStateModelLocations.put(state, new ModelResourceLocation(s, getPropertyString(linkedhashmap)));
            }
        }
        return this.mapStateModelLocations;
    }
}
*/