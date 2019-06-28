package com.thesledgehammer.groovymc.api.minecraftjoules

class MjTools {
    /** A single minecraft joule, in micro joules (the power system base unit) */
    static final long ONE_MINECRAFT_JOULE = 1_000_000L;

    static final long MJ = ONE_MINECRAFT_JOULE;

    static long formatMj(long microMj) {
        return (microMj / MJ as double);
    }
}
