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

package com.thesledgehammer.groovymc.utils

import net.minecraft.util.text.TextFormatting

class StringTools {

    static String formatTextColor(String name, TextFormatting color) {
        int length = name.length();

        if (length < 1) {
            return "";
        }
        String output = "";
        output = output + color + name;
        return output;
    }

    static String stringToRainbow(String name, boolean isBlack) {
        int length = name.length();

        if (length < 1) {
            return "";
        }
        String output = "";
        ArrayList<TextFormatting> colorChars = new ArrayList<>();
        colorChars.add(TextFormatting.RED);
        colorChars.add(TextFormatting.GOLD);
        colorChars.add(TextFormatting.YELLOW);
        colorChars.add(TextFormatting.GREEN);
        colorChars.add(TextFormatting.AQUA);
        colorChars.add(TextFormatting.BLUE);
        colorChars.add(TextFormatting.LIGHT_PURPLE);
        colorChars.add(TextFormatting.DARK_PURPLE);

        for(int i = 0; i < length; i++) {
            output = output + colorChars.get(i % 8) + name.substring(i, i + 1);
        }
        if(isBlack) {
            return output + TextFormatting.BLACK;
        }
        return output + TextFormatting.WHITE;
    }

    static String stringToRainbow(String name) {
        return stringToRainbow(name, false);
    }

    static boolean containsDigit(String name) {
        for(int i = 0; i < name.length(); i++) {
            if(Character.isDigit(name.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    static String getDigitFromString(String name) {
        String digit = "";

        for(int i = 0; i < name.length(); i++) {
            if(Character.isDigit(name.charAt(i))) {
                int idx = Character.getNumericValue(name.charAt(i));
                digit = name.substring(0, idx)
            }
        }
        //Removes Space if Present
        if(digit.contains(' ')) {
            int idx = name.indexOf(' ');
            digit = digit.substring(0, idx);
        }
        return digit;
    }

    static int getIntegerFromString(String name) {
        return Integer.valueOf(getDigitFromString(name));
    }

    static float getFloatFromString(String name) {
        return Float.valueOf(getDigitFromString(name));
    }

    static long getLongFromString(String name) {
        return Long.valueOf(getDigitFromString(name));
    }

    static double getDoubleFromString(String name) {
        return Double.valueOf(getDigitFromString(name));
    }

    static byte getByteFromString(String name) {
        return Byte.valueOf(getDigitFromString(name));
    }

    /* //Not Working
    static String FloatListVariableName(ArrayList<Float> arrPart, String variableName) {
        String name = "";
        for(int i = 0; i < FloatListToStringList(arrPart).size(); i++) {
            if(FloatListToStringList(arrPart).get(i).contains(variableName)) {
                name = FloatListToStringList(arrPart).get(i);
            }
        }
        return name;
    }

    static String DoubleListVariableName(ArrayList<Double> arrPart, String variableName) {
        String name = "";
        for(int i = 0; i < DoubleListToStringList(arrPart).size(); i++) {
            if(DoubleListToStringList(arrPart).get(i).contains(variableName)) {
                name = DoubleListToStringList(arrPart).get(i);
            }
        }
        return name;
    }

    //Variable Methods may be Moved
    //Variables as a Float
    static String Variable(ArrayList<Float> arrPart, String variableName, String variable) {
        String valname = FloatListVariableName(arrPart, variableName);
        ArrayList<String> valarr = StringToList(valname, " ");

        String newValue;
        float base;
        float var;

        valname = variable;

        if(valarr.size() == 1) {
            base = 0;
        } else {
            base = Float.valueOf(valarr.get(0));
        }

        var = Float.valueOf(valname);
        newValue = base + var;
        valarr.clear();
        return newValue;
    }

    static ArrayList<String> VariablePart(ArrayList<Float> arrPart, String variableName, String variable) {
        ArrayList<String> arr = FloatListToStringList(arrPart);
        String valname = FloatListVariableName(arrPart, variableName);
        ArrayList<String> valarr = StringToList(valname, " ");

        String newValue;
        float base;
        float var;
        int idx = 0;

        for (int j = 0; j < arr.size(); j++) {
            if(arr.get(j).contains(variableName)) {
                idx = j;
            }
        }

        valname = variable;

        if(valarr.size() == 1) {
            base = 0;
        } else {
            base = Float.valueOf(valarr.get(0));
        }

        var = Float.valueOf(valname);

        newValue = base + var;
        arr.set(idx, newValue);
        valarr.clear();
        return arr;
    }

    //Variables as a Double
    static String VariableDouble(ArrayList<Double> arrPart, String variableName, String variable) {
        String valname = DoubleListVariableName(arrPart, variableName);
        ArrayList<String> valarr = StringToList(valname, " ");

        String newValue;
        double base;
        double var;

        valname = variable;

        if(valarr.size() == 1) {
            base = 0;
        } else {
            base = Double.valueOf(valarr.get(0));
        }

        var = Double.valueOf(valname);
        newValue = base + var;
        valarr.clear();
        return newValue;
    }

    static ArrayList<String> VariablePartDouble(ArrayList<Double> arrPart, String variableName, String variable) {
        ArrayList<String> arr = DoubleListToStringList(arrPart);
        String valname = DoubleListVariableName(arrPart, variableName);
        ArrayList<String> valarr = StringToList(valname, " ");

        String newValue;
        double base;
        double var;
        int idx = 0;

        for (int j = 0; j < arr.size(); j++) {
            if(arr.get(j).contains(variableName)) {
                idx = j;
            }
        }

        valname = variable;

        if(valarr.size() == 1) {
            base = 0;
        } else {
            base = Double.valueOf(valarr.get(0));
        }

        var = Double.valueOf(valname);

        newValue = base + var;
        arr.set(idx, newValue);
        valarr.clear();
        return arr;
    }
    */
}
