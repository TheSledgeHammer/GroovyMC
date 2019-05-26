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

import com.thesledgehammer.groovymc.client.model.json.GroovysonAbstractModel
import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableFloat
import com.thesledgehammer.groovymc.experimental.variables.VariableInteger
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.StringTools

class GroovysonVariableModel extends GroovysonAbstractModel {

    GroovysonVariableModel(String resourceObject, String fileName) {
        super(resourceObject, fileName)
    }

    GroovysonVariableModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        super(resourceDirectory, modID, resourceObject, fileName)
    }

    VariableInteger AssignVariableInteger(String newValue, List<String> arr, int index, String inputVariable) {
        VariableInteger vInt = new VariableInteger()
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        int digit = 0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getIntegerFromString(arr.get(index));
        }
        vInt.setValue(digit + Integer.valueOf(newValue));
        return vInt;
    }

    VariableLong AssignVariableLong(String newValue, List<String> arr, int index, String inputVariable) {
        VariableLong vLong = new VariableLong()
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        long digit = 0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getLongFromString(arr.get(index));
        }
        vLong.setValue(digit + Long.valueOf(newValue));
        return vLong;
    }

    VariableFloat AssignVariableFloat(String newValue, List<String> arr, int index, String inputVariable) {
        VariableFloat vFloat = new VariableFloat()
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        float digit = 0.0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getFloatFromString(arr.get(index));
        }
        vFloat.setValue(digit + Float.valueOf(newValue));
        return vFloat;
    }

    VariableDouble AssignVariableDouble(String newValue, List<String> arr, int index, String inputVariable) {
        VariableDouble vDouble = new VariableDouble()
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        double digit = 0.0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getDoubleFromString(arr.get(index));
        }
        vDouble.setValue(digit + Double.valueOf(newValue));
        return vDouble;
    }

    VariableBoolean AssignVariableBoolean(boolean newValue, String inputVariable) {
        VariableBoolean vBoolean = new VariableBoolean();
        String oldVal = inputVariable;
        if(oldVal != null) {
            oldVal = newValue;
        }
        boolean bool = null;
        if(newValue instanceof Boolean) {
            bool = String.valueOf(newValue);
        }
        vBoolean.setValue(bool);
        return vBoolean;
    }

    VariableObject<String> AssignVariableString() {
        VariableObject<String> vString = new VariableObject<String>();

        return vString;
    }

    //Fix Up not relavent to Object
    /*
    <T> VariableObject<T> AssignVariableObject(String newValue, List<String> arr, int index, String inputVariable) {
        VariableObject vObject = new VariableObject()
        String oldVal = UnassignedVariable(arr, index, inputVariable);
        if(oldVal != null) {
            oldVal = newValue;
        }
        double digit = 0.0;
        if(StringTools.containsDigit(arr.get(index))) {
            digit = StringTools.getDoubleFromString(arr.get(index));
        }
        vObject.setValue(digit + Double.valueOf(newValue));
        return vObject;
    }
    */

    private String UnassignedVariable(List<String> arr, int index, String inputVariable) {
        String variableInput = "";
        if(containsUnassignedVariable(arr, index, inputVariable)) {
            int idx = arr.get(index).indexOf(inputVariable);
            variableInput = arr.get(index).substring(idx);
        }
        return variableInput;
    }

    private boolean containsUnassignedVariable(List<String> arr, int index, String inputVariable) {
        if(arr.get(index).contains(inputVariable)) {
            return true;
        }
        return false;
    }

    static float[] bakePosition(VariableDouble[] var) {
        float x = (float) (var[0].getValue() / 16f);
        float y = (float) (var[1].getValue() / 16f)
        float z = (float) (var[2].getValue() / 16f)
        return [x, y, z];
    }
}
