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

package com.thesledgehammer.groovymc.client.render

import com.thesledgehammer.groovymc.client.model.GroovyBaseModel
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing

class TranslucentKey {

    private String renderName;
    private LinkedList<String> translucentList;
    private BlockRenderLayer render;
    private LinkedList<EnumFacing> translucentFaces;

    TranslucentKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        setTranslucentKey(GROOVY_MODEL, modelElement);
    }

    private void setTranslucentKey(GroovyBaseModel GROOVY_MODEL, int modelElement) {
        //Valid RenderTypes
        String renderType = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypes();

        //Map render to BlockRenderLayer & create a list of applicable faces
        if(renderType.contains("translucent")) {
            this.renderName = "translucent";
            this.render = BlockRenderLayer.valueOf(renderName.toUpperCase());
            this.translucentList = GROOVY_MODEL.getModelElements(modelElement).BlockRenderTypeFace(renderName);
        }

        //Map Each Face from Translucent to its EnumFacing face
        this.translucentFaces = new LinkedList<>();
        for(int i = 0; i < translucentList.size(); i++) {
            String faceTranslucent = translucentList.get(i);
            for(EnumFacing face : EnumFacing.VALUES) {
                if (faceTranslucent.equalsIgnoreCase(face.name())) {
                    translucentFaces.add(EnumFacing.valueOf(faceTranslucent.toUpperCase()));
                }
            }
        }
    }

    BlockRenderLayer RenderLayer() {
        return render;
    }

    LinkedList<String> TranslucentKeyList() {
        return translucentList;
    }

    LinkedList<EnumFacing> TranslucentKeyFaces() {
        return translucentFaces;
    }

    //Before this, need to setRenderLayer on each Face
    boolean applyTranslucentKey(EnumFacing face, IBlockState state) {
        /*
        check if face is null or contains all faces
        -> true: ignore faces and apply render to all
        -> false: apply render to set faces
         */

        //extendedState.getBlock().canRenderInLayer(state);
        return state.getBlock().canRenderInLayer(state, render);
    }
}
