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

import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.util.EnumFacing

class GroovysonObjectPart {

    private GroovysonObject groovysonObject;
    private def part; //raw Json Model Elements if applicable

    GroovysonObjectPart() {

    }

    GroovysonObjectPart(GroovysonObject groovysonObject) {
        setGroovysonObject(groovysonObject);
    }

    GroovysonObjectPart(GroovysonObject groovysonObject, int index) {
        setGroovysonObject(groovysonObject);
        setPart(index);
    }

    GroovysonObjectPart(GroovysonObject groovysonObject, String name) {
        setGroovysonObject(groovysonObject);
        setPartByName(name);
    }

    void setGroovysonObject(GroovysonObject groovysonObject) {
        this.groovysonObject = groovysonObject;
    }

    //Refers to def part
    private void setPart(int index) {
        part = groovysonObject.getElementPart(index);
    }

    void setPartByName(String name) {
        int k = 0;
        int idx = 0;
        for(int i = 0; i < groovysonObject.getElements().size; i++) {
            if(name.equals(groovysonObject.getElementPart(i).name)) {
                setPart(i);
                idx = i;
            }
        }
        if(!name.equals(groovysonObject.getElementPart(idx).name)) {
            Log.logError("${groovysonObject.getName()} model does not contain an element named: ${name}");
        }
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

    def Facing(EnumFacing face) {
        String faces = face.getName().toLowerCase();
        if(part.faces.get(faces) == null) {
           // Log.logError("Current Face does not exist...!");
            return null;
        }
        return part.faces.get(faces);
    }

    ArrayList<Float> FacingUv(EnumFacing face) {
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

    def TextureFace(EnumFacing face) {
        return Facing(face).texture;
    }

    def CullFaceFace(EnumFacing face) {
        return Facing(face).cullface
    }

    def FacingRotation(EnumFacing face, int fallback) {
        if(Facing(face).rotation == null) {
            return fallback;
        }
        return Facing(face).rotation;
    }

    def FacingTint(EnumFacing face, int fallback) {
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
            Log.logError("The element does not contain a render of: ${renderType}");
            return null;
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
}
