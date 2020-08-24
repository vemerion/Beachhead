package mod.vemerion.beachhead.entities;

import mod.vemerion.beachhead.Beachhead;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class BeachheadAlbatrossEntity extends CreatureEntity {


	public BeachheadAlbatrossEntity(EntityType<? extends BeachheadAlbatrossEntity> type, World worldIn) {
		super(type, worldIn);
		setNoGravity(true);
	}

	@Override
	public void registerAttributes() {
		super.registerAttributes();
		getAttribute(LivingEntity.ENTITY_GRAVITY).setBaseValue(0);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return getRNG().nextBoolean() ? Beachhead.BIRD1_SOUND : Beachhead.BIRD2_SOUND;
	}
	
	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BeachheadGoals.KillTurtleOnContact(this));
		this.goalSelector.addGoal(1, new BeachheadGoals.SwoopDownOnTurtle(this, 0.2f));
		this.goalSelector.addGoal(2, new BeachheadGoals.MoveForwardGoal(this, 0.06f));
	}
	
	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}
}
