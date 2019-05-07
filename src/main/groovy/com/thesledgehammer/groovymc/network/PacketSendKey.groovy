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

package com.thesledgehammer.groovymc.network

import io.netty.buffer.ByteBuf
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class PacketSendKey implements IMessage {

	private BlockPos blockPos;

	@Override
	void fromBytes(ByteBuf buf) {
		blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	void toBytes(ByteBuf buf) {
		buf.writeInt(blockPos.getX());
		buf.writeInt(blockPos.getY());
		buf.writeInt(blockPos.getZ());
	}

	PacketSendKey() {
		RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
		blockPos = mouseOver.getBlockPos();
	}

	static class Handler implements IMessageHandler<PacketSendKey, IMessage> {

		@Override
		IMessage onMessage(PacketSendKey message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(new Runnable() {
				@Override
				void run() {
					Handle(message, ctx);
				}
			});
		return null;
		}
		
		private void Handle(PacketSendKey message, MessageContext ctx) {
			EntityPlayerMP playerEntity = ctx.getServerHandler().player;
			World world = playerEntity.world;
			Block block = world.getBlockState(message.blockPos).getBlock();
			playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + block.getRegistryName()), false);
		}
	}
}
