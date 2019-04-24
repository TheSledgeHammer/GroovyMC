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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.util.ResourceLocation

class GuiGroovy<T extends ContainerGroovy> extends GuiContainer {
	
	static final int WIDTH = 180
	static final int HEIGHT = 152
	
	final T container
	
	private ResourceLocation background;
	
	GuiGroovy(T container) {
		super(container)
		this.container = container
		xSize = WIDTH
		ySize = HEIGHT
	}

	GuiGroovy(T container, String backgroundTexture) {
		super(container)
		setBackground(backgroundTexture)
		this.container = container
		xSize = WIDTH
		ySize = HEIGHT
	}

	GuiGroovy(T container, String modID, String backgroundTexture) {
		super(container)
		setBackground(modID, backgroundTexture)
		this.container = container
		xSize = WIDTH
		ySize = HEIGHT
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background)
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
	}

	void setBackground(String texture) {
		this.background = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_GUI + '/' + texture);
	}

	void setBackground(String modID, String texture) {
		this.background = new ResourceLocation(modID, Constants.TEXTURE_PATH_GUI + '/' + texture);
	}

	ResourceLocation getBackground() {
		return background;
	}
}
