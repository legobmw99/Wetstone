package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Wetstone.STONE_BRICKS.get());
        simpleBlock(Wetstone.NETHER_BRICKS.get());
        simpleBottomTopBlock(Wetstone.SANDSTONE.get());
    }

    private void simpleBottomTopBlock(Block b) {
        ResourceLocation t = blockTexture(b);
        ModelFile m = models().cubeBottomTop(t.getPath(), t, extend(t, "_bottom"), extend(t, "_top"));
        simpleBlock(b, m);
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    @Override
    public String getName() {
        return "Wetstone Blockstates";
    }
}
