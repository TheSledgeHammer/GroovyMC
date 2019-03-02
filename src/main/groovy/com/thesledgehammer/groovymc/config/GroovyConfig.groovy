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
