package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        parentedBlock(Wetstone.STONE_BRICKS.get(), "block/wet_stone_bricks");
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