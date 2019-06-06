package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.experimental.variables.VariableBoolean
import com.thesledgehammer.groovymc.experimental.variables.VariableDouble
import com.thesledgehammer.groovymc.experimental.variables.VariableFloat
import com.thesledgehammer.groovymc.experimental.variables.VariableInteger
import com.thesledgehammer.groovymc.experimental.variables.VariableLong
import com.thesledgehammer.groovymc.experimental.variables.VariableObject
import com.thesledgehammer.groovymc.utils.StringTools

trait VariableTraits implements IVariable {

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    //Incomplete
    VariableObject<String> AssignVariableString(String newValue, String inputVariable) {
        VariableObject<String> vString = new VariableObject<String>();
        return vString;
    }

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
}