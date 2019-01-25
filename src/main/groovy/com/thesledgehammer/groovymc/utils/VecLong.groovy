/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.utils;

/** An immutable class the represents a 4 part vector of longs. It is *highly* recommended that you have a way to
 * convert this to a more specific implementation (for example another library provided Vector3i class) */
class VecLong {
    static final VecLong ZERO = new VecLong(0, 0, 0, 0);

    final long a, b, c, d;

    VecLong(long a) {
        this(a, 0, 0, 0);
    }

    VecLong(long a, long b) {
        this(a, b, 0, 0);
    }

    VecLong(long a, long b, long c) {
        this(a, b, c, 0);
    }

    VecLong(long a, long b, long c, long d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    VecLong add(long a_, long b_, long c_, long d_) {
        return new VecLong(a + a_, b + b_, c + c_, d + d_);
    }

    VecLong sub(long a_, long b_, long c_, long d_) {
        return new VecLong(a - a_, b - b_, c - c_, d - d_);
    }

    VecLong scale(long a_, long b_, long c_, long d_) {
        return new VecLong(a * a_, b * b_, c * c_, d * d_);
    }

    VecLong div(long a_, long b_, long c_, long d_) {
        return new VecLong(a / a_, b / b_, c / c_, d / d_);
    }

    VecLong add(VecLong w) {
        return new VecLong(a + w.a, b + w.b, c + w.c, d + w.d);
    }

    VecLong sub(VecLong neg) {
        return new VecLong(a - neg.a, b - neg.b, c - neg.c, d - neg.d);
    }

    VecLong scale(VecLong s) {
        return new VecLong(a * s.a, b * s.b, c * s.c, d * s.d);
    }

    VecLong div(VecLong s) {
        return new VecLong(a / s.a, b / s.b, c / s.c, d / s.d);
    }

    long dotProduct2(VecLong w) {
        return a * w.a + b * w.b;
    }

    long dotProduct3(VecLong w) {
        return a * w.a + b * w.b + c * w.c;
    }

    long dotProduct4(VecLong w) {
        return a * w.a + b * w.b + c * w.c + d * w.d;
    }

    double length() {
        return Math.sqrt(a * a + b * b + c * c + d * d);
    }

    VecLong crossProduct(VecLong w) {
        long x = b * w.c - c * w.b;
        long y = c * w.b - a * w.c;
        long z = a * w.b - b * w.a;
        return new VecLong(x, y, z, 1);
    }

    double distance(VecLong to) {
        long da = a - to.a;
        long db = b - to.b;
        long dc = c - to.c;
        long dd = d - to.d;
        return Math.sqrt(da * da + db * db + dc * dc + dd * dd);
    }

    VecDouble castToDouble() {
        return new VecDouble(a, b, c, d);
    }

    @Override
    String toString() {
        return "{ " + a + ", " + b + ", " + c + ", " + d + " }";
    }
}
