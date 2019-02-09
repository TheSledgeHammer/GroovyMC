/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.client.model.json

import com.thesledgehammer.groovymc.client.model.MutableVertex

import javax.vecmath.Point2f
import javax.vecmath.Point3f
import javax.vecmath.Vector3f

class JsonVertex {
	
	Point3f pos;
	Vector3f normal;
	Point2f uv;
	
	JsonVertex(MutableVertex vertex) {
		this.pos = vertex.positionvf();
		this.normal = vertex.normal();
		this.uv = vertex.tex();
	}
	
	void loadInto(MutableVertex vertex) {
		vertex.positionv(pos);
		vertex.normalv(normal);
		vertex.texv(uv);
	}
}
