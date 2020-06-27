package com.legobmw99.Wetstone;

import com.legobmw99.Wetstone.block.WetstoneBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Wetstone.MODID)
public class Wetstone {
    public static final String MODID = "wetstone";
    public static final Logger LOGGER = LogManager.getLogger();

    private static final Item.Properties ITEM_PROP = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Wetstone.MODID);
    public static final DeferredRegister<Item> ITEMS =  DeferredRegister.create(ForgeRegistries.ITEMS, Wetstone.MODID);

    // todo look into data fixers
    public static final RegistryObject<Block> STONE_BRICKS = BLOCKS.register("wet_stone_bricks", WetstoneBlock::new);
    public static final RegistryObject<Item> STONE_BRICKS_ITEM = ITEMS.register("wet_stone_bricks", () -> new BlockItem(STONE_BRICKS.get(), ITEM_PROP));

    public static final RegistryObject<Block> NETHER_BRICKS = BLOCKS.register("wet_nether_bricks", WetstoneBlock::new);
    public static final RegistryObject<Item> NETHER_BRICKS_ITEM = ITEMS.register("wet_nether_bricks", () -> new BlockItem(NETHER_BRICKS.get(), ITEM_PROP));

    public static final RegistryObject<Block> SANDSTONE = BLOCKS.register("wet_sandstone", WetstoneBlock::new);
    public static final RegistryObject<Item> SANDSTONE_ITEM = ITEMS.register("wet_sandstone", () -> new BlockItem(SANDSTONE.get(), ITEM_PROP));

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public Wetstone() {
        Wetstone.register();
    }

}
