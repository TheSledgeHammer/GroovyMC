/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

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
