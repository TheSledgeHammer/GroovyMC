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

import com.thesledgehammer.groovymc.GroovyMC
import net.minecraft.item.ItemStack
import org.apache.logging.log4j.Level

class Log {

    static void log(Level logLevel, String message) {
        GroovyMC.logger.log(logLevel, message);
    }

    static void log(Level logLevel, String message, Object e) {
        GroovyMC.logger.log(logLevel, message, e);
    }

    static void logInfo(String message){
        GroovyMC.logger.info(message);
    }

    static void logWarn(String message) {
        GroovyMC.logger.warn(message);
    }

    static void logFatal(String message) {
        GroovyMC.logger.fatal(message);
    }

    static void logDebug(String message) {
        GroovyMC.logger.debug(message);
    }

    static void logError(String string) {
        GroovyMC.logger.error(string);
    }

    static void logError(String string, ItemStack stack) {
        GroovyMC.logger.error(string, stack);
    }
}
