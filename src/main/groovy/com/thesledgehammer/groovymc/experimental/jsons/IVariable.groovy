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

package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableFloat
import com.thesledgehammer.groovymc.experimental.variables.VariableInteger
import com.thesledgehammer.groovymc.experimental.variables.VariableLong

interface IVariable {

    VariableInteger AssignVariableInteger(String newValue, List<String> arr, int index, String inputVariable);

    VariableLong AssignVariableLong(String newValue, List<String> arr, int index, String inputVariable)

    VariableFloat AssignVariableFloat(String newValue, List<String> arr, int index, String inputVariable);

    VariableDouble AssignVariableDouble(String newValue, List<String> arr, int index, String inputVariable);

    VariableBoolean AssignVariableBoolean(boolean newValue, String inputVariable);
}