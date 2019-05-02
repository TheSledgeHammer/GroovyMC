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

package com.thesledgehammer.groovymc.client.render.keys

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel
import com.thesledgehammer.groovymc.utils.ListTools
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.property.IExtendedBlockState
@Deprecated //Please refer to the RenderKeys in "experimental/render"
class SolidKey {

    private String renderName;
    private LinkedList<String> solidList;
    private BlockRenderLayer render;
    private LinkedList<EnumFacing> solidFaces;

    SolidKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        setSolidKey(GROOVY_MODEL, modelElement);
    }

    private void setSolidKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        //Valid RenderTypes
        String renderType = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypes();

        //Map render to BlockRenderLayer & create a list of applicable faces
        if(renderType.contains("solid")) {
            this.renderName = "solid";
            this.render = BlockRenderLayer.valueOf(renderName.toUpperCase());
            this.solidList = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypeFace(renderName);
        }

        //Map Each Face from Soild to its EnumFacing face
        this.solidFaces = new LinkedList<>();
        for(int i = 0; i < solidList.size(); i++) {
            String faceSolid = solidList.get(i);
            for(EnumFacing face : EnumFacing.VALUES) {
                if (faceSolid.equalsIgnoreCase(face.name())) {
                    solidFaces.add(EnumFacing.valueOf(faceSolid.toUpperCase()));
                }
            }
        }
    }

    BlockRenderLayer RenderLayer() {
        return render;
    }

    LinkedList<String> SolidKeyList() {
        return solidList;
    }

    LinkedList<EnumFacing> SolidKeyFaces() {
        return solidFaces;
    }

    //Before this, need to setRenderLayer on each Face
    boolean canRenderLayerSolid(Block block, IBlockState state) {
        return block.canRenderInLayer(state, RenderLayer());
    }

    boolean ignoreFaces(EnumFacing face) {
        /*
        check if face is null or contains all faces
        -> true: ignore faces and apply render to all
        -> false: apply render to set faces
        */
        if(face == null || solidList.get(0).contentEquals("all") || solidList.size() == 6 && !ListTools.doesListContainDuplicates(solidList)) {
            return true;
        }
        if(face != null || solidList.size() < 6) {
            return false;
        }
        return false;
    }
}
