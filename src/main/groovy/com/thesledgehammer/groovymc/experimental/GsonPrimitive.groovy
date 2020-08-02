package com.thesledgehammer.groovymc.experimental

import com.thesledgehammer.groovymc.utils.GPrimitives

class GsonPrimitive {

    GsonPrimitive(Boolean bool) {
        GPrimitives.isBoolean(bool);
    }

    GsonPrimitive(Number number) {
        GPrimitives.isNumber(number);
    }

    GsonPrimitive(String string) {
        GPrimitives.isString(string);
    }

    GsonPrimitive(Character character) {
        GPrimitives.isCharacter(character);
    }

    GsonPrimitive(Object object) {
        GPrimitives.isObject(object);
    }
}
