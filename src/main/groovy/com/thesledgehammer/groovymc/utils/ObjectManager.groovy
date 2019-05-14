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
/*
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.statemap.BlockStateMapper
import net.minecraft.client.renderer.block.statemap.IStateMapper
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ObjectManager {

	private final static Set<IItemColor> itemColorList = new HashSet<>();
	private final static Set<IBlockColor> blockColorList = new HashSet<>();
	private final static Set<IStateMapper> stateMapperList = new HashSet<>();

	static void RegisterColors() {
		registerItemColor();
		registerBlockColor();
	}

	@SideOnly(Side.CLIENT)
	static void registerBlockClient(Block block) {
		if(block instanceof IBlockColor) {
			blockColorList.add((IBlockColor) block);
		}
		if(block instanceof IStateMapper) {
			stateMapperList.add((IStateMapper) block);
		}
		for (IStateMapper stateMapper : stateMapperList) {
			if(stateMapper instanceof BlockStateMapper) {
				stateMapper.registerBlockStateMapper(block, stateMapper);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	static void registerItemClient(Item item) {
		if(item instanceof IItemColor) {
			itemColorList.add((IItemColor) item);
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerItemColor() {
		for (IItemColor itemColor : itemColorList) {
			if (itemColor instanceof Item) {
				Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ColoredItemItemColor.INSTANCE, (Item) itemColor);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private static void registerBlockColor() {
		for (IBlockColor blockColor : blockColorList) {
			if (blockColor instanceof Block) {
				Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(ColoredBlockBlockColor.INSTANCE, (Block) blockColor);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private static class ColoredItemItemColor implements IItemColor {
		private static final ColoredItemItemColor INSTANCE = new ColoredItemItemColor();

		private ColoredItemItemColor() {

		}

		@Override
		int colorMultiplier(ItemStack stack, int tintIndex) {
			Item item = stack.getItem();
			if (item instanceof IItemColor) {
				return ((IItemColor) item).colorMultiplier(stack, tintIndex);
			}
			return 0xffffff;
		}
	}

	@SideOnly(Side.CLIENT)
	private static class ColoredBlockBlockColor implements IBlockColor {
		public static final ColoredBlockBlockColor INSTANCE = new ColoredBlockBlockColor();

		private ColoredBlockBlockColor() {

		}

		@Override
		int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
			Block block = state.getBlock();
			if (block instanceof IBlockColor) {
				return ((IBlockColor) block).colorMultiplier(state, worldIn, pos, tintIndex);
			}
			return 0xffffff;
		}
	}
}
*/