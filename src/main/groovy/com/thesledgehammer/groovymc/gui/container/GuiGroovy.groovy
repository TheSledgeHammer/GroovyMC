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

package com.thesledgehammer.groovymc.gui.container

import com.thesledgehammer.groovymc.gui.builder.GuiBackground
import com.thesledgehammer.groovymc.gui.builder.GuiSlot
import net.minecraft.client.gui.inventory.GuiContainer

class GuiGroovy<C extends ContainerGroovy> extends GuiContainer {
	
	static final int WIDTH = 200
	static final int HEIGHT = 200

	private final GuiSlot guiSlot = new GuiSlot();
	private final GuiBackground guiBackground = new GuiBackground();
	
	GuiGroovy(C container) {
		super(container)
		setXSize(WIDTH);
		setYSize(HEIGHT);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int xOffset = xSize - (xSize - 19);
		int yOffset = (ySize - 22);
		guiSlot.drawPlayerHotbar(xOffset, yOffset);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		guiBackground.draw(guiLeft, guiTop,  xSize, ySize);
	}

	@Override
	void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	void setXSize(int xSize) {
		this.xSize = xSize;
	}

	void setYSize(int ySize) {
		this.ySize = ySize;
	}
}
