/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumFacing.Axis
import net.minecraft.util.EnumFacing.AxisDirection

import javax.vecmath.Point3f
import javax.vecmath.Tuple3f
import javax.vecmath.Vector3f

/** Provides various utilities for creating {@link MutableQuad} out of various position information, such as a single
 * face of a cuboid. */
class ModelUtil {
    /** Mutable class for holding the current {@link #minU}, {@link #maxU}, {@link #minV} and {@link #maxV} of a
     * face. */
    static class UvFaceData {
        private static final UvFaceData DEFAULT = new UvFaceData(0, 0, 1, 1);

        public float minU, maxU, minV, maxV;

        UvFaceData() {}

        UvFaceData(UvFaceData from) {
            this.minU = from.minU;
            this.maxU = from.maxU;
            this.minV = from.minV;
            this.maxV = from.maxV;
        }

        static UvFaceData from16(double minU, double minV, double maxU, double maxV) {
            return new UvFaceData(minU / 16.0, minV / 16.0, maxU / 16.0, maxV / 16.0);
        }

        static UvFaceData from16(int minU, int minV, int maxU, int maxV) {
            return new UvFaceData(minU / 16f, minV / 16f, maxU / 16f, maxV / 16f);
        }

        UvFaceData(float uMin, float vMin, float uMax, float vMax) {
            this.minU = uMin;
            this.maxU = uMax;
            this.minV = vMin;
            this.maxV = vMax;
        }

        UvFaceData(double minU, double minV, double maxU, double maxV) {
            this((float) minU, (float) minV, (float) maxU, (float) maxV);
        }

        UvFaceData andSub(UvFaceData sub) {
            float size_u = (float) (maxU - minU);
            float size_v = (float) (maxV - minV);

            float min_u = (float) (minU + sub.minU * size_u);
            float min_v = (float) (minV + sub.minV * size_v);
            float max_u = (float) (minU + sub.maxU * size_u);
            float max_v = (float) (minV + sub.maxV * size_v);

            return new UvFaceData(min_u, min_v, max_u, max_v);
        }

        UvFaceData inParent(UvFaceData parent) {
            return parent.andSub(this);
        }

        @Override
        String toString() {
            return "[ " + minU * 16 + ", " + minV * 16 + ", " + maxU * 16 + ", " + maxV * 16 + " ]";
        }
    }

    static class TexturedFace {
        public TextureAtlasSprite sprite;
        public UvFaceData faceData = new UvFaceData();
    }

    static MutableQuad createFace(EnumFacing face, Tuple3f a, Tuple3f b, Tuple3f c, Tuple3f d, UvFaceData uvs) {
        MutableQuad quad = new MutableQuad(-1, face);
        if (uvs == null) {
            uvs = UvFaceData.DEFAULT;
        }
        if (face == null || shouldInvertForRender(face)) {
            quad.vertex_0.positionv(a).texf(uvs.minU, uvs.minV);
            quad.vertex_1.positionv(b).texf(uvs.minU, uvs.maxV);
            quad.vertex_2.positionv(c).texf(uvs.maxU, uvs.maxV);
            quad.vertex_3.positionv(d).texf(uvs.maxU, uvs.minV);
        } else {
            quad.vertex_3.positionv(a).texf(uvs.minU, uvs.minV);
            quad.vertex_2.positionv(b).texf(uvs.minU, uvs.maxV);
            quad.vertex_1.positionv(c).texf(uvs.maxU, uvs.maxV);
            quad.vertex_0.positionv(d).texf(uvs.maxU, uvs.minV);
        }
        return quad;
    }

    static <T extends Tuple3f> MutableQuad createFace(EnumFacing face, T[] points, UvFaceData uvs) {
        return createFace(face, points[0], points[1], points[2], points[3], uvs);
    }

    static MutableQuad createFace(EnumFacing face, Tuple3f center, Tuple3f radius, UvFaceData uvs) {
        Point3f[] points = getPointsForFace(face, center, radius);
        return createFace(face, points, uvs).normalf(face.getFrontOffsetX(), face.getFrontOffsetY(), face.getFrontOffsetZ());
    }

    static MutableQuad createInverseFace(EnumFacing face, Tuple3f center, Tuple3f radius, UvFaceData uvs) {
        return createFace(face, center, radius, uvs).copyAndInvertNormal();
    }

    static MutableQuad[] createDoubleFace(EnumFacing face, Tuple3f center, Tuple3f radius, UvFaceData uvs) {
        MutableQuad norm = createFace(face, center, radius, uvs);
        return [norm, norm.copyAndInvertNormal()];
    }

    static Point3f[] getPointsForFace(EnumFacing face, Tuple3f center, Tuple3f radius) {
        Point3f centerOfFace = new Point3f(center);
        Point3f faceAdd = new Point3f(face.frontOffsetX * radius.x as float, face.getFrontOffsetY() * radius.y as float, face.getFrontOffsetZ() * radius.z as float);
        centerOfFace.add(faceAdd);
        Vector3f faceRadius = new Vector3f(radius);
        if (face.getAxisDirection() == AxisDirection.POSITIVE) {
            faceRadius.sub(faceAdd);
        } else {
            faceRadius.add(faceAdd);
        }
        return getPoints(centerOfFace, faceRadius);
    }

    static Point3f[] getPoints(Point3f centerFace, Tuple3f faceRadius) {
        ArrayList<Point3f> arr = new ArrayList<>();
        arr.add(new Point3f(centerFace));
        arr.add(new Point3f(centerFace));
        arr.add(new Point3f(centerFace));
        arr.add(new Point3f(centerFace));
        Point3f[] array = arr;
        array[0].add(addOrNegate(faceRadius, false, false));
        array[1].add(addOrNegate(faceRadius, false, true));
        array[2].add(addOrNegate(faceRadius, true, true));
        array[3].add(addOrNegate(faceRadius, true, false));
        return array;
    }

    static Vector3f addOrNegate(Tuple3f coord, boolean u, boolean v) {
        boolean zisv = coord.x != 0 && coord.y == 0;
        float x = coord.x * (u ? 1 : -1);
        float y = coord.y * (v ? -1 : 1);
        float z = coord.z * (zisv ? (v ? -1 : 1) : (u ? 1 : -1));
        Vector3f neg = new Vector3f(x, y, z);
        return neg;
    }

    static boolean shouldInvertForRender(EnumFacing face) {
        boolean flip = face.getAxisDirection() == AxisDirection.NEGATIVE;
        if (face.getAxis() == Axis.Z) flip = !flip;
        return flip;
    }

    static EnumFacing faceForRender(EnumFacing face) {
        if (shouldInvertForRender(face)) return face.getOpposite();
        return face;
    }
}
