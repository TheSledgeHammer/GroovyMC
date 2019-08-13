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
package com.thesledgehammer.groovymc.api.client.definitions

import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState

interface IGroovyModelDefinition {

    IBakedModel getIBakedModel();

    IModel getIModel();

    IModelState getIModelState();

    IModelPart getIModelPart();

    List<IBakedModel> getIBakedModels();

    List<IModel> getIModels();

    List<IModelState> getIModelStates();

    List<IModelPart> getIModelParts();

    void setIBakedModel(IBakedModel bakedModel);

    void setIModel(IModel iModel);

    void setIModelState(IModelState iModelState);

    void setIModelPart(IModelPart iModelPart);
}