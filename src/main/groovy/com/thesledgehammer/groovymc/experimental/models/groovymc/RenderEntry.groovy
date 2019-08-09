package com.thesledgehammer.groovymc.experimental.models.groovymc

import com.thesledgehammer.groovymc.api.modules.BlankEventBusModule
import com.thesledgehammer.groovymc.client.definitions.model.ModelEntryHolder
import net.minecraft.tileentity.TileEntity

abstract class RenderEntry<M extends ModelEntryHolder, T extends TileEntity> extends BlankEventBusModule implements IRenderEntry<M, T> {

    private M modelEntry;

    RenderEntry(String modID, String eventName, M model) {
        super(modID, eventName);
        this.modelEntry = model;
    }
}
