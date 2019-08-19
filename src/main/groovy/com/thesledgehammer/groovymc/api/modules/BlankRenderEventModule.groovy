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

import com.thesledgehammer.groovymc.init.RenderEventModuleContainer
import com.thesledgehammer.groovymc.utils.Log
import org.apache.logging.log4j.Level

abstract class BlankRenderEventModule extends BlankModule implements IRenderEventModule {

    private String eventName;

    BlankRenderEventModule(String modID, String moduleName, String eventName) {
        super(modID, moduleName);
        setEventName(eventName);
        RenderEventModuleContainer.EVENT_CONTAINER().add(this);
        Log.log(Level.INFO, "${modID}'s ${moduleName} for ${eventName} was added to the RenderEventModule");
    }

    private void setEventName(String eventName) {
        this.eventName = eventName;
    }

    String getEventName() {
        return eventName;
    }
}
