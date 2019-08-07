package com.thesledgehammer.groovymc.api.modules

import com.thesledgehammer.groovymc.init.CompatModuleContainer

abstract class BlankCompatModule implements ICompatModule {

    private String modID;

    BlankCompatModule(String modID) {
        setModID(modID);
        CompatModuleContainer.MODULES_CONTAINER().add(this);
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
