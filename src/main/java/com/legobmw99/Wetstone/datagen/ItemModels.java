package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        parentedBlock(Wetstone.STONE_BRICKS.get(), "block/wet_stone_bricks");
        parentedBlock(Wetstone.NETHER_BRICKS.get(), "block/wet_nether_bricks");
        parentedBlock(Wetstone.SANDSTONE.get(), "block/wet_sandstone");
    }

    public void parentedBlock(Block block, String model) {
        Wetstone.LOGGER.debug("Creating Item Model for " + block.getRegistryName());
        getBuilder(block.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc(model)));
    }

    @Override
    public String getName() {
        return "Wetstone Item Models";
    }
}