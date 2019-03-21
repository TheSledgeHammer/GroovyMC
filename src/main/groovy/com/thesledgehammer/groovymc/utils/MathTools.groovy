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
/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * This license pertains to the following Lines(99 - 115) of code.
 */

package com.thesledgehammer.groovymc.utils

import org.apache.commons.lang3.mutable.Mutable

class MathTools {

    static boolean isPrimitive(def obj) {
        if(isInteger(obj) || isFloat(obj) || isDouble(obj) || isLong(obj) || isShort(obj)|| isByte(obj) || isBoolean(obj) || isString(obj) || isCharacter(obj)) {
            return true;
        }
        return false;
    }

    static boolean isNumber(def obj) {
        if(isInteger(obj) || isFloat(obj) || isDouble(obj) || isLong(obj) || isShort(obj)) {
            return true;
        }
        return false;
    }

    static boolean isInteger(def obj) {
        if(obj.class == Integer) {
            return true;
        }
        return false;
    }

    static boolean isFloat(def obj) {
        if(obj.class == Float) {
            return true;
        }
        return false;
    }

    static boolean isDouble(def obj) {
        if(obj.class == Double) {
            return true;
        }
        return false;
    }

    static boolean isLong(def obj) {
        if(obj.class == Long) {
            return true;
        }
        return false;
    }

    static boolean isShort(def obj) {
        if(obj.class == Short) {
            return true;
        }
        return false;
    }

    static boolean isByte(def obj) {
        if(obj.class == Byte) {
            return true;
        }
        return false;
    }

    static boolean isBoolean(def obj) {
        if(obj.class == Boolean) {
            return true;
        }
        return false;
    }

    static boolean isString(def obj) {
        if(obj.class == String) {
            return true;
        }
        return false;
    }

    static boolean isCharacter(def obj) {
        if(obj.class == Character) {
            return true;
        }
        return false;
    }

    static boolean isVariable(def obj) {
        if(obj.class instanceof Mutable) {
            return true;
        }
        return false;
    }

    /**Below is Copied from BuildCraft
     * Please refer to the Copyright Header Above for Licencing
     **/
    static int clamp(int toClamp, int min, int max) {
        return Math.max(Math.min(toClamp, max), min);
    }

    static int clamp(double toClamp, int min, int max) {
        return clamp((int) toClamp, min, max);
    }

    static double clamp(double toClamp, double min, double max) {
        return Math.max(Math.min(toClamp, max), min);
    }

    static long clamp(long toClamp, long min, long max) {
        return Math.max(Math.min(toClamp, max), min);
    }
}
