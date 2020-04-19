package com.legobmw99.Wetstone.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class WetstoneBlock extends Block {

    public WetstoneBlock() {
         this(Material.IRON, 2.0f, SoundType.STONE);
    }

    public WetstoneBlock(Material mat){
        this(mat,2.0f,SoundType.STONE);
    }

    public WetstoneBlock(Material mat, float resistance, SoundType sound){
        super(Properties.create(mat).hardnessAndResistance(resistance).sound(sound));
    }
    
    @Override
    public IFluidState getFluidState(BlockState state) {
        return Fluids.WATER.getFlowingFluidState(7, false);
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return false;
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }

    @Override
    public void onBlockAdded(BlockState p_220082_1_, World worldIn, BlockPos pos, BlockState p_220082_4_, boolean p_220082_5_) {
        this.checkForMixing(worldIn, pos, p_220082_1_);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_) {
        this.checkForMixing(worldIn, pos, state);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ITextComponent lore = new TranslationTextComponent(this.getTranslationKey() + ".lore");
        lore.setStyle(lore.getStyle().setColor(TextFormatting.GRAY));
        tooltip.add(lore);
    }

    public boolean checkForMixing(World worldIn, BlockPos pos, BlockState state) {

        boolean flag = false;
        Direction facing = null;
        for (Direction dir : Direction.values()) {
            if (worldIn.getBlockState(pos.offset(dir)).getMaterial() == Material.LAVA) {
                facing = dir;
                flag = true;
                break;
            }
        }

        if (flag) {
            Integer integer = (Integer) worldIn.getBlockState(pos.offset(facing)).getValues().get(FlowingFluidBlock.LEVEL);

            if (integer.intValue() == 0) {
                worldIn.setBlockState(pos.offset(facing), Blocks.OBSIDIAN.getDefaultState());
                this.triggerMixEffects(worldIn, pos.offset(facing));
                return true;
            }

            if (integer.intValue() <= 4) {
                worldIn.setBlockState(pos.offset(facing), Blocks.COBBLESTONE.getDefaultState());
                this.triggerMixEffects(worldIn, pos.offset(facing));
                return true;
            }
        }

        return false;
    }

    protected void triggerMixEffects(World worldIn, BlockPos pos) {
        double d0 = (double) pos.getX();
        double d1 = (double) pos.getY();
        double d2 = (double) pos.getZ();
        worldIn.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
                2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i) {
            worldIn.addOptionalParticle(ParticleTypes.LARGE_SMOKE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(),
                    0.0D, 0.0D, 0.0D);

        }
    }
}
