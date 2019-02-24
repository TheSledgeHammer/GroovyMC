/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.blocks


import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.bakedmodels.GroovyAbstractModelBaker
import com.thesledgehammer.groovymc.client.model.GroovysonModel
import com.thesledgehammer.groovymc.experimental.patterns.ModelBase
import com.thesledgehammer.groovymc.client.model.ModelEntryStatic

import com.thesledgehammer.groovymc.utils.GroovyLoader

/*TODO: Create Following:
- BlankItemModel: Uses ModelTools.addBakedQuadsToItem
- BlankBlockModel: Uses ModelTools.addBakedQuadsToBlock
*/
class JsonTest {

	static GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

	static void main(String[] args) {
		GroovyAbstractModelBaker GBBM = new GroovyAbstractModelBaker("block", "engine_base");
		GroovysonModel Engine_Def = new GroovysonModel("block","engine_base")

		//Model Elements
		GBBM.setModelParts("base");
		//GBBM.setModelParts("base_moving");
		GBBM.setModelParts("trunk");
		//GBBM.setModelParts("chamber");
		//Model Textures
		GBBM.setModelTextures("#trunk_blue");
		GBBM.setModelTextures("#trunk_green");
		GBBM.setModelTextures("#trunk_yellow");
		GBBM.setModelTextures("#trunk_red");
		GBBM.setModelTextures("#trunk_overheat");
		GBBM.setModelTextures("#trunk_black");
		GBBM.setModelTextures("#chamber");
		GBBM.setModelTextures("#back");
		GBBM.setModelTextures("#side");

		ModelBase model = new ModelBase("block","engine_base");

		model.setModelTextures("#trunk_blue");

		ModelEntryStatic MES = new ModelEntryStatic();
		MES.GroovyDefinitionContext().setModelResourceLocation(model.GROOVY_MODEL.name);

		println MES.GroovyDefinitionContext().getModelResourceLocation()
	}
/*
	static void Old() {

		GroovysonObject engine = new GroovysonObject(GL.getModResourceDirectory(), GL.getModID(), Constants.MODEL_PATH_BLOCKS, "engine_base")
		GroovysonObjectPart base = new GroovysonObjectPart(engine, "base")
		GroovysonObjectPart base_moving = new GroovysonObjectPart(engine, "base_moving")
		GroovysonObjectPart trunk = new GroovysonObjectPart(engine, "trunk");
		GroovysonObjectPart chamber = new GroovysonObjectPart(engine, "chamber");

		//G.addModelResourceObject("block", "engine_base");
		//G.addTextureResourceObject("items", "beecomb_0");
		//println base.To();
		//print base_moving.VariablePart(base_moving.From(),"progress_size", "10.0");
		//println engine.getTextures()
		//JsonTexture texture = new JsonTexture(base, EnumFacing.NORTH);
		//println engine.getTexturesByName("#chamber");

		AbstractModel AM = new AbstractModel("block", "engine_base")

		AM.setRawModelParts("base")
		//AM.setRawModelParts("base_moving");
		AM.setRawModelParts("trunk")

		AM.setRawModelTextures("#trunk_yellow")
		AM.setRawModelTextures("#chamber")
		//AM.getRawModelTexturesLocation("#chamber");
		//println AM.getRawModelPart(0).
		//map.setTextureEntry(
		//map.registerSprite(G.getTextureResourceLocationObject(0))
		for(EnumFacing face : EnumFacing.VALUES) {
			if(face.equals(EnumFacing.NORTH)) {
				println ModelTools.addBakedQuadsToBlock(AM, face, sprite).size();
			}
		}
	}
	*/
}
