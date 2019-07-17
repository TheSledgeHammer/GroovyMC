package com.thesledgehammer.groovymc


import com.thesledgehammer.groovymc.blocks.GroovyBlockTileBasic
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockReader

import javax.annotation.Nullable

class Block1 extends GroovyBlockTileBasic {

    Block1() {
        super(Material.IRON)
    }

    @Override
    boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityTest1(Registry.TEST);
    }
}
