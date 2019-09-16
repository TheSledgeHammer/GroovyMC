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


import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.state.EnumProperty
import net.minecraft.util.Direction

//Todo: Efficient Way to create Tile Entities: Setting hasTileEntity to true, would make this like ITileEntityProvider
abstract class GroovyBlockTileBasic extends GroovyBlock {

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP);

    GroovyBlockTileBasic(Block.Properties properties) {
        super(properties);
    }

    GroovyBlockTileBasic(Material material) {
        super(Block.Properties.create(material));
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
    }
}
