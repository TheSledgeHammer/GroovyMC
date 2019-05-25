package com.thesledgehammer.groovymc.experimental.integration.modules.buildcraft

class MinecraftJoules extends MinecraftJoulesStorage {

    MinecraftJoules(long capacity) {
        super(capacity)
    }

    MinecraftJoules(long capacity, long maxTransfer) {
        super(capacity, maxTransfer)
    }

    MinecraftJoules(long capacity, long maxReceive, long maxExtract) {
        super(capacity, maxReceive, maxExtract)
    }
}
