package com.thesledgehammer.groovymc.api.modules

import com.thesledgehammer.groovymc.init.EventBusModuleContainer

abstract class BlankEventBusModule implements IEventBusModule {

    BlankEventBusModule() {
        EventBusModuleContainer.EVENT_REGISTER().add(this);
    }
}
