package com.thesledgehammer.groovymc.api

import net.minecraft.nbt.NBTTagCompound

interface INBTCompound {

    NBTTagCompound writeToNBT(NBTTagCompound tag)

    void readFromNBT(NBTTagCompound tag);

}