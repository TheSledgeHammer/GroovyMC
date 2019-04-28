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

package com.thesledgehammer.groovymc.experimental.gui.builder

import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.util.ResourceLocation

class GuiSlot extends AbstractGui {

    private int xNumber;
    private int yNumber;
    private int xSize;
    private int ySize;
    private int xBoundary;
    private int yBoundary;
    private static ResourceLocation slotTexture = new ResourceLocation(Constants.TEXTURE_PATH_GUI + "/builder/slot.png");

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

    private void setXBoundary(int xSize, int xNumber) {
        setXSize(xSize);
        setXNumberOfSlots(xNumber);
        this.xBoundary = xSize * xNumber;
    }

    private void setYBoundary(int ySize, int yNumber) {
        setYSize(ySize);
        setYNumberOfSlots(yNumber);
        this.yBoundary = ySize * yNumber;
    }

    private void setXSize(int xSize) {
        this.xSize = xSize;
    }

    private void setYSize(int ySize) {
        this.ySize = ySize;
    }

    private void setXNumberOfSlots(int xNumber) {
        this.xNumber = xNumber;
    }

    private void setYNumberOfSlots(int yNumber) {
        this.yNumber = yNumber;
    }

    int getXBoundary() {
        return xBoundary;
    }

    int getYBoundary() {
        return yBoundary;
    }

    int getXSize() {
        return xSize;
    }

    int getYSize() {
        return ySize;
    }

    int getXNumber() {
        return xNumber;
    }

    int getYNumber() {
        return yNumber;
    }

    /**
     * Draws a grid of slots at a set texture size
     * @param xOffsetBase: the base x value
     * @param yOffsetBase: the base y value
     * @param xSize: Size of texture along x axis
     * @param ySize: Size of texture along y axis
     * @param xNumber: The number of slots along the x axis
     * @param yNumber: The number of slots along the y axis
     */
    void drawSlotGrid(int xOffsetBase, int yOffsetBase, int xSize, int ySize, int xNumber, int yNumber) {
        setXBoundary(xSize, xNumber);
        setYBoundary(ySize, yNumber);
        for(int x = 0; x < getXBoundary(); x+= xSize) {
            for(int y = 0; y < getYBoundary(); y+= ySize) {
                draw(xOffsetBase + x, yOffsetBase + y, xSize, ySize);
            }
        }
    }

    void drawSlotGrid(int xOffsetBase, int yOffsetBase, int xNumber, int yNumber) {
        setXBoundary(18, xNumber);
        setYBoundary(18, yNumber);
        for(int x = 0; x < getXBoundary(); x+= 18) {
            for(int y = 0; y < getYBoundary(); y+= 18) {
                draw(xOffsetBase + x, yOffsetBase + y, 18, 18);
            }
        }
    }
}
