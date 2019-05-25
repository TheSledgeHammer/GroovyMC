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

package com.thesledgehammer.groovymc.client.model.json


import net.minecraft.util.EnumFacing

class GroovysonStaticModel extends GroovysonAbstractModel {

    GroovysonStaticModel(String resourceObject, String fileName) {
        super(resourceObject, fileName);
    }

    GroovysonStaticModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, resourceObject, fileName);
    }

    //Get Element variables at index by face name
    HashMap<String, EnumFacing> FaceMap(int index) {
        HashMap<String, EnumFacing> arrMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            arrMap.put(face.getName(), getRawModelPart(index).Facing(face));
        }
        return arrMap;
    }
}
