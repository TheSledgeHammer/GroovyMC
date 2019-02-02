/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.models.GBlockBakedModel
import com.thesledgehammer.groovymc.utils.GroovyLoader

/*TODO: Create Following:
- BlankItemModel: Uses ModelTools.addBakedQuadsToItem
- BlankBlockModel: Uses ModelTools.addBakedQuadsToBlock
*/
class JsonTest {

	static GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

	static void main(String[] args) {

        GBlockBakedModel GBBM = new GBlockBakedModel("block", "engine_base");

		//Model Elements
		GBBM.setModelParts("base");
		GBBM.setModelParts("base_moving");
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

		//String lookup = GBBM.getAbstractModel().jTexTable.get(EnumFacing.EAST, 0);
		//JsonTexture tex = new JsonTexture(lookup);
		//TextureAtlasSprite sprite;
//		lookup = GBBM.getAbstractModel().jTexTable.get(EnumFacing.EAST, 0).location
		//sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
		//ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
		//face.sprite = sprite;
		//face.faceData = GBBM.getAbstractModel().jTexTable.get(EnumFacing.EAST, 0).faceData;

		println  GBBM.getAbstractModel().getJsonTextureMappings()
	}

	static void Old() {
		/*
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
		*/
	}
}
