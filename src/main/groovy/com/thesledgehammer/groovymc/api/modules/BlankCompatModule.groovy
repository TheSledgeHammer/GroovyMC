package com.thesledgehammer.groovymc.api.modules

import com.thesledgehammer.groovymc.init.CompatModuleContainer
import com.thesledgehammer.groovymc.utils.Log
import org.apache.logging.log4j.Level

abstract class BlankCompatModule implements ICompatModule {

    private String modID;

    BlankCompatModule(String modID) {
        setModID(modID);
        CompatModuleContainer.MODULES_CONTAINER().add(this);
        Log.log(Level.INFO, "${modID} was added to CompatModule");
    }

    private void setModID(String modID) {
        this.modID = modID;
    }

    String getModID() {
        return modID;
    }

    @Override
    abstract void init();
}
