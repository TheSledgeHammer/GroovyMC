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
 
package com.thesledgehammer.groovymc.client.definitions

import com.thesledgehammer.groovymc.api.client.definitions.IGroovyModelDefinition
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState

class GroovyModelDefinition implements IGroovyModelDefinition {

    private IBakedModel bakedModel;
    private IModel iModel;
    private IModelState iModelState;
    private IModelPart iModelPart;
    private List<IBakedModel> bakedModelList = new LinkedList<>();
    private List<IModel> iModelList = new LinkedList<>();
    private List<IModelState> iModelStateList = new LinkedList<>();
    private List<IModelPart> iModelPartList = new LinkedList<>();

    @Override
    IBakedModel getIBakedModel() {
        return bakedModel;
    }

    @Override
    IModel getIModel() {
        return iModel
    }

    @Override
    IModelState getIModelState() {
        return iModelState
    }

    @Override
    IModelPart getIModelPart() {
        return iModelPart
    }

    @Override
    List<IBakedModel> getIBakedModels() {
        return bakedModelList;
    }

    @Override
    List<IModel> getIModels() {
        return iModelList;
    }

    @Override
    List<IModelState> getIModelStates() {
        return iModelStateList;
    }

    @Override
    List<IModelPart> getIModelParts() {
        return iModelPartList;
    }

    @Override
    void setIBakedModel(IBakedModel bakedModel) {
        this.bakedModel = bakedModel;
        bakedModelList.add(this.bakedModel);
    }

    @Override
    void setIModel(IModel iModel) {
        this.iModel = iModel;
        iModelList.add(this.iModel);
    }

    @Override
    void setIModelState(IModelState iModelState) {
        this.iModelState = iModelState;
        iModelStateList.add(this.iModelState);
    }

    @Override
    void setIModelPart(IModelPart iModelPart) {
        this.iModelPart = iModelPart;
        iModelPartList.add(this.iModelPart);
    }
}