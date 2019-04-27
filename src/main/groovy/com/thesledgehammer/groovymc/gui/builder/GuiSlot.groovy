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

class GuiSlot extends AbstractGui {

    private static ResourceLocation slotTexture = new ResourceLocation(Constants.TEXTURE_PATH_GUI + "/components/slot.png");

    GuiSlot() {
        super(slotTexture, 0, 0, 18, 18, 18, 18);
    }

    GuiSlot(ResourceLocation textureLocation, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(textureLocation, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }

    GuiSlot(String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(texture, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }

    GuiSlot(String modID, String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        super(modID, texture, u, v, uWidth, vHeight, textureWidth, textureHeight)
    }

    /**
     * Draws a single row of 9 slots, for the player hotbar with the default texture size
     * @param xOffsetBase : the base x value, for where the 9 player slots start
     * @param yOffsetBase : the base y value, for where the 9 player slots start
     */
    void drawPlayerHotbar(int xOffsetBase, int yOffsetBase) {
        for (int x = 0; x < 162; x += 18) {
            draw(xOffsetBase + x, yOffsetBase);
        }
    }

    /**
     *  Draws a single row of 9 slots, for the player hotbar at a set texture size
     * @param xOffsetBase: the base x value, for where the 9 player slots start
     * @param yOffsetBase: the base y value, for where the 9 player slots start
     * @param xSize: Size of texture along x axis
     * @param ySize: Size of texture along y axis
     */
    void drawPlayerHotbar(int xOffsetBase, int yOffsetBase, int xSize, int ySize) {
        int xBoundary = xSize * 9;
        for(int x = 0; x < xBoundary; x+=xSize) {
            draw(xOffsetBase + x, yOffsetBase, xSize, ySize);
        }
    }

    /**
     * Draws 3 rows of 9 slots, for the player inventory with the default texture size
     * @param xOffsetBase : the base x value, for where the 9 player slots start
     * @param yOffsetBase : the base y value, for where the 9 player slots start
     */
    void drawPlayerInventory(int xOffsetBase, int yOffsetBase) {
        for (int x = 0; x < 162; x += 18) {
            for (int y = 0; y < 54; y += 18) {
                draw(xOffsetBase + x, yOffsetBase + y);
            }
        }
    }

    /**
     * Draws 3 rows of 9 slots, for the player inventory at a set texture size
     * @param xOffsetBase : the base x value, for where the 9 player slots start
     * @param yOffsetBase : the base y value, for where the 9 player slots start
     * @param xSize: Size of texture along x axis
     * @param ySize: Size of texture along y axis
     */
    void drawPlayerInventory(int xOffsetBase, int yOffsetBase, int xSize, int ySize) {
        int xBoundary = xSize * 9;
        int yBoundary = ySize * 3
        for (int x = 0; x < xBoundary; x += xSize) {
            for (int y = 0; y < yBoundary; y += ySize) {
                draw(xOffsetBase + x, yOffsetBase + y, xSize, ySize);
            }
        }
    }
}
