package com.thesledgehammer.groovymc.fluids

import com.thesledgehammer.groovymc.api.IInitModel
import com.thesledgehammer.groovymc.utils.GroovyLoader
import com.thesledgehammer.groovymc.utils.GroovyStateMapperBase
import net.minecraft.block.material.Material
import net.minecraft.item.Item
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class GroovyBlockFluid extends BlockFluidClassic implements IInitModel {

    private String name;
    private String modID;

    GroovyBlockFluid(Fluid fluid, Material material, String name, String modID) {
        super(fluid, material);
        this.name = name;
        this.modID = modID;
    }

    GroovyBlockFluid(Fluid fluid, Material material, String name) {
        this(fluid, material, name, GroovyLoader.Instance().getModID());
    }

    @Override
    boolean canDisplace(IBlockAccess world, BlockPos pos) {
        if(world.getBlockState(pos).getMaterial().isLiquid()) {
            return false;
        }
        return super.canDisplace(world, pos);
    }

    @Override
    boolean displaceIfPossible(World world, BlockPos pos) {
        if(world.getBlockState(pos).getMaterial().isLiquid()) {
            return false;
        }
        return super.displaceIfPossible(world, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    void initModel() {
        Item item = Item.getItemFromBlock(this);
        if(modID == null) {
            modID = GroovyLoader.Instance().getModID();
        }
        GroovyStateMapperBase mapper = new GroovyStateMapperBase(modID,"fluids", name);
        ModelLoader.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        ModelLoader.setCustomStateMapper(this, mapper);
    }
}
