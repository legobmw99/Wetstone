package com.legobmw99.Wetstone;

import com.legobmw99.Wetstone.block.WetstoneBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Wetstone.MODID)
public class Wetstone {
    public static final String MODID = "wetstone";

    @ObjectHolder("wetstone:wetstone")
    public static WetstoneBlock blockWetstone;


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        
        @SubscribeEvent
        public static void onBlockRegister(final RegistryEvent.Register<Block> e) {
            e.getRegistry().register(new WetstoneBlock());
        }

        @SubscribeEvent
        public static void onItemRegister(final RegistryEvent.Register<Item> e) {
            Item.Properties properties = new Item.Properties()
                    .group(ItemGroup.BUILDING_BLOCKS);
            e.getRegistry().register(new BlockItem(blockWetstone, properties).setRegistryName(blockWetstone.getRegistryName()));
        }
    }

}
