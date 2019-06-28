package com.thesledgehammer.groovymc.api.integration

import com.thesledgehammer.groovymc.integration.ModuleContainer

abstract class BlankModule implements IModule {

    private String modID;

    BlankModule(String modID) {
        setModID(modID);
        ModuleContainer.MODULES_CONTAINER().add(this);
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
