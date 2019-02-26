/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.patterns.GroovyBlockModel
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.util.EnumFacing

/*TODO:
1. GroovyBaseModel: Create method to get ResourceLocation of Textures from setModelTextures & setTextureAtlasSprites bug: only gets last sprite entry
2. GroovyResourceDefinition: Add Get TextAtlasSprite by name & as well as by ModelElement if needed
*/
class JsonTest {

	static GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

	static void main(String[] args) {
		//GroovyBaseModel model = new GroovyBaseModel("block","engine_base");
		GroovyBlockModel blockModel = new GroovyBlockModel("engine_base");
		//blockModel.setGroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition())
		//Model Elements
		blockModel.setModelElements("base");
		//blockModel.setModelElements("base_moving");
		blockModel.setModelElements("trunk");
		//blockModel.setModelElements("chamber");
		//Model Textures

		blockModel.setModelTextures("#trunk_blue");
		blockModel.setModelTextures("#trunk_green");
		blockModel.setModelTextures("#trunk_yellow");
		blockModel.setModelTextures("#trunk_red");
		blockModel.setModelTextures("#trunk_overheat");
		blockModel.setModelTextures("#trunk_black");
		blockModel.setModelTextures("#chamber");
		blockModel.setModelTextures("#back");
		blockModel.setModelTextures("#side");

		//blockModel.GroovyDefinitionContext().setTextureAtlasSprite("back");
		//blockModel.GroovyDefinitionContext().setTextureAtlasSprite("side");
		println blockModel.GroovyDefinitionContext()
		//println blockModel.getMutableQuads(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite());
		//println blockModel.addBakedQuadsToBlock(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite()).sprite
	}
}
