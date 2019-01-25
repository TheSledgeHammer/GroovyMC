/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */
package com.thesledgehammer.groovymc.utils;

/** An immutable class the represents a 4 part vector of doubles. It is *highly* recommended that you have a way to
 * convert this to a more specific implementation (for example another library provided Vector3d class) */
class VecDouble {
    static final VecDouble ZERO = new VecDouble(0, 0, 0, 0);

    final double a, b, c, d;

    VecDouble(double a) {
        this(a, 0, 0, 0);
    }

    VecDouble(double a, double b) {
        this(a, b, 0, 0);
    }

    VecDouble(double a, double b, double c) {
        this(a, b, c, 0);
    }

    VecDouble(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    VecDouble add(double a_, double b_, double c_, double d_) {
        return new VecDouble(a + a_, b + b_, c + c_, d + d_);
    }

    VecDouble sub(double a_, double b_, double c_, double d_) {
        return new VecDouble(a - a_, b - b_, c - c_, d - d_);
    }

    VecDouble scale(double a_, double b_, double c_, double d_) {
        return new VecDouble(a * a_, b * b_, c * c_, d * d_);
    }

    VecDouble div(double a_, double b_, double c_, double d_) {
        return new VecDouble(a / a_, b / b_, c / c_, d / d_);
    }

    VecDouble add(VecDouble w) {
        return new VecDouble(a + w.a, b + w.b, c + w.c, d + w.d);
    }

    VecDouble sub(VecDouble w) {
        return new VecDouble(a - w.a, b - w.b, c - w.c, d - w.d);
    }

    VecDouble scale(VecDouble w) {
        return new VecDouble(a * w.a, b * w.b, c * w.c, d * w.d);
    }

    VecDouble div(VecDouble w) {
        return new VecDouble(a / w.a, b / w.b, c / w.c, d / w.d);
    }

    VecDouble normalize() {
        double sqrt = Math.sqrt(a * a + b * b + c * c + d * d);
        return sqrt < 1.0E-4D ? ZERO : new VecDouble(a / sqrt, b / sqrt, c / sqrt, d / sqrt);
    }

    double length() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    double distance(VecDouble to) {
        double da = a - to.a;
        double db = b - to.b;
        double dc = c - to.c;
        double dd = d - to.d;
        return Math.sqrt(da * da + db * db + dc * dc + dd * dd);
    }

    double dotProduct2(VecDouble w) {
        return a * w.a + b * w.b;
    }

    double dotProduct3(VecDouble w) {
        return a * w.a + b * w.b + c * w.c;
    }

    double dotProduct4(VecDouble w) {
        return a * w.a + b * w.b + c * w.c + d * w.d;
    }

    VecDouble crossProduct(VecDouble w) {
        double x = b * w.c - c * w.b;
        double y = c * w.b - a * w.c;
        double z = a * w.b - b * w.a;
        return new VecDouble(x, y, z, 1);
    }

    VecLong roundToLong() {
        return new VecLong(Math.round(a), Math.round(b), Math.round(c), Math.round(d));
    }

    VecLong floorToLong() {
        return new VecLong((long) Math.floor(a), (long) Math.floor(b), (long) Math.floor(c), (long) Math.floor(d));
    }

    VecLong ceilToLong() {
        return new VecLong((long) Math.ceil(a), (long) Math.ceil(b), (long) Math.ceil(c), (long) Math.ceil(d));
    }
}
