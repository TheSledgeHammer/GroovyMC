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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.config.Constants
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.util.ResourceLocation

abstract class AbstractGui {
    //Position on the Texture
    private int u;
    private int v;

    //Rectangle Size
    private int uWidth;
    private int vHeight;

    //Texture
    private ResourceLocation textureLocation;

    //Texture Size
    private int textureWidth;
    private int textureHeight;

    AbstractGui(ResourceLocation textureLocation, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        setU(u);
        setV(v);
        setUWidth(uWidth);
        setVHeight(vHeight);
        setTextureLocation(textureLocation);
        setTextureWidth(textureWidth);
        setTextureHeight(textureHeight);
    }

    AbstractGui(String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        setU(u);
        setV(v);
        setUWidth(uWidth);
        setVHeight(vHeight);
        setTextureLocation(texture);
        setTextureWidth(textureWidth);
        setTextureHeight(textureHeight);
    }

    AbstractGui(String modID, String texture, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        setU(u);
        setV(v);
        setUWidth(uWidth);
        setVHeight(vHeight);
        setTextureLocation(modID, texture);
        setTextureWidth(textureWidth);
        setTextureHeight(textureHeight);
    }

    void draw(int xOffset, int yOffset) {
        draw(xOffset, yOffset, uWidth, vHeight);
    }

    void draw(int xOffset, int yOffset, int width, int height) {
        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        textureManager.bindTexture(textureLocation);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(xOffset, yOffset, u, v, uWidth, vHeight, width, height, textureWidth, textureHeight);
    }

    int getU() {
        return u;
    }

    int getV() {
        return v;
    }

    int getuWidth() {
        return uWidth;
    }

    int getvHeight() {
        return vHeight;
    }

    ResourceLocation getTextureLocation() {
        return textureLocation;
    }

    int getTextureWidth() {
        return textureWidth;
    }

    int getTextureHeight() {
        return textureHeight;
    }

    private void setU(int u) {
        this.u = u;
    }

    private void setV(int v) {
        this.v = v;
    }

    private void setUWidth(int uWidth) {
        this.uWidth = uWidth;
    }

    private void setVHeight(int vHeight) {
        this.vHeight = vHeight;
    }

    private void setTextureLocation(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
    }

    private void setTextureLocation(String texture) {
        this.textureLocation = new ResourceLocation(GroovyLoader.Instance().getModID(), Constants.TEXTURE_PATH_GUI + '/' + texture);
    }

    private void setTextureLocation(String modID, String texture) {
        this.textureLocation = new ResourceLocation(modID, Constants.TEXTURE_PATH_GUI + '/' + texture);
    }

    private void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    private void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }
}
