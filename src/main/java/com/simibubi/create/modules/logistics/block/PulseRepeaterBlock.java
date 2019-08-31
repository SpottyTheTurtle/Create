package com.simibubi.create.modules.logistics.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

public class PulseRepeaterBlock extends RedstoneDiodeBlock {

	public static BooleanProperty PULSING = BooleanProperty.create("pulsing");

	public PulseRepeaterBlock() {
		super(Properties.from(Blocks.REPEATER));
		setDefaultState(getDefaultState().with(PULSING, false).with(POWERED, false));
	}

	@Override
	protected int getDelay(BlockState state) {
		return 1;
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		boolean powered = state.get(POWERED);
		boolean pulsing = state.get(PULSING);
		boolean shouldPower = shouldBePowered(worldIn, pos, state);

		if (pulsing) {
			worldIn.setBlockState(pos, state.with(POWERED, true).with(PULSING, false), 2);
		} else if (powered && !shouldPower) {
			worldIn.setBlockState(pos, state.with(POWERED, false).with(PULSING, false), 2);
		} else if (!powered) {
			worldIn.setBlockState(pos, state.with(POWERED, true).with(PULSING, true), 2);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.getDelay(state), TickPriority.HIGH);
		}

	}

	@Override
	protected int getActiveSignal(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return state.get(PULSING) ? 15 : 0;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, POWERED, PULSING);
		super.fillStateContainer(builder);
	}

}
