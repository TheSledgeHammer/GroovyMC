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
package com.thesledgehammer.groovymc.client.definitions.json

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import net.minecraft.util.Direction

class GroovysonStaticKey implements IStaticKey {

    private final Map<GroovysonObjectPart, List<Double>> from = new HashMap<>();
    private final Map<GroovysonObjectPart, List<Double>> to = new HashMap<>();
    private final Table<GroovysonObjectPart, Direction, List<Double>> uv = HashBasedTable.create();
    private final Map<GroovysonObjectPart, Boolean> shade = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> visible = new HashMap<>();
    private final Map<GroovysonObjectPart, Long> colour = new HashMap<>();
    private final Map<GroovysonObjectPart, Long> light = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> invert = new HashMap<>();
    private final Map<GroovysonObjectPart, Boolean> bothSides = new HashMap<>();
    private final Table<GroovysonObjectPart, Direction, Long> textureRotation = HashBasedTable.create();
    private final Table<GroovysonObjectPart, Direction, String> texture = HashBasedTable.create();
    private final Map<GroovysonObjectPart, List<Double>> rotationAngle = new HashMap<>();
    private final Map<GroovysonObjectPart, List<Double>> rotationAxis = new HashMap<>();
    private final Map<GroovysonObjectPart, List<Double>> rotationOrigin = new HashMap<>();

    @Override
    void setFrom(GroovysonObjectPart part) {
        this.from.put(part, From(part));
    }

    @Override
    void setTo(GroovysonObjectPart part) {
        this.to.put(part, To(part));
    }

    @Override
    void setUV(GroovysonObjectPart part) {
        for(Direction face : Direction.values()) {
            if(part.Facing(face) != null) {
                this.uv.put(part, face, FaceUV(part, face));
            }
        }
    }

    @Override
    void setShade(GroovysonObjectPart part) {
        this.shade.put(part, Shade(part));
    }

    @Override
    void setVisible(GroovysonObjectPart part) {
        this.visible.put(part, Visible(part));
    }

    @Override
    void setColour(GroovysonObjectPart part) {
        this.colour.put(part, Long.valueOf(Colour(part)));
    }

    @Override
    void setLight(GroovysonObjectPart part) {
        this.light.put(part, Long.valueOf(Light(part)));
    }

    @Override
    void setTexture(GroovysonObjectPart part) {
        for(Direction face : Direction.values()) {
            if (face != null) {
                this.texture.put(part, face, TextureFace(part, face));
            }
        }
    }

    @Override
    void setRotationAngle(GroovysonObjectPart part) {
        this.rotationAxis.put(part, RotationAngle(part));
    }

    @Override
    void setRotationAxis(GroovysonObjectPart part) {
        this.rotationAxis.put(part, RotationAxis(part));
    }

    @Override
    void setRotationOrigin(GroovysonObjectPart part) {
        this.rotationAxis.put(part, RotationOrigin(part));
    }

    @Override
    List<Double> getFrom(GroovysonObjectPart part) {
        return from.get(part);
    }

    @Override
    List<Double> getTo(GroovysonObjectPart part) {
        return to.get(part);
    }

    @Override
    List<Double> getFaceUV(GroovysonObjectPart part, Direction face) {
        return uv.get(part, face);
    }

    @Override
    Boolean getShade(GroovysonObjectPart part) {
        return shade.get(part);
    }

    @Override
    Boolean getVisible(GroovysonObjectPart part) {
        return visible.get(part);
    }

    @Override
    Long getColour(GroovysonObjectPart part) {
        return colour.get(part);
    }

    @Override
    Long getLight(GroovysonObjectPart part) {
        return light.get(part);
    }

    @Override
    Boolean getInvert(GroovysonObjectPart part) {
        return invert.get(part);
    }

    @Override
    Boolean getBothSides(GroovysonObjectPart part) {
        return bothSides.get(part);
    }

    @Override
    String getTexture(GroovysonObjectPart part, Direction face) {
        return texture.get(part, face);
    }

    @Override
    List<Double> getRotationAngle(GroovysonObjectPart part) {
        return rotationAngle.get(part);
    }

    @Override
    List<Double> getRotationAxis(GroovysonObjectPart part) {
        return rotationAxis.get(part);
    }

    @Override
    List<Double> getRotationOrigin(GroovysonObjectPart part) {
        return rotationOrigin.get(part);
    }

    private static List<Double> From(GroovysonObjectPart part) {
        return part.From();
    }

    private static List<Double> To(GroovysonObjectPart part) {
        return part.To();
    }

    private static List<Double> FaceUV(GroovysonObjectPart part, Direction face) {
        return part.FacingUv(face);
    }

    private static List<Double> RotationAngle(GroovysonObjectPart part) {
        return part.RotationAngle();;
    }

    private static List<Double> RotationAxis(GroovysonObjectPart part) {
        return part.RotationAxis();
    }

    private static List<Double> RotationOrigin(GroovysonObjectPart part) {
        return part.RotationOrigin();
    }

    private static float FaceTint(GroovysonObjectPart part, Direction face, int fallback) {
        return part.FacingTint(face, fallback);
    }

    private static float FaceRotation(GroovysonObjectPart part, Direction face, int fallback) {
        return part.FacingRotation(face, fallback);
    }

    private static String TextureFace(GroovysonObjectPart part, Direction face) {
        return part.TextureFace(face);
    }

    private static boolean Shade(GroovysonObjectPart part) {
        return part.Shade()
    }

    private static boolean RotationRescale(GroovysonObjectPart part) {
        return part.RotationRescale();
    }

    private static String CullFace(GroovysonObjectPart part, Direction face) {
        return part.CullFaceFace(face);
    }

    private static String Light(GroovysonObjectPart part) {
        return part.Light()
    }

    private static boolean Visible(GroovysonObjectPart part) {
        return part.Visible();
    }

    private static String Colour(GroovysonObjectPart part) {
        return part.Colour();
    }
}
