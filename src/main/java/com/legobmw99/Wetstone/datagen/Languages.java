package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class Languages extends LanguageProvider {
    public Languages(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(Wetstone.STONE_BRICKS.get(), "Wetstone");
    }

    @Override
    public String getName() {
        return "Wetstone Language";
    }
}