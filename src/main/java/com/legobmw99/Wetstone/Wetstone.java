package com.legobmw99.Wetstone;

import com.legobmw99.Wetstone.block.BlockWetstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Wetstone.MODID, version = Wetstone.VERSION)
public class Wetstone {
	public static final String MODID = "wetstone";
	public static final String VERSION = "1.0";
	public static Block blockWetstone;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(blockWetstone), 0,
				new ModelResourceLocation("wetstone:wetstone", "inventory"));
	}

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(new ItemBlock(blockWetstone).setRegistryName(blockWetstone.getRegistryName()));
	}

	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> e) {
		blockWetstone = new BlockWetstone();
		e.getRegistry().register(blockWetstone);
	}
}
