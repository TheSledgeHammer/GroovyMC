/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.api.client.ISprite
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.client.renderer.vertex.VertexFormatElement
import net.minecraft.util.Tuple
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.vector.Matrix4f
import net.minecraft.util.math.vector.Vector3d
import net.minecraft.util.math.vector.Vector3f
import net.minecraft.util.math.vector.Vector3i
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

import javax.vecmath

/**
 * Holds all of the information necessary to make one of the verticies in a {@link BakedQuad}. This provides a variety
 * of methods to quickly set or get different elements. This should be used with {@link MutableQuad} to make a face, or
 * by itself if you only need to define a single vertex. <br>
 * This currently holds the 3D position, normal, colour, 2D texture, skylight and blocklight. Note that you don't have
 * to use all of the elements for this to work - the extra elements come with sensible defaults. <br>
 * All of the mutating methods are in the form {@literal <element><type>}, where {@literal <element>} is the element to
 * set/get, and {@literal <type>} is the type that they should be set as. So {@link #positiond(double, double, double)}
 * will take in 3 doubles and set them to the position element, and {@link #colouri(int, int, int, int)} will take in 4
 * int's and set them to the colour elements.
 */
@OnlyIn(Dist.CLIENT)
class MutableVertex {
    /** The position of this vertex. */
    float position_x, position_y, position_z;
    /** The normal of this vertex. Might not be normalised. Default value is [0, 1, 0]. */
    float normal_x, normal_y, normal_z;
    /** The colour of this vertex, where each one is a number in the range 0-255. Default value is 255. */
    short colour_r, colour_g, colour_b, colour_a;
    /** The texture co-ord of this vertex. Should usually be between 0-1 */
    float tex_u, tex_v;
    /** The light of this vertex. Should be in the range 0-15. */
    byte light_block, light_sky;

    MutableVertex() {
        normal_x = 0;
        normal_y = 1;
        normal_z = 0;

        colour_r = 0xFF;
        colour_g = 0xFF;
        colour_b = 0xFF;
        colour_a = 0xFF;
    }

    MutableVertex(MutableVertex from) {
        copyFrom(from);
    }

    @Override
    String toString() {
        return "{ pos = [ " + position_x + ", " + position_y + ", " + position_z + " ], norm = [ " + normal_x + ", " + normal_y + ", " + normal_z + " ], colour = [ " + colour_r + ", " + colour_g + ", " + colour_b + ", " + colour_a + " ], tex = [ " + tex_u + ", " + tex_v + " ], light_block = " + light_block + ", light_sky = " + light_sky + " }";
    }

    MutableVertex copyFrom(MutableVertex from) {
        position_x = from.position_x;
        position_y = from.position_y;
        position_z = from.position_z;

        normal_x = from.normal_x;
        normal_y = from.normal_y;
        normal_z = from.normal_z;

        colour_r = from.colour_r;
        colour_g = from.colour_g;
        colour_b = from.colour_b;
        colour_a = from.colour_a;

        tex_u = from.tex_u;
        tex_v = from.tex_v;

        light_block = from.light_block;
        light_sky = from.light_sky;
        return this;
    }

    void toBakedBlock(int[] data, int offset) {
        // POSITION_3F
        data[offset + 0] = Float.floatToRawIntBits(position_x);
        data[offset + 1] = Float.floatToRawIntBits(position_y);
        data[offset + 2] = Float.floatToRawIntBits(position_z);
        // COLOR_4UB
        data[offset + 3] = colourRGBA();
        // TEX_2F
        data[offset + 4] = Float.floatToRawIntBits(tex_u);
        data[offset + 5] = Float.floatToRawIntBits(tex_v);
        // TEX_2S
        data[offset + 6] = lightc();
    }

    void toBakedItem(int[] data, int offset) {
        // POSITION_3F
        data[offset + 0] = Float.floatToRawIntBits(position_x);
        data[offset + 1] = Float.floatToRawIntBits(position_y);
        data[offset + 2] = Float.floatToRawIntBits(position_z);
        // COLOR_4UB
        data[offset + 3] = colourRGBA();
        // TEX_2F
        data[offset + 4] = Float.floatToRawIntBits(tex_u);
        data[offset + 5] = Float.floatToRawIntBits(tex_v);
        // NORMAL_3B
        data[offset + 6] = normalToPackedInt();
    }

    void fromBakedBlock(int[] data, int offset) {
        // POSITION_3F
        position_x = Float.intBitsToFloat(data[offset + 0]);
        position_y = Float.intBitsToFloat(data[offset + 1]);
        position_z = Float.intBitsToFloat(data[offset + 2]);
        // COLOR_4UB
        colouri(data[offset + 3]);
        // TEX_2F
        tex_u = Float.intBitsToFloat(data[offset + 4]);
        tex_v = Float.intBitsToFloat(data[offset + 5]);
        // TEX_2S
        lighti(data[offset + 6]);
        normalf(0, 1, 0);
    }

    void fromBakedItem(int[] data, int offset) {
        // POSITION_3F
        position_x = Float.intBitsToFloat(data[offset + 0]);
        position_y = Float.intBitsToFloat(data[offset + 1]);
        position_z = Float.intBitsToFloat(data[offset + 2]);
        // COLOR_4UB
        colouri(data[offset + 3]);
        // TEX_2F
        tex_u = Float.intBitsToFloat(data[offset + 4]);
        tex_v = Float.intBitsToFloat(data[offset + 5]);
        // NORMAL_3B
        normali(data[offset + 6]);
        lightf(1, 1);
    }

    // Rendering

    void render(BufferBuilder bb) {
        VertexFormat vf = bb.getVertexFormat();
        if (vf == DefaultVertexFormats.BLOCK) {
            renderAsBlock(bb);
        } else {
            for (VertexFormatElement vfe : vf.getElements()) {
                if (vfe.getUsage() == VertexFormatElement.Usage.POSITION) renderPosition(bb);
                else if (vfe.getUsage() == VertexFormatElement.Usage.NORMAL) renderNormal(bb);
                else if (vfe.getUsage() == VertexFormatElement.Usage.COLOR) renderColour(bb);
                else if (vfe.getUsage() == VertexFormatElement.Usage.UV) {
                    if (vfe.getIndex() == 0) renderTex(bb);
                    else if (vfe.getIndex() == 1) renderLightMap(bb);
                }
            }
            bb.endVertex();
        }
    }

    /** Renders this vertex into the given {@link BufferBuilder}, assuming that the {@link VertexFormat} is
     * {@link DefaultVertexFormats#BLOCK}.
     * <p>
     * Slight performance increase over {@link #render(BufferBuilder)}. */
    void renderAsBlock(BufferBuilder bb) {
        renderPosition(bb);
        renderColour(bb);
        renderTex(bb);
        renderLightMap(bb);
        bb.endVertex();
    }

    void renderPosition(BufferBuilder bb) {
        bb.pos(position_x, position_y, position_z);
    }

    void renderNormal(BufferBuilder bb) {
        bb.normal(normal_x, normal_y, normal_z);
    }

    void renderColour(BufferBuilder bb) {
        bb.color(colour_r, colour_g, colour_b, colour_a);
    }

    void renderTex(BufferBuilder bb) {
        bb.tex(tex_u, tex_v);
    }

    void renderTex(BufferBuilder bb, ISprite sprite) {
        bb.tex(sprite.getInterpU(tex_u), sprite.getInterpV(tex_v));
    }

    void renderLightMap(BufferBuilder bb) {
        bb.lightmap(light_sky << 4, light_block << 4);
    }

    // Mutating

    MutableVertex positionv(Tuple vec) {
        return positionf(vec.x, vec.y, vec.z);
    }

    MutableVertex positiond(double x, double y, double z) {
        return positionf((float) x, (float) y, (float) z);
    }

    MutableVertex positionf(float x, float y, float z) {
        position_x = x;
        position_y = y;
        position_z = z;
        Point
        return this;
    }

    Point3f positionvf() {
        return new Point3f(position_x, position_y, position_z);
    }

    /** Sets the current normal for this vertex based off the given vector.<br>
     * Note: This calls {@link #normalf(float, float, float)} internally, so refer to that for more warnings.
     * 
     * @see #normalf(float, float, float) */
    MutableVertex normalv(Tuple vec) {
        return normalf(vec.x, vec.y, vec.z);
    }

    /** Sets the current normal given the x, y, and z coordinates. These are NOT normalised or checked. */
    MutableVertex normalf(float x, float y, float z) {
        normal_x = x;
        normal_y = y;
        normal_z = z;
        return this;
    }

    MutableVertex normali(int combined) {
        normal_x = ((combined >> 0) & 0xFF) / 0x7f;
        normal_y = ((combined >> 8) & 0xFF) / 0x7f;
        normal_z = ((combined >> 16) & 0xFF) / 0x7f;
        return this;
    }

    MutableVertex invertNormal() {
        return normalf(-normal_x, -normal_y, -normal_z);
    }

    /** @return The current normal vector of this vertex. This might be normalised. */
    Vector3f normal() {
        return new Vector3f(normal_x, normal_y, normal_z);
    }

    int normalToPackedInt() {
        return normalAsByte(normal_x, 0) | normalAsByte(normal_y, 8) | normalAsByte(normal_z, 16);
    }

    private static int normalAsByte(float norm, int offset) {
        int a = (int) (norm * 0x7f);
        return a << offset;
    }

    MutableVertex colourv(Tuple4f vec) {
        return colourf(vec.x, vec.y, vec.z, vec.w);
    }

    MutableVertex colourf(float r, float g, float b, float a) {
        return colouri((int) (r * 0xFF), (int) (g * 0xFF), (int) (b * 0xFF), (int) (a * 0xFF));
    }

    MutableVertex colouri(int rgba) {
        return colouri(rgba, rgba >> 8, rgba >> 16, rgba >>> 24);
    }

    MutableVertex colouri(int r, int g, int b, int a) {
        colour_r = (short) (r & 0xFF);
        colour_g = (short) (g & 0xFF);
        colour_b = (short) (b & 0xFF);
        colour_a = (short) (a & 0xFF);
        return this;
    }

    Point4f colourv() {
        return new Point4f(colour_r / 255f as float, colour_g / 255f as float, colour_b / 255f as float, colour_a / 255f as float);
    }

    int colourRGBA() {
        int rgba = 0;
        rgba |= (colour_r & 0xFF) << 0;
        rgba |= (colour_g & 0xFF) << 8;
        rgba |= (colour_b & 0xFF) << 16;
        rgba |= (colour_a & 0xFF) << 24;
        return rgba;
    }

    int colourABGR() {
        int rgba = 0;
        rgba |= (colour_r & 0xFF) << 24;
        rgba |= (colour_g & 0xFF) << 16;
        rgba |= (colour_b & 0xFF) << 8;
        rgba |= (colour_a & 0xFF) << 0;
        return rgba;
    }

    MutableVertex multColourd(double d) {
        int m = (int) (d * 255);
        return multColouri(m);
    }

    MutableVertex multColourd(double r, double g, double b, double a) {
        return multColouri((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
    }

    MutableVertex multColouri(int by) {
        return multColouri(by, by, by, 255);
    }

    MutableVertex multColouri(int r, int g, int b, int a) {
        colour_r = (short) (colour_r * r / 255);
        colour_g = (short) (colour_g * g / 255);
        colour_b = (short) (colour_b * b / 255);
        colour_a = (short) (colour_a * a / 255);
        return this;
    }

    /** Multiplies the colour by {@link MutableQuad#diffuseLight(float, float, float)} for the normal. */
    MutableVertex multShade() {
        return multColourd(MutableQuad.diffuseLight(normal_x, normal_y, normal_z));
    }

    MutableVertex texFromSprite(TextureAtlasSprite sprite) {
        tex_u = sprite.getInterpolatedU(tex_u * 16);
        tex_v = sprite.getInterpolatedV(tex_v * 16);
        return this;
    }

    MutableVertex texv(Tuple2f vec) {
        return texf(vec.x, vec.y);
    }

    MutableVertex texf(float u, float v) {
        tex_u = u;
        tex_v = v;
        return this;
    }

    Point2f tex() {
        return new Point2f(tex_u, tex_v);
    }

    MutableVertex lightv(Tuple2f vec) {
        return lightf(vec.x, vec.y);
    }

    MutableVertex lightf(float block, float sky) {
        return lighti((int) (block * 0xF), (int) (sky * 0xF));
    }

    MutableVertex lighti(int combined) {
        return lighti(combined >> 4, combined >> 20);
    }

    MutableVertex lighti(int block, int sky) {
        light_block = (byte) block;
        light_sky = (byte) sky;
        return this;
    }

    MutableVertex maxLighti(int block, int sky) {
        return lighti(Math.max(block, light_block), Math.max(sky, light_sky));
    }

    Point2f lightvf() {
        return new Point2f(light_block * 15f as float, light_sky * 15f as float);
    }

    int lightc() {
        return light_block << 4 + light_sky << 20;
    }

    int[] lighti() {
        return [light_block, light_sky ];
    }

    MutableVertex transform(Matrix4f matrix) {
        Point3f point = positionvf();
        matrix.transform(point);
        positionv(point);

        Vector3f normal = normal();
        matrix.transform(normal);
        normalv(normal);
        return this;
    }

    MutableVertex translatei(int x, int y, int z) {
        position_x += x;
        position_y += y;
        position_z += z;
        return this;
    }

    MutableVertex translatef(float x, float y, float z) {
        position_x += x;
        position_y += y;
        position_z += z;
        return this;
    }

    MutableVertex translated(double x, double y, double z) {
        position_x += x;
        position_y += y;
        position_z += z;
        return this;
    }

    MutableVertex translatevi(Vector3i vec) {
        return translatei(vec.getX(), vec.getY(), vec.getZ());
    }

    MutableVertex translatevd(Vector3d vec) {
        return translated(vec.x, vec.y, vec.z);
    }

    MutableVertex scalef(float scale) {
        position_x *= scale;
        position_y *= scale;
        position_z *= scale;
        return this;
    }

    MutableVertex scaled(double scale) {
        return scalef((float) scale);
    }

    MutableVertex scalef(float x, float y, float z) {
        position_x *= x;
        position_y *= y;
        position_z *= z;
        // TODO: scale normals?
        return this;
    }

    MutableVertex scaled(double x, double y, double z) {
        return scalef((float) x, (float) y, (float) z);
    }

    /** Rotates around the X axis by angle. */
    void rotateX(float angle) {
        float cos = MathHelper.cos(angle);
        float sin = MathHelper.sin(angle);
        rotateDirectlyX(cos, sin);
    }

    /** Rotates around the Y axis by angle. */
    void rotateY(float angle) {
        float cos = MathHelper.cos(angle);
        float sin = MathHelper.sin(angle);
        rotateDirectlyY(cos, sin);
    }

    /** Rotates around the Z axis by angle. */
    void rotateZ(float angle) {
        float cos = MathHelper.cos(angle);
        float sin = MathHelper.sin(angle);
        rotateDirectlyZ(cos, sin);
    }

    void rotateDirectlyX(float cos, float sin) {
        float y = position_y;
        float z = position_z;
        position_y = (float) (y * cos - z * sin);
        position_z = (float) (y * sin + z * cos);
    }

    void rotateDirectlyY(float cos, float sin) {
        float x = position_x;
        float z = position_z;
        position_x = (float) (x * cos - z * sin);
        position_z = (float) (x * sin + z * cos);
    }

    void rotateDirectlyZ(float cos, float sin) {
        float x = position_x;
        float y = position_y;
        position_x = (float) (x * cos + y * sin);
        position_y = (float) (x * -sin + y * cos);
    }

    /** Rotates this vertex around the X axis 90 degrees.
     * 
     * @param scale The multiplier for scaling. Positive values will rotate clockwise, negative values rotate
     *            anti-clockwise. */
    MutableVertex rotateX_90(float scale) {
        float ym = scale;
        float zm = -ym;

        float t = (float) (position_y * ym);
        position_y = (float) (position_z * zm);
        position_z = t;

        t = (float) (normal_y * ym);
        normal_y = (float) (normal_z * zm);
        normal_z = t;
        return this;
    }

    /** Rotates this vertex around the Y axis 90 degrees.
     * 
     * @param scale The multiplier for scaling. Positive values will rotate clockwise, negative values rotate
     *            anti-clockwise. */
    MutableVertex rotateY_90(float scale) {
        float xm = scale;
        float zm = -xm;

        float t = (float) (position_x * xm);
        position_x = (float) (position_z * zm);
        position_z = t;

        t = (float) (normal_x * xm);
        normal_x = (float) (normal_z * zm);
        normal_z = t;
        return this;
    }

    /** Rotates this vertex around the Z axis 90 degrees.
     * 
     * @param scale The multiplier for scaling. Positive values will rotate clockwise, negative values rotate
     *            anti-clockwise. */
    MutableVertex rotateZ_90(float scale) {
        float xm = scale;
        float ym = -xm;

        float t = (float) (position_x * xm);
        position_x = (float) (position_y * ym);
        position_y = t;

        t = (float) (normal_x * xm);
        normal_x = (float) (normal_y * ym);
        normal_y = t;
        return this;
    }

    /** Rotates this vertex around the X axis by 180 degrees. */
    MutableVertex rotateX_180() {
        position_y = -position_y;
        position_z = -position_z;
        normal_y = -normal_y;
        normal_z = -normal_z;
        return this;
    }

    /** Rotates this vertex around the Y axis by 180 degrees. */
    MutableVertex rotateY_180() {
        position_x = -position_x;
        position_z = -position_z;
        normal_x = -normal_x;
        normal_z = -normal_z;
        return this;
    }

    /** Rotates this vertex around the Z axis by 180 degrees. */
    MutableVertex rotateZ_180() {
        position_x = -position_x;
        position_y = -position_y;
        normal_x = -normal_x;
        normal_y = -normal_y;
        return this;
    }
}
