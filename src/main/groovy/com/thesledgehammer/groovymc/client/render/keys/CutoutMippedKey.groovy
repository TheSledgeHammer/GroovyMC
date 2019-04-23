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
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.property.IExtendedBlockState

class CutoutMippedKey {

    private String renderName;
    private LinkedList<String> cutoutMippedList;
    private BlockRenderLayer render;
    private LinkedList<EnumFacing> cutoutMippedFaces;

    CutoutMippedKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        setCutoutMippedKey(GROOVY_MODEL, modelElement);
    }

    private void setCutoutMippedKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        //Valid RenderTypes
        String renderType = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypes();

        //Map render to BlockRenderLayer & create a list of applicable faces
        if(renderType.contains("cutout_mipped")) {
            this.renderName = "cutout_mipped";
            this.render = BlockRenderLayer.valueOf(renderName.toUpperCase());
            this.cutoutMippedList = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypeFace(renderName);
        }

        //Map Each Face from CutoutMipped to its EnumFacing face
        this.cutoutMippedFaces = new LinkedList<>();
        for(int i = 0; i < cutoutMippedList.size(); i++) {
            String faceCutoutMipped = cutoutMippedList.get(i);
            for(EnumFacing face : EnumFacing.VALUES) {
                if (faceCutoutMipped.equalsIgnoreCase(face.name())) {
                    cutoutMippedFaces.add(EnumFacing.valueOf(faceCutoutMipped.toUpperCase()));
                }
            }
        }
    }

    BlockRenderLayer RenderLayer() {
        return render;
    }

    LinkedList<String> CutoutMippedKeyList() {
        return cutoutMippedList;
    }

    LinkedList<EnumFacing> CutoutMippedKeyFaces() {
        return cutoutMippedFaces;
    }

    //Before this, need to setRenderLayer on each Face
    boolean applyCutoutMippedKey(EnumFacing face, IExtendedBlockState state) {
        /*
        check if face is null or contains all faces
        -> true: ignore faces and apply render to all
        -> false: apply render to set faces
         */
        boolean ignoreFaces;
        if(face == null || cutoutMippedList.get(0).contentEquals("all") || cutoutMippedList.size() == 6 && !ListTools.doesListContainDuplicates(cutoutMippedList)) {
            ignoreFaces = true;
        }
        if(face != null || cutoutMippedList.size() < 6) {
            ignoreFaces = false;
        }
        return state.getBlock().canRenderInLayer(state, render);
    }
}
