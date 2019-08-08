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

package com.thesledgehammer.groovymc.api.modules

import com.thesledgehammer.groovymc.init.EventBusModuleContainer

abstract class BlankEventBusModule implements IEventBusModule {

    private String modID;;
    private String eventName;

    BlankEventBusModule(String modID, String eventName) {
        setModID(modID);
        setEventName(eventName);
        EventBusModuleContainer.EVENT_CONTAINER().add(this);
    }

    private void setModID(String modID) {
        this.modID = modID;
    }

    private void setEventName(String eventName) {
        this.eventName = eventName;
    }

    String getModID() {
        return modID;
    }

    String getEventName() {
        return eventName;
    }
/*
    @Override
    @SideOnly(Side.CLIENT)
    abstract void onModelBake(ModelBakeEvent event);*/
}
