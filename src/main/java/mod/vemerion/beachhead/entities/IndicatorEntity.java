package mod.vemerion.beachhead.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class IndicatorEntity extends Entity {

	private PlayerEntity owner;
	private float startYaw;
	private float startPitch;

	public IndicatorEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setNoGravity(true);
	}

	public IndicatorEntity(EntityType<?> entityTypeIn, World worldIn, PlayerEntity owner) {
		super(entityTypeIn, worldIn);
		this.setNoGravity(true);
		this.owner = owner;
		this.startYaw = owner.rotationYaw;
		this.startPitch = owner.rotationPitch;
	}

	@Override
	public void tick() {
		super.tick();
		if (!world.isRemote && (owner == null || (Math.abs(owner.rotationYaw - startYaw) > 0.0001f
				|| Math.abs(owner.rotationPitch - startPitch) > 0.0001f))) {
			remove();
		}
	}

	@Override
	protected void registerData() {
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
	}

}
