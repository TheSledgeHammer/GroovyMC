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

package com.thesledgehammer.groovymc.experimental.variables

import org.apache.commons.lang3.mutable.MutableFloat

class VariableFloat extends MutableFloat {

    MutableFloat mutableFloat;

    VariableFloat() {
        this.mutableFloat = new MutableFloat();
    }

    VariableFloat(float value) {
        this.mutableFloat = new MutableFloat(value);
    }

}