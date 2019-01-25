/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.gui


import com.thesledgehammer.groovymc.GroovyMC
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.util.ResourceLocation

class GuiGroovy<T extends ContainerGroovy> extends GuiContainer {
	
	public static final int WIDTH = 180
	public static final int HEIGHT = 152
	
	final T container
	
	private static final ResourceLocation background = new ResourceLocation(GroovyMC.MOD_ID, "textures/gui/container.png")
	
	GuiGroovy(T container) {
		super(container)
		this.container = container
		xSize = WIDTH
		ySize = HEIGHT
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background)
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
	}
}
