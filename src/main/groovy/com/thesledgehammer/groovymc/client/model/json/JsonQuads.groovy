/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Lines 25-30 & Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.utils.ListTools
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

import javax.vecmath.Vector3f

class JsonQuads {

	final boolean shade = false;
	int tint = -1;
	EnumFacing face;
	String texture;
	final JsonVertex[] vertices = new JsonVertex[4];

	JsonQuads(GroovysonObjectPart groovysonObjectPart, float[] from, float[] to, EnumFacing face) {
		this.face = face;
		this.tint = groovysonObjectPart.Tint(face, -1);
		this.texture = groovysonObjectPart.TextureFace(face);
		int rotation = groovysonObjectPart.Rotation(face, 0);
		float[] uv = ListTools.FloatListToFloatArray(groovysonObjectPart.FacingUv(face));

        ModelUtil.UvFaceData uvs = new ModelUtil.UvFaceData();
		uvs.minU = (float) (uv[0] / 16f);
		uvs.minV = (float) (uv[1] / 16f);
		uvs.maxU = (float) (uv[2] / 16f);
		uvs.maxV = (float) (uv[3] / 16f);
		Vector3f radius = new Vector3f(to[0] - from[0] as float, to[1] - from[1] as float, to[2] - from[2] as float);
		radius.scale(0.5f);
		Vector3f center = new Vector3f(from);
		center.add(radius);
		MutableQuad quad = ModelUtil.createFace(face, center, radius, uvs);
		quad.rotateTextureUp(rotation);
		vertices[0] = new JsonVertex(quad.vertex_0);
		vertices[1] = new JsonVertex(quad.vertex_1);
		vertices[2] = new JsonVertex(quad.vertex_2);
		vertices[3] = new JsonVertex(quad.vertex_3);
	}

	MutableQuad toQuad(TextureAtlasSprite sprite) {
		MutableQuad quad = new MutableQuad(this.tint, this.face, this.shade);
		vertices[0].loadInto(quad.vertex_0);
		vertices[1].loadInto(quad.vertex_1);
		vertices[2].loadInto(quad.vertex_2);
		vertices[3].loadInto(quad.vertex_3);
		if(sprite != null) {
			quad.texFromSprite(sprite);
			quad.setSprite(sprite);
		}
		return quad;
	}
}