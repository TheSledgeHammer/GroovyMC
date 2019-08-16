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

import com.thesledgehammer.groovymc.init.CompatModuleContainer
import com.thesledgehammer.groovymc.utils.Log
import org.apache.logging.log4j.Level

abstract class BlankCompatModule implements ICompatModule {

    private String modID;

    BlankCompatModule(String modID) {
        setModID(modID);
        CompatModuleContainer.MODULES_CONTAINER().add(this);
        Log.log(Level.INFO, "${modID} was added to CompatModule");
    }

    private void setModID(String modID) {
        this.modID = modID;
    }

    String getModID() {
        return modID;
    }

    @Override
    abstract void init();
}
