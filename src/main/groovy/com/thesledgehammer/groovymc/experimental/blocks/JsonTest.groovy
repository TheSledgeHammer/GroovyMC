/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.blocks


import com.thesledgehammer.groovymc.client.model.BlankGroovyModel
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.models.GBlockBakedModel
import com.thesledgehammer.groovymc.experimental.models.ModelEntryStatic
import com.thesledgehammer.groovymc.experimental.textures.GroovyTextureAtasSpriteBuilder
import com.thesledgehammer.groovymc.experimental.textures.GroovyTextureMap
import com.thesledgehammer.groovymc.experimental.textures.TextureAtlas
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

/*TODO: Create Following:
- BlankItemModel: Uses ModelTools.addBakedQuadsToItem
- BlankBlockModel: Uses ModelTools.addBakedQuadsToBlock
*/
class JsonTest {

	static GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

	static void main(String[] args) {

        GBlockBakedModel GBBM = new GBlockBakedModel("block", "engine_base");
		ModelEntryStatic entry = new ModelEntryStatic(GBBM.abstractModel, new BlankGroovyModel());

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

		//TextureAtlasSprite & TextureMap with GroovyTextureAtasSpriteBuilder
		GroovyTextureMap textureMap = new GroovyTextureMap("textures", null, true);

		GroovyTextureAtasSpriteBuilder GTASB = new GroovyTextureAtasSpriteBuilder.Builder()
				.setGroovyTextureMap("textures")
				.setTextureAtlasSprite("'#back'")
				.build();

		for(EnumFacing face : EnumFacing.VALUES) {
			//GTASB.getTextureAtlasSpriteProvider().setSprite(face, 0, textureMap);
			//println GBBM.getAbstractModel().getTexturesByName("#back")
			//println GBBM.getAbstractModel().getRawModelPart(0).Facing(EnumFacing.EAST)
		}

		//GTASB.getGroovyTextureMap().setTextureEntry(GTASB.getTextureAtlasSpriteProvider());
		//textureMap.setTextureEntry(GTASB.getTextureAtlasSpriteProvider())

		//println
		TextureAtlasSprite sprite = TextureAtlas.createForConfig(new ResourceLocation("back"));
		TextureAtlas atlas = new TextureAtlas(GBBM.getAbstractModel(), EnumFacing.DOWN, 0);
		//this.minV = (float)originInY / (float)inY;
		//this.maxV = (float)(originInY + this.height) / (float)inY;

		println GBBM.getAbstractModel().getJsonTexture(EnumFacing.EAST,0).location//BakeTools.TexturedFaceLookup(GBBM.getAbstractModel(), EnumFacing.EAST, 0, textureMap);
		//println GBBM.getAbstractModel().TexturedFaceLookup(EnumFacing.UP, 0, textureMap).sprite
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
