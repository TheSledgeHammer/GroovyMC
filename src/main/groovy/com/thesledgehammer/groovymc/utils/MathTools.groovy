/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * This license pertains to the following Lines(99 - 115) of code.
 */

package com.thesledgehammer.groovymc.utils

class MathTools {

    static int swapARGBforABGR(int argb) {
        int a = (argb >>> 24) & 255;
        int r = (argb >> 16) & 255;
        int g = (argb >> 8) & 255;
        int b = (argb >> 0) & 255;
        return (a << 24) | (b << 16) | (g << 8) | r;
    }

<<<<<<< HEAD
    /**Below is Copied from BuildCraft
     * Please refer to the Copyright Header Above for Licencing
     **/
    static int swapARGBforABGR(int argb) {
        int a = (argb >>> 24) & 255;
        int r = (argb >> 16) & 255;
        int g = (argb >> 8) & 255;
        int b = (argb >> 0) & 255;
        return (a << 24) | (b << 16) | (g << 8) | r;
    }

=======
>>>>>>> 1.16.x
    static int clamp(int toClamp, int min, int max) {
        return Math.max(Math.min(toClamp, max), min);
    }

    static int clamp(double toClamp, int min, int max) {
        return clamp((int) toClamp, min, max);
    }

    static double clamp(double toClamp, double min, double max) {
        return Math.max(Math.min(toClamp, max), min);
    }

    static long clamp(long toClamp, long min, long max) {
        return Math.max(Math.min(toClamp, max), min);
    }
}
