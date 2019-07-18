package com.thesledgehammer.groovymc.blocks

import net.minecraft.block.Block

interface IBlockProperties {

    Block.Properties getBlockProperties();

    void setBlockProperties(Block.Properties blockProperties);
}