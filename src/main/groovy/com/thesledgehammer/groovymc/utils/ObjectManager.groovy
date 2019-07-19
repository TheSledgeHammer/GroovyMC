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

package com.thesledgehammer.groovymc.utils

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IEnviromentBlockReader
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.annotation.Nullable

class ObjectManager {

	private final static Set<IItemColor> itemColorList = new HashSet<>();
	private final static Set<IBlockColor> blockColorList = new HashSet<>();
	//private final static Set<IStateMapper> stateMapperList = new HashSet<>();

	static void RegisterColors() {
		registerItemColor();
		registerBlockColor();
	}

    @OnlyIn(Dist.CLIENT)
	static void registerBlockClient(Block block) {
		if(block instanceof IBlockColor) {
			blockColorList.add((IBlockColor) block);
		}
        /*
		if(block instanceof StateMapper) {
			stateMapperList.add((IStateMapper) block);
		}
		for (IStateMapper stateMapper : stateMapperList) {
			if(stateMapper instanceof BlockStateMapper) {
				stateMapper.registerBlockStateMapper(block, stateMapper);
			}
		}
		*/
	}

    @OnlyIn(Dist.CLIENT)
	static void registerItemClient(Item item) {
		if(item instanceof IItemColor) {
			itemColorList.add((IItemColor) item);
		}
	}

    @OnlyIn(Dist.CLIENT)
	private static void registerItemColor() {
		for (IItemColor itemColor : itemColorList) {
			if (itemColor instanceof Item) {
				Minecraft.instance.getItemColors().register(ColoredItemItemColor.INSTANCE, (Item) itemColor);
			}
		}
	}

    @OnlyIn(Dist.CLIENT)
	private static void registerBlockColor() {
		for (IBlockColor blockColor : blockColorList) {
			if (blockColor instanceof Block) {
				Minecraft.instance.getBlockColors().register(ColoredBlockBlockColor.INSTANCE, (Block) blockColor);
			}
		}
	}

    @OnlyIn(Dist.CLIENT)
	private static class ColoredItemItemColor implements IItemColor {
		private static final ColoredItemItemColor INSTANCE = new ColoredItemItemColor();

		private ColoredItemItemColor() {

		}

        @Override
        int getColor(ItemStack stack, int tintIndex) {
            Item item = stack.getItem();
            if (item instanceof IItemColor) {
                return ((IItemColor) item).getColor(stack, tintIndex);
            }
            return 0xffffff
        }
    }

    @OnlyIn(Dist.CLIENT)
	private static class ColoredBlockBlockColor implements IBlockColor {
		public static final ColoredBlockBlockColor INSTANCE = new ColoredBlockBlockColor();

		private ColoredBlockBlockColor() {

		}

        @Override
        int getColor(BlockState state, @Nullable IEnviromentBlockReader world, @Nullable BlockPos pos, int tintIndex) {
            Block block = state.getBlock();
            if (block instanceof IBlockColor) {
                return ((IBlockColor) block).getColor(state, world, pos, tintIndex);
            }
            return 0xffffff;
        }
    }
}
