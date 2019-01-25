/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.config


import com.thesledgehammer.groovymc.proxy.CommonProxy
import com.thesledgehammer.groovymc.utils.Log
import net.minecraftforge.common.config.Configuration
import org.apache.logging.log4j.Level

class GroovyConfig {

    static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initConfig(cfg);
        } catch (Exception e) {
            Log.log(Level.ERROR, "Problem loading config file!", e);
        } finally {
            if(cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initConfig(Configuration cfg) {

    }
}
