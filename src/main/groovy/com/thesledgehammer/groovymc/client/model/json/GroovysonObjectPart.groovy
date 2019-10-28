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

package com.thesledgehammer.groovymc.client.model.json

import groovy.json.JsonException
import net.minecraft.util.Direction

class GroovysonObjectPart {

    private GroovysonObject groovysonObject;
    private def part; //raw Json Model Elements if applicable

    GroovysonObjectPart(GroovysonObject groovysonObject, String index) {
        this.groovysonObject = groovysonObject;
        this.part = groovysonObject.elements.getAt(index)
    }

    String getPartName() {
        return part.name;
    }

    ArrayList<Float> From() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.from.size; i++) {
            arrPart.add(i, part.from.get(i));
        }
        return arrPart;
    }

    ArrayList<Float> To() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.to.size; i++) {
            arrPart.add(i, part.to.get(i));
        }
        return arrPart;
    }

    def Rotation() {
        return part.rotation;
    }

    ArrayList<Float> RotationOrigin() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.origin.size; i++) {
            arrPart.add(i, part.rotation.origin.get(i));
        }
        return arrPart;
    }

    ArrayList<Float> RotationAxis() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.axis.size; i++) {
            arrPart.add(i, part.rotation.axis.get(i));
        }
        return arrPart;
    }

    ArrayList<Float> RotationAngle() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.rotation.angle.size; i++) {
            arrPart.add(i, part.rotation.angle.get(i));
        }
        return arrPart;
    }

    boolean RotationRescale() {
        return part.rotation.rescale;
    }

    boolean Shade() {
        return part.shade;
    }

    def Faces() {
        return part.faces;
    }

    def Facing(Direction face) {
        String faces = face.getName().toLowerCase();
        if(part.faces.get(faces) == null) {
            throw new JsonException("Current Face does not exist...!");
        }
        return part.faces.get(faces);
    }

    ArrayList<Float> FacingUv(Direction face) {
        ArrayList<Float> arrPart = new ArrayList<>();
        def uvFace = null;
        if(Facing(face) != null) {
            uvFace = Facing(face);
        }
        if(uvFace != null) {
            for(int i = 0; i < uvFace.uv.size; i++) {
                arrPart.add(i, uvFace.uv.get(i));
            }
        }
        return arrPart;
    }

    def TextureFace(Direction face) {
        return Facing(face).texture;
    }

    def CullFaceFace(Direction face) {
        return Facing(face).cullface
    }

    def FacingRotation(Direction face, int fallback) {
        if(Facing(face).rotation == null) {
            return fallback;
        }
        return Facing(face).rotation;
    }

    def FacingTint(Direction face, int fallback) {
        if(Facing(face).tintindex == null || Facing(face) == null) {
            return fallback;
        }
        return Facing(face).tintindex;
    }

    //Refers to BlockRenderLayer: I.e. Cutout, Translucent, Cutout_Mipped, etc...
    String BlockRenderType() {
        return part.render;
    }

    //Refers to BlockRenderLayer: I.e. Cutout, Translucent, Cutout_Mipped, etc...
    def BlockRenderType(String renderType) {
        String render = renderType.toLowerCase();
        if (part.render.get(render) == null) {
            throw new JsonException("The element does not contain a render of: ${renderType}");
        }
        return part.render.get(render);
    }

    def Light() {
        return part.light;
    }

    def Colour() {
        return part.colour;
    }

    def Visible() {
        return part.visible;
    }

    def Invert() {
        return part.invert;
    }

    def BothSides() {
        return part.bothsides;
    }
}
