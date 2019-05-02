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

package com.thesledgehammer.groovymc.experimental.misc

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel
import com.thesledgehammer.groovymc.client.model.GroovyBlockModel
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.client.render.keys.GroovyRenderKeysDefinition
import com.thesledgehammer.groovymc.config.Constants

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonObjectState
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

class JsonTest {

    static void main(String[] args) {
        GroovyLoader GL = new GroovyLoader(Constants.MOD_PATH, Constants.RESOURCE_PATH, Constants.GROOVY_JVM, Constants.URL, Constants.MOD_ID)
        GroovyBlockModel blockModel = new GroovyBlockModel("engine_base");

        //Model Elements
        blockModel.setModelElements("base");
        //blockModel.setModelElements("base_moving");
        //blockModel.setModelElements("trunk");
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

        //blockModel.GroovyDefinitionContext().setResourceLocation("#side")
        //println blockModel.GroovyDefinitionContext()
        //println blockModel.getMutableQuads(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite());
        //println blockModel.addBakedQuadsToBlock(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite()).sprite

        //println TextureEntry.Register.getTextureEntries().get(0).getResourceLocation()
        //println blockModel.getMutableQuads(EnumFacing.EAST, blockModel.GroovyDefinitionContext().getTextureAtlasSprite())

        //List<String> var = ListTools.FloatListToStringList(blockModel.getModelElements(1).To());
        //println VariableContext.AssignVariable("10.0", var, 1, "progress_size").getValue();

        GroovysonObjectState GOS = new GroovysonObjectState(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", "block", "blockstat");

        //println GOS.getMultipartWhen().OR.east
       // renderKeysDefinition.getCutoutKey().Test(blockModel)
        //CutoutKey cutoutParts = new CutoutKey(blockModel, 0);
       // cutoutParts.Cutout(blockModel);

        //println blockModel.getGroovyRenderKeyDefinition().getCutoutKey().Cutout(blockModel)
    }

    static MutableQuad[] bakePart(GroovyBaseModel model, List<GroovysonObjectPart> groovysonObjectParts) {
        List<MutableQuad[]> mutableQuads = new ArrayList<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            if (face != null) {
                mutableQuads.add(bakePartFace(model, groovysonObjectParts, face))
            }
        }
        MutableQuad[] mutable = mutableQuads.toArray() as MutableQuad[];
        return mutable;
    }

    static MutableQuad[] bakePartFace(GroovyBaseModel model, List<GroovysonObjectPart> groovysonObjectParts, EnumFacing face) {
        //TextureAtlasSprite missingSprite = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        List<MutableQuad> list = new ArrayList<>();
        groovysonObjectParts = model.getModelElements();

        for (GroovysonObjectPart part : groovysonObjectParts) {
            for (JsonQuads quad : model.Quads(face)) {
                String lookup = quad.texture;
                int attempts = 0;
                while (lookup.startsWith("#") && part.TextureFace(face).contains(lookup) && attempts < 10) {
                    lookup = part.TextureFace(face);
                    attempts++;
                }
                if(lookup.startsWith("~") && part.TextureFace(face).contains(lookup)) {
                    //BC8 makes this an immutable map
                    //lookup = part.TextureFace(face);
                }
                TextureAtlasSprite sprite;
                /*if (lookup.startsWith("#") || lookup.startsWith("~")) {
                    if (allowTextureFallthrough) {
                        sprite = null;
                    } else {
                        sprite = missingSprite;
                    }
                } else {
                    sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
                }*/
               // sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
                list.add(quad.toQuad(sprite));
            }
        }
        MutableQuad[] mutableQuads = new MutableQuad[list.size()];
        return list.toArray(mutableQuads);
    }
}