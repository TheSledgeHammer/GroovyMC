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
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing

@Deprecated //Please refer to the RenderKeys in "experimental/render"
class CutoutKey {

    private String renderName;
    private LinkedList<String> cutoutList;
    private BlockRenderLayer render;
    private LinkedList<EnumFacing> cutoutFaces;

    CutoutKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        setCutoutKey(GROOVY_MODEL, modelElement);
    }

    private void setCutoutKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        //Valid RenderTypes
        String renderType = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypes();

        //Map render to BlockRenderLayer & create a list of applicable faces
        if(renderType.contains("cutout")) {
            this.renderName = "cutout";
            this.render = BlockRenderLayer.valueOf(renderName.toUpperCase());
            this.cutoutList = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypeFace(renderName);
        }

        //Map Each Face from Cutout to its EnumFacing face
        this.cutoutFaces = new LinkedList<>();
        for(int i = 0; i < cutoutList.size(); i++) {
            String faceCutout = cutoutList.get(i);
            for(EnumFacing face : EnumFacing.VALUES) {
                if (faceCutout.equalsIgnoreCase(face.name())) {
                    cutoutFaces.add(EnumFacing.valueOf(faceCutout.toUpperCase()));
                }
            }
        }
    }

    BlockRenderLayer RenderLayer() {
        return render;
    }

    LinkedList<String> CutoutKeyList() {
        return cutoutList;
    }

    LinkedList<EnumFacing> CutoutKeyFaces() {
        return cutoutFaces;
    }

    //Before this, need to setRenderLayer on each Face
    boolean canRenderLayerCutout(Block block, IBlockState state) {
        return block.canRenderInLayer(state, RenderLayer());
    }

    boolean ignoreFaces(EnumFacing face) {
        /*
        check if face is null or contains all faces
        -> true: ignore faces and apply render to all
        -> false: apply render to set faces
        */
        if(face == null || cutoutList.get(0).contentEquals("all") || cutoutList.size() == 6 && !ListTools.doesListContainDuplicates(cutoutList)) {
            return true;
        }
        if(face != null || cutoutList.size() < 6) {
            return false;
        }
        return false;
    }
}
//String layerType, ModelElement, BlockRenderLayer
//IExtendedBlockState state;
//state.getBlock().canRenderInLayer()