/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */

package com.thesledgehammer.groovymc.api.minecraftjoules

class MjTools {
    /** A single minecraft joule, in micro joules (the power system base unit) */
    static final long ONE_MINECRAFT_JOULE = 1_000_000L;

    static final long MJ = ONE_MINECRAFT_JOULE;

    static final long formatMj(long microMj) {
        return (microMj / (double) MJ);
    }

    static final long formatMj(long microMj, EnumVoltage voltage) {
        long volts = voltage.getVoltage() * MJ;
        long generate = Math.min(microMj, volts);
        return (generate / (double) MJ);
    }
}
