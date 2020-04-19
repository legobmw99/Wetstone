package com.legobmw99.Wetstone.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class LootTables extends LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private DataGenerator gen;
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        gen = dataGeneratorIn;
    }

    private void addBlockTables() {
        addSimpleBlock("wetstone", Wetstone.STONE_BRICKS.get());

    }

    // Useful boilerplate from McJtyLib
    protected void addSimpleBlock(String name, Block block) {
        Wetstone.LOGGER.debug("Creating Loot Table for block " + block.getRegistryName());
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block))
                .acceptCondition(SurvivesExplosion.builder());

        lootTables.put(block, LootTable.builder().addLootPool(builder));
    }

    @Override
    public void act(DirectoryCache cache) {
        addBlockTables();

        Map<ResourceLocation, LootTable> tables;
        tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }

        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.gen.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                Wetstone.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "Wetstone Loot Tables";
    }
}