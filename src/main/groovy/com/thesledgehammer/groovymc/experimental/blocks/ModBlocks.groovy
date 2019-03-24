package com.thesledgehammer.groovymc.experimental.blocks

import com.thesledgehammer.groovymc.GroovyMC
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary

import javax.annotation.Nullable

class ModBlocks {

    static void init() {

    }

    protected static <T extends Block> T registerBlock(T block, @Nullable ItemBlock itemBlock, String name) {
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        GroovyMC.registerBlock(block);

        if(itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
            GroovyMC.registerItem(itemBlock);
        }
        return block;
    }

    protected static <T extends Block> T registerBlock(T block, String name) {
        return registerBlock(block, null, name);
    }

    protected static void registerOreDictWildcard(String oreDictName, Block block) {
        OreDictionary.registerOre(oreDictName, new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE));
    }
}
