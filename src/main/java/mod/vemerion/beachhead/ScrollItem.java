package mod.vemerion.beachhead;

import mod.vemerion.beachhead.entities.ScrollProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ScrollItem extends Item {

	public ScrollItem(Properties properties) {
		super(properties);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(),
				Beachhead.PROJECTILE_LAUNCH_SOUND, SoundCategory.PLAYERS, 1, 0.9f + random.nextFloat() * 0.2f - 0.1f);
		playerIn.getCooldownTracker().setCooldown(this, 20);

		if (!worldIn.isRemote) {
			ScrollProjectileEntity projectile = new ScrollProjectileEntity(worldIn, playerIn.getPositionVec(),
					playerIn.rotationPitch, playerIn.rotationYaw);
			worldIn.addEntity(projectile);
		}
		return ActionResult.resultSuccess(itemstack);
	}

}
