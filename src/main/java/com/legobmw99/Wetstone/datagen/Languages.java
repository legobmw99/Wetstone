package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import com.legobmw99.Wetstone.block.WetstoneBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class Languages extends LanguageProvider {
    public Languages(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(Wetstone.STONE_BRICKS.get(), "Wetstone");
        addLore(Wetstone.STONE_BRICKS.get(), "Stone Bricks");
        add(Wetstone.NETHER_BRICKS.get(), "Wetstone");
        addLore(Wetstone.NETHER_BRICKS.get(), "Nether Bricks");
        add(Wetstone.SANDSTONE.get(), "Wetstone");
        addLore(Wetstone.SANDSTONE.get(), "Sandstone");
    }

    private void addLore(Block key, String lore){
        add(key.getTranslationKey() + ".lore", lore);
    }

    @Override
    public String getName() {
        return "Wetstone Language";
    }
}