package mod.vemerion.beachhead.entities;

import mod.vemerion.beachhead.Beachhead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class ScrollProjectileEntity extends Entity {

	private Vec3d startPosition;
	private float startPitch;
	private float startYaw;

	public ScrollProjectileEntity(EntityType<? extends ScrollProjectileEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public ScrollProjectileEntity(World worldIn, Vec3d startPosition, float startPitch, float startYaw) {
		super(Beachhead.SCROLL_PROJECTILE_ENTITY, worldIn);
		this.startPosition = startPosition;
		this.startPitch = startPitch;
		this.startYaw = startYaw;
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();

		if (!world.isRemote) {
			if (startPosition == null) {
				remove();
			} else {
				// Move
				Vec3d position = projectilePosition(ticksExisted / 5, startPosition, startPitch, startYaw);
				setPosition(position.getX(), position.getY(), position.getZ());

				// Kill crabs and birds
				for (Entity e : world.getEntitiesInAABBexcluding(this, getBoundingBox(),
						(e) -> e instanceof BeachheadCrabEntity || e instanceof BeachheadAlbatrossEntity)) {
					e.attackEntityFrom(DamageSource.MAGIC, Integer.MAX_VALUE);
					world.playSound(null, getPosition(), Beachhead.PROJECTILE_IMPACT_SOUND,
							SoundCategory.PLAYERS, 1, rand.nextFloat() * 0.2f + 0.8f);
					remove();
				}

				// Particles
				ServerWorld serverWorld = (ServerWorld) world;
				for (int i = 0; i < 20; i++) {
					Vec3d particlePosition = getPositionVec().add(rand.nextDouble() * 0.5 - 0.25, rand.nextDouble() * 0.5 - 0.25,
							rand.nextDouble() * 0.5 - 0.25);
					serverWorld.spawnParticle(Beachhead.YELLOW_SMOKE_PARTICLE_TYPE, particlePosition.getX(), particlePosition.getY(),
							particlePosition.getZ(), 1, 0, 0, 0, 0.1);
				}
				
				if (!world.isAirBlock(getPosition())) {
					world.playSound(null, getPosition(), Beachhead.PROJECTILE_IMPACT_SOUND,
							SoundCategory.PLAYERS, 1, rand.nextFloat() * 0.2f + 0.8f);
					remove();

				}
			}
		}
	}

	public static Vec3d projectilePosition(float time, Vec3d start, float pitch, float yaw) {
		float horizontal = MathHelper.cos((float) Math.toRadians(30 - pitch));
		float vertical = MathHelper.sin((float) Math.toRadians(30 - pitch));
		float x = -MathHelper.sin((float) Math.toRadians(yaw));
		float z = MathHelper.cos((float) Math.toRadians(yaw));
		float g = 0.3f;
		float horizontalSpeed = 1.2f + (float)MathHelper.clampedLerp(0, 3, pitch / -70);
		float verticalSpeed = 1.4f;
		Vec3d position = new Vec3d(start.getX() + (time + 1) * horizontalSpeed * horizontal * x,
				start.getY() + 1 + time * verticalSpeed * vertical - 0.5 * g * time * time,
				start.getZ() + (time + 1) * horizontalSpeed * horizontal * z);
		return position;
	}

	public static float projectilePitch(Vec3d position, Vec3d nextPosition, float pitch) {
		float horizontal = MathHelper.cos((float) Math.toRadians(30 - pitch));
		float horizontalSpeed = 1.2f + (float)MathHelper.clampedLerp(0, 3, pitch / -70);

		nextPosition = nextPosition.subtract(position).normalize();

		return (float) (MathHelper.atan2(nextPosition.getY(), horizontalSpeed * horizontal)
				* (double) (180F / (float) Math.PI));
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

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
