/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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
    void setPart(int index) {
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
            Log.logError(groovysonObject.getName() + " model does not contain an element named: " + name);
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

    float[] from() {
        float[] from = From();
        return from;
    }

    ArrayList<Float> To() {
        ArrayList<Float> arrPart = new ArrayList<>();
        for(int i = 0; i < part.to.size; i++) {
            arrPart.add(i, part.to.get(i));
        }
        return arrPart;
    }

    float[] to() {
        float[] to = To();
        return to;
    }

    def Faces() {
        return part.faces;
    }

    def Facing(EnumFacing face) {
        String faces = face.getName().toLowerCase();
        if(part.faces.get(faces) == null) {
            Log.logError("Current Face does not exist...!");
            return null;
        }
        return part.faces.get(faces);
    }

    ArrayList<Float> FacingUv(EnumFacing face) {
        ArrayList<Float> arrPart = new ArrayList<>();
        if(Facing(face) == null) {
            return null;
        }
        for(int i = 0; i < Facing(face).uv.size; i++) {
            arrPart.add(i, Facing(face).uv.get(i));
        }
        return arrPart;
    }

    float[] FaceUV(EnumFacing face) {
        float[] uv = FacingUv(face);
        return uv;
    }

    String TextureFace(EnumFacing face) {
        return Facing(face).texture;
    }

    def Rotation(EnumFacing face, int fallback) {
        if(Facing(face).rotation == null) {
            return fallback;
        }
        return Facing(face).rotation;
    }

    int Rotation(EnumFacing face) {
        return Rotation(face, 0);
    }

    def Tint(EnumFacing face, int fallback) {
        if(Facing(face).tintindex == null) {
            return fallback;
        }
        return Facing(face).tintindex;
    }

    int Tint(EnumFacing face) {
        return Tint(face, 0);
    }
}
