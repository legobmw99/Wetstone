package com.legobmw99.Wetstone.datagen;

import com.legobmw99.Wetstone.Wetstone;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    private final Map<Character, Ingredient> defaultIngredients = new HashMap<>();

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
        add('b', Items.WATER_BUCKET);
        add('i', Tags.Items.NUGGETS_IRON);
        add('s', Items.STONE_BRICKS);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        buildShaped(consumer, Wetstone.STONE_BRICKS.get(), 4, Items.WATER_BUCKET, "sis", "ibi", "sis");

    }


    protected void buildShaped(Consumer<IFinishedRecipe> consumer, IItemProvider result, int count, Item criterion, String... lines) {
        Wetstone.LOGGER.debug("Creating Shaped Recipe for " + result.asItem().getRegistryName());

        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shapedRecipe(result, count);

        builder.addCriterion("has_" + criterion.getRegistryName().getPath(), InventoryChangeTrigger.Instance.forItems(criterion));

        Set<Character> characters = new HashSet<>();
        for (String line : lines) {
            builder.patternLine(line);
            line.chars().forEach(value -> characters.add((char) value));
        }

        for (Character c : characters) {
            if (defaultIngredients.containsKey(c)) {
                builder.key(c, defaultIngredients.get(c));
            }
        }

        builder.build(consumer);
    }




    protected void add(char c, Tag<Item> itemTag) {
        defaultIngredients.put(c, Ingredient.fromTag(itemTag));
    }

    protected void add(char c, IItemProvider itemProvider) {
        defaultIngredients.put(c, Ingredient.fromItems(itemProvider));
    }

    protected void add(char c, Ingredient ingredient) {
        defaultIngredients.put(c, ingredient);
    }

    protected Ingredient ing(Tag<Item> itemTag) {
        return Ingredient.fromTag(itemTag);
    }

    protected Ingredient ing(IItemProvider itemProvider) {
        return Ingredient.fromItems(itemProvider);
    }

    protected Ingredient ing(Ingredient ingredient) {
        return ingredient;
    }

    @Override
    public String getName() {
        return "Wetstone Recipes";
    }
}
