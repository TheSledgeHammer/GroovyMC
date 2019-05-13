/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: All Static Inner Classes are referenced or altered from BC's JsonModelRule
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.MathHelper

//TODO:
//Methods Need Refinement for before it can be implemented for how these rules apply
//Work on caching variables & processing variables when said rules apply

class JsonRule {

    private GroovysonObject groovysonObject;

    JsonRule(GroovysonObject groovysonObject) {
        this.groovysonObject = groovysonObject;
    }

    def getRules() {
        return groovysonObject.getRules();
    }

    def getWhen() {
        return groovysonObject.getRulesByName("when");
    }

    def getType() {
        return groovysonObject.getRulesByName("type");
    }

    def getFrom() {
        return groovysonObject.getRulesByName("from");
    }

    def getTo() {
        return groovysonObject.getRulesByName("to");
    }

    def getOrigin() {
        return groovysonObject.getRulesByName("origin");
    }

    def getAngle() {
        return groovysonObject.getRulesByName("angle");
    }

    def getScale() {
        return groovysonObject.getRulesByName("scale");
    }

    static class RotateFacing {

        final String from;
        final String to;
        final double[] origin;

        RotateFacing(String from, String to, double[] origin) {
            this.from = from;
            this.to = to;
            this.origin = origin;
        }

        void apply(List<MutableQuad> quads) {
            EnumFacing faceFrom = EnumFacing.valueOf(ListTools.removeBrackets(from));
            EnumFacing faceTo = EnumFacing.valueOf(ListTools.removeBrackets(to));

            if(faceFrom == faceTo) {
                return;
            }

            float ox = origin[0] / 16f;
            float oy = origin[1] / 16f;
            float oz = origin[2] / 16f;

            for(MutableQuad q : quads) {
                print q.rotate(faceFrom, faceTo, ox, oy, oz);

            }
        }
    }

    static class Rotate {

        final double[] origin;
        final double[] angle;

        Rotate(double[] origin, double[] angle) {
            this.origin = origin;
            this.angle = angle;
        }

        void apply(List<MutableQuad> quads) {
            float ox = origin[0] / 16f;
            float oy = origin[1] / 16f;
            float oz = origin[2] / 16f;

            float ax = Math.toRadians(angle[0]) as float;
            float ay = Math.toRadians(angle[1]) as float;
            float az = Math.toRadians(angle[2]) as float;

            if (ax == 0 && ay == 0 && az == 0) {
                return;
            }

            float cx = MathHelper.cos(ax);
            float cy = MathHelper.cos(ay);
            float cz = MathHelper.cos(az);

            float sx = MathHelper.sin(ax);
            float sy = MathHelper.sin(ay);
            float sz = MathHelper.sin(az);

            for(MutableQuad q : quads) {
                q.translatef(-ox, -oy, -oz);
                if(cx != 1) {
                    q.rotateDirectlyX(cx, sx);
                }
                if(cy != 1) {
                    q.rotateDirectlyY(cy, sy);
                }
                if(cz != 1) {
                    q.rotateDirectlyZ(cz, sz);
                }
                q.translatef(ox, oy, oz);
            }
        }
    }

    static class Scale {
        final double[] origin;
        final double[] scale;

        Scale(double[] origin, double[] scale) {
            this.origin = origin;
            this.scale = scale;
        }

        void apply(List<MutableQuad> quads) {
            float ox = origin[0] / 16f;
            float oy = origin[1] / 16f;
            float oz = origin[2] / 16f;

            float sx = scale[0] as float;
            float sy = scale[1] as float;
            float sz = scale[2] as float;

            if(sx == 1 && sy == 1 && sz == 1) {
                return;
            }

            for(MutableQuad q : quads) {
                q.translatef(-ox, -oy, -oz);
                q.scalef(sx, sy, sz);
                q.translatef(ox, oy, oz);
            }
        }
    }
}
