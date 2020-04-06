package io.github.vampirestudios.raa.blocks;

import io.github.vampirestudios.raa.generation.dimensions.data.DimensionData;
import io.github.vampirestudios.raa.utils.Rands;
import io.github.vampirestudios.vampirelib.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static io.github.vampirestudios.raa.RandomlyAddingAnything.MOD_ID;

public class DimensionalStone extends Block {
    private final DimensionData dimensionData;

    public DimensionalStone(DimensionData dimensionData) {
        super(Settings.copy(Blocks.STONE).strength(dimensionData.getStoneHardness(), dimensionData.getStoneResistance()));
        this.dimensionData = dimensionData;
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Registry.BLOCK.get(Utils.appendToPath(dimensionData.getId(), "_cobblestone")).asItem()));
        return list;
    }
}
