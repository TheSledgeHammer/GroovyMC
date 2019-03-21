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

import com.thesledgehammer.groovymc.utils.StringTools

//Temporary PlaceHolder for Variables(Mutable Objects)
class VariableContext {

    static boolean containsUnassignedVariable(List<String> arr, int index, String inputVariable) {
        if(arr.get(index).contains(inputVariable)) {
            return true;
        }
        return false;
    }

    static String UnassignedVariable(List<String> arr, int index, String inputVariable) {
        String variableInput = "";
        if(containsUnassignedVariable(arr, index, inputVariable)) {
            int idx = arr.get(index).indexOf(inputVariable);
            variableInput = arr.get(index).substring(idx);
        }
        return variableInput;
    }

    static Variable AssignVariable(String newValue, List<String> arr, int index, String inputVariable) {
        Variable variable = new Variable();
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        float digit = 0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getFloatFromString(arr.get(index))
        }
        variable.setValue(digit + Float.valueOf(newValue));

        return variable;
    }
}
