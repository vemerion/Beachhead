package mod.vemerion.beachhead.entities;

import java.util.EnumSet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;

public class BeachheadGoals {
	public static class MoveForwardGoal extends Goal {
		private LivingEntity entity;
		private float speed;

		public MoveForwardGoal(LivingEntity entity, float speed) {
			this.entity = entity;
			this.speed = speed;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void tick() {
			Vec3d motion = entity.getMotion();
			Vec3d direction = Vec3d.fromPitchYaw(0, entity.rotationYaw);
			entity.setMotion(speed * direction.getX(), motion.getY(), speed * direction.getZ());
		}
	}

	public static class KillTurtleOnContact extends Goal {
		private LivingEntity entity;

		public KillTurtleOnContact(LivingEntity entity) {
			this.entity = entity;
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void tick() {
			for (Entity turtle : entity.world.getEntitiesInAABBexcluding(entity, entity.getBoundingBox(),
					(e) -> e instanceof BeachheadTurtleEntity)) {
				turtle.attackEntityFrom(DamageSource.causeMobDamage(entity), Integer.MAX_VALUE);
			}
		}
	}

	public static class SwoopDownOnTurtle extends Goal {
		private LivingEntity entity;
		private double height;
		private boolean shouldContinue = true;
		private boolean rising;
		private float speed;

		public SwoopDownOnTurtle(LivingEntity entity, float speed) {
			this.entity = entity;
			this.speed = speed;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			return !entity.world.getEntitiesInAABBexcluding(entity, entity.getBoundingBox().expand(0, -10, 0),
					(e) -> e instanceof BeachheadTurtleEntity).isEmpty();
		}

		@Override
		public void startExecuting() {
			shouldContinue = true;
			rising = false;
			height = entity.getPosY();
		}

		@Override
		public boolean shouldContinueExecuting() {
			return shouldContinue;
		}

		@Override
		public void tick() {
			Vec3d motion = entity.getMotion();
			if (rising) {
				if (entity.getPosY() >= height) {
					shouldContinue = false;
					entity.setMotion(motion.getX(), 0, motion.getZ());
				} else {
					entity.setMotion(motion.getX(), speed, motion.getZ());
				}
			} else {
				entity.setMotion(motion.getX(), -speed, motion.getZ());
				if (entity.onGround)
					rising = true;
			}
		}
	}
}
