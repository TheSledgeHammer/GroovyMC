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

<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/api/modules/model/ModelEntryConsumer.groovy
package com.thesledgehammer.groovymc.client.definitions.model

abstract class ModelEntryConsumer extends ModelEntryProvider {

    ModelEntryConsumer() {
        super();
    }
}
=======
package com.thesledgehammer.groovymc.api.modules

/* Redesign to better suit 1.14 & beyond */
interface IBlankModule {

    void preInit();

    void Init();

    void postInit();
}
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/api/modules/IBlankModule.groovy
