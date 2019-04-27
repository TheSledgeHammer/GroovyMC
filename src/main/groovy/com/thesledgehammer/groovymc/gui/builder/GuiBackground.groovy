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

package com.thesledgehammer.groovymc.gui.builder

import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.util.ResourceLocation

class GuiBackground extends AbstractGui {

    private static ResourceLocation backgroundTexture = new ResourceLocation(Constants.TEXTURE_PATH_GUI + "/builder/background.png");

    GuiBackground() {
        super(backgroundTexture, 0, 0, 256, 256, 256, 256);
    }

    GuiBackground(ResourceLocation textureLocation, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(textureLocation, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }

    GuiBackground(String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(texture, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }

    GuiBackground(String modID, String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(modID, texture, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }
}
