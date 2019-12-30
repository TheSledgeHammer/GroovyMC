package com.thesledgehammer.groovymc.api.client.json

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.utils.StringTools
import net.minecraft.util.ResourceLocation

class GroovyResourceLocation extends ResourceLocation {

    protected GroovyResourceLocation(int unused, String... resourceName) {
        super(unused, resourceName);
    }

    GroovyResourceLocation(String resourceDomain, String resourcePath) {
        this(0, Split(GroovyLoader.Instance().getModID(), StringTools.regexFirst(
                StringTools.SubString(
                        GroovysonReader.ResourcePath(
                                GroovyLoader.Instance().getModResourceDirectory(),
                                resourceDomain, resourcePath),
                        GroovyLoader.Instance().getModID()), "/", ":")));
    }

    static String[] Split(String modID, String toSplit) {
        String[] astring = [modID, toSplit];
        int i = toSplit.indexOf(58);
        if (i >= 0) {

            astring[1] = toSplit.substring(i + 1, toSplit.length());

            if (i > 1) {
                astring[0] = toSplit.substring(0, i);
            }
        }
        return astring;
    }

    @Override
    String toString() {
        return getResourceDomain() + ':' + getResourcePath();
    }
}
