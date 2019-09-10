/*
 * (C) 2014-2018 Team CoFH / CoFH / Cult of the Full Hub
 * http://www.teamcofh.com
 * Modified by TheSledgeHammer 2018
 */

package com.thesledgehammer.groovymc.utils

import com.thesledgehammer.groovymc.api.GroovyLoader
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.ItemMeshDefinition
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.ItemStack

class GroovyStateMapperBase extends StateMapperBase implements ItemMeshDefinition {

    final ModelResourceLocation location;

    GroovyStateMapperBase(String modId, String fileName, String modelName) {
        this.location = new ModelResourceLocation(modId + ":" + fileName, modelName);
    }

    GroovyStateMapperBase(String fileName, String modelName) {
        this.location = new ModelResourceLocation(GroovyLoader.Instance().getModID() + ":" + fileName, modelName);
    }

    @Override
    ModelResourceLocation getModelLocation(ItemStack stack) {
        return location
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return location;
    }
}
