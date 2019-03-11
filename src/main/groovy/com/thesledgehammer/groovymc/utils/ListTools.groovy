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

class ListTools {

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

    //Character Brackets:''
    static String removeCharacterBrackets(String name) {
        int first = 1;
        int last = name.length() - 1;
        return name.substring(first, last);
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
    static ArrayList<String> IntegerListToStringList(ArrayList<Integer> arrPart) {
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    static ArrayList<String> LongListToStringList(ArrayList<Long> arrPart) {
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    static ArrayList<String> FloatListToStringList(ArrayList<Float> arrPart) {
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    static ArrayList<String> DoubleListToStringList(ArrayList<Double> arrPart) {
        for(int i = 0; i < arrPart.size(); i++) {
            arrToString.add(arrPart.get(i).toString());
        }
        return arrToString;
    }

    static ArrayList<String> getStringList() {
        return arrToString;
    }

    private static int counter = 0;
    static boolean doesListContainDuplicates(List<Object> list) {
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) == list.get(j)) {
                    count++;
                }
            }
        }
        counter = count;
        if(count > 0) {
            return true;
        }
        return false;
    }

    static int countDuplicates(List<Object> list) {
        if(doesListContainDuplicates(list) && counter > 0){
            return counter;
        }
        return 0;
    }

    static Integer[] IntegerListToIntegerArray(List<Integer> list) {
        Integer[] arr = new Integer[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static Long[] LongListToLongArray(List<Long> list) {
        Long[] arr = new Long[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static Float[] FloatListToFloatArray(List<Float> list) {
        Float[] arr = new Float[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static Double[] DoubleListToDoubleArray(List<Double> list) {
        Double[] arr = new Double[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static Character[] CharacterListToCharacterArray(List<Character> list) {
        Character[] arr = new Character[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static String[] StringListToStringArray(List<String> list) {
        String[] arr = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    static Byte[] ByteListToByteArray(List<Byte> list) {
        Byte[] arr = new Byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
