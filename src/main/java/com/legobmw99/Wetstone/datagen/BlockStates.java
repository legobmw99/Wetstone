package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        singleTextureBlock(Wetstone.STONE_BRICKS.get(), "wetstone", "block/wetstone");

    }


    private void singleTextureBlock(Block block, String model, String texture) {
        Wetstone.LOGGER.debug("Creating Block Data for " + block.getRegistryName());
        simpleBlock(block);
    }


    @Override
    public String getName() {
        return "Wetstone Blockstates";
    }
}
