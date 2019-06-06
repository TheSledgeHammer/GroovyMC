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