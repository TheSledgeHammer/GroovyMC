/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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

    static String removeBrackets(String name) {
        if(name.contains('[') && name.contains(']')) {
            int idx1 = name.indexOf('[');
            int idx2 = name.indexOf(']');
            name = name.substring(idx1 + 1, idx2);
        }
        if(name.contains('{') && name.contains('}')) {
            int idx1 = name.indexOf('{');
            int idx2 = name.indexOf('}');
            name = name.substring(idx1 + 1, idx2);
        }
        return name;
    }

    static ArrayList<String> StringToList(String name) {
        return StringToList(name, ', ');
    }

    static ArrayList<String> StringToList(String name, String regex) {
        String name1 = removeBrackets(name);
        String[] arr = name1.split(regex);
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    private static ArrayList<String> arrToString = new ArrayList<>();
    static ArrayList<String> FloatListToStringList(ArrayList<Float> arrPart) {
        //arrToString = new ArrayList<>();
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    //private static ArrayList<String>
    static ArrayList<String> DoubleListToStringList(ArrayList<Double> arrPart) {
        //arrToString = new ArrayList<>();
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    static ArrayList<String> getStringList() {
        return arrToString;
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
