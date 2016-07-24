package net.tylian.musicalis.common.items.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.tylian.musicalis.common.constants.ConstItemNames;
import net.tylian.musicalis.common.items.BaseItem;

public class ItemGrassHorn extends BaseItem {
	static final int MAX_TICKS = 120;
	Random random = new Random();

	public ItemGrassHorn() {
		super(ConstItemNames.GRASS_HORN);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player,
			EnumHand hand) {
		if (world.isRemote && Minecraft.getMinecraft().currentScreen != null) {
			return new ActionResult(EnumActionResult.FAIL, itemStack);
		} else {
			player.setActiveHand(hand);
			return new ActionResult(EnumActionResult.PASS, itemStack);
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int time) {
		if (!player.worldObj.isRemote) {
			int duration = getMaxItemUseDuration(stack) - time;
			double percent = duration / MAX_TICKS;
			
			// quadratic ease-out!
			if(duration > MAX_TICKS || random.nextDouble() <  -0.80 * percent * (percent - 2) + 0.20)
				breakGrass((EntityPlayer) player, player.worldObj, player.getPosition(), duration * 20);
	
			player.worldObj.playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_BASS, SoundCategory.NEUTRAL,
					1F, 0.001F);
		}
	}

	// TODO
	public boolean canAffectBlock(World world, BlockPos position) {
		IBlockState state = world.getBlockState(position);
		Block block = state.getBlock();
		return block instanceof BlockBush;
	}

	public void breakGrass(EntityPlayer player, World world, BlockPos src, int amount) {
		int range = 12;
		int rangeY = 3;
		
		List<BlockPos> targets = new ArrayList();
		for (int x = -range; x < range + 1; x++) {
			for (int y = -rangeY; y < rangeY + 1; y++) {
				for (int z = -range; z < range + 1; z++) {
					BlockPos position = src.add(x, y, z);

					if (canAffectBlock(world, position))
						targets.add(position);
				}
			}
		}
		
		if(targets.size() == 0)
			return;
		
		BlockPos targetPos = targets.get(random.nextInt(targets.size()));
		IBlockState state = world.getBlockState(targetPos);
		Block block = state.getBlock();

		// TODO add API stuff
		BreakEvent event = new BreakEvent(world, targetPos, state, player);
		MinecraftForge.EVENT_BUS.post(event);
		if (!event.isCanceled()) {
			if (block.removedByPlayer(state, world, targetPos, player, true)) {
				block.onBlockDestroyedByPlayer(world, targetPos, state);
				block.harvestBlock(world, player, targetPos, state,
						world.getTileEntity(targetPos), null);
			}
		}
		
		world.playEvent(2001, targetPos, Block.getStateId(state));
	}
}
