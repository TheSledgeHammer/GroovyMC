/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: All Static Inner Classes are referenced or altered from BC's JsonModelRule
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableFaceUV
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableModel
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.MathHelper

//Work In Progress
abstract class JsonRule {

    private static GroovysonObject groovysonObject;
    final VariableBoolean when;

    JsonRule(VariableBoolean when) {
        this.when = when;
    }

    static def getRules() {
        return groovysonObject.getRules();
    }

    static def getWhen() {
        return groovysonObject.getRulesByName("when");
    }

    static def getType() {
        return groovysonObject.getRulesByName("type");
    }

    static def getFrom() {
        return groovysonObject.getRulesByName("from");
    }

    static def getTo() {
        return groovysonObject.getRulesByName("to");
    }

    static def getOrigin() {
        return groovysonObject.getRulesByName("origin");
    }

    static def getAngle() {
        return groovysonObject.getRulesByName("angle");
    }

    static def getScale() {
        return groovysonObject.getRulesByName("scale");
    }

    static JsonRule SetRules(GroovysonObject groovysonObject) {
        this.groovysonObject = groovysonObject;

        String when = getWhen();
        VariableBoolean nodeWhen = new VariableBoolean(Boolean.valueOf(when));

        String builtin = getType();
        if(StringTools.contains(builtin, "rotate_facing")) {
            String from = getFrom();
            String fromValue = StringTools.stringToEnum(from, EnumFacing.class).toUpperCase();
            VariableObject<EnumFacing> nodeFrom = new VariableObject<>();
            nodeFrom.setValue(EnumFacing.valueOf(fromValue));

            String to = getTo();
            String toValue = StringTools.stringToEnum(to, EnumFacing.class).toUpperCase();
            VariableObject<EnumFacing> nodeTo = new VariableObject<>();
            nodeTo.setValue(EnumFacing.valueOf(toValue));

            //Fix Origin is incomplete
            VariableDouble[] origin = RotateFacing.DEFAULT_ORIGIN;
            return new RotateFacing(nodeWhen, nodeFrom, nodeTo, origin);

        } else if(StringTools.contains(builtin, "rotate")) {

            //Fix Origin is incomplete
            VariableDouble[] origin = Rotate.DEFAULT_ORIGIN

            VariableDouble[] angle = new VariableDouble[getAngle()];
            return new Rotate(nodeWhen, origin, angle);

        } else if(StringTools.contains(builtin, "scale")) {

            //Fix Origin is incomplete
            VariableDouble[] origin = Scale.DEFAULT_ORIGIN

            VariableDouble[] scale = new VariableDouble[getScale()];
            return new Scale(nodeWhen, origin, scale);

        } else {
            throw new Exception("Unknown built in rule type ${builtin}");
        }
    }

    abstract void apply(List<MutableQuad> quads);

    static class RotateFacing extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(8);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableObject<EnumFacing> from;
        protected final VariableObject<EnumFacing> to;
        protected final VariableDouble[] origin;

        RotateFacing(VariableBoolean when, VariableObject<EnumFacing> from, VariableObject<EnumFacing> to, VariableDouble[] origin) {
            super(when);
            this.from = from;
            this.to = to;
            this.origin = origin;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            EnumFacing faceFrom = from.getValue();//EnumFacing.valueOf(ListTools.removeBrackets(from));
            EnumFacing faceTo = to.getValue();//EnumFacing.valueOf(ListTools.removeBrackets(to));

            if(faceFrom == faceTo) {
                return;
            }

            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            for(MutableQuad q : quads) {
                print q.rotate(faceFrom, faceTo, ox, oy, oz);

            }
        }
    }

    static class Rotate extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(0.5);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableDouble[] origin;
        protected final VariableDouble[] angle;

        Rotate(VariableBoolean when, VariableDouble[] origin, VariableDouble[] angle) {
            super(when);
            this.origin = origin;
            this.angle = angle;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            float ax = Math.toRadians(angle[0].getValue()) as float;
            float ay = Math.toRadians(angle[1].getValue()) as float;
            float az = Math.toRadians(angle[2].getValue()) as float;

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

    static class Scale extends JsonRule {

        private static final VariableDouble CONST_ORIGIN = new VariableDouble(0.5);
        static final VariableDouble[] DEFAULT_ORIGIN = [CONST_ORIGIN, CONST_ORIGIN, CONST_ORIGIN];

        protected final VariableDouble[] origin;
        protected final VariableDouble[] scale;

        Scale(VariableBoolean when, VariableDouble[] origin, VariableDouble[] scale) {
            super(when);
            this.origin = origin;
            this.scale = scale;
        }

        @Override
        void apply(List<MutableQuad> quads) {
            float ox = origin[0].getValue() / 16f as float;
            float oy = origin[1].getValue() / 16f as float;
            float oz = origin[2].getValue() / 16f as float;

            float sx = scale[0].getValue() as float;
            float sy = scale[1].getValue() as float;
            float sz = scale[2].getValue() as float;

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
