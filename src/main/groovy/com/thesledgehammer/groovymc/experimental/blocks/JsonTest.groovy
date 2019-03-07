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

package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.client.definitions.ModelEntry
import com.thesledgehammer.groovymc.client.definitions.TextureEntry
import com.thesledgehammer.groovymc.client.model.GroovyBlockModel
import com.thesledgehammer.groovymc.client.model.ModelEntryRegistery
import com.thesledgehammer.groovymc.client.model.ModelEntryStatic
import com.thesledgehammer.groovymc.config.Constants
import com.thesledgehammer.groovymc.experimental.patterns.CutoutKey
import com.thesledgehammer.groovymc.utils.GroovyLoader
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.BakedItemModel
import net.minecraftforge.client.model.BakedModelWrapper
import net.minecraftforge.common.property.IExtendedBlockState

/*TODO:
1. GroovyBaseModel: Create method to get ResourceLocation of Textures from setModelTextures & setTextureAtlasSprites bug: only gets last sprite entry
2. GroovyResourceDefinition: Add Get TextAtlasSprite by name & as well as by ModelElement if needed
*/
class JsonTest {

	static GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)

	//TODO: FIX TextureEntry & ModelEntry Registering
	static void main(String[] args) {
		//GroovyBaseModel model = new GroovyBaseModel("block","engine_base");
		GroovyBlockModel blockModel = new GroovyBlockModel("engine_base");
		blockModel.setGroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition())

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
		//blockModel.GroovyDefinitionContext().setTextureAtlasSprite("#side");
		//println blockModel.GroovyDefinitionContext()
		//println blockModel.getMutableQuads(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite());
		//println blockModel.addBakedQuadsToBlock(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite()).sprite

		println TextureEntry.Register.getTextureEntries().get(0).getResourceLocation()

		//Can be used to define the blockrenderlayer
		//println blockModel.GROOVY_MODEL.getRawModelPart(0).getPartRenderTypeByName("cutout");
		//println BlockRenderLayer.CUTOUT

		//Use above in conjunction to bakePart with a cutout, translucent, etc..... render
		//println blockModel.getMutableQuads(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite())
	}
}
