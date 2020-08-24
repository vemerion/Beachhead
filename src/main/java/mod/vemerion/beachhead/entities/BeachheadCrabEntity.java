package mod.vemerion.beachhead.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BeachheadCrabEntity extends CreatureEntity {

	private int ticksInWater;

	public BeachheadCrabEntity(EntityType<? extends BeachheadCrabEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		// Remove crab if in water
		if (!world.isRemote) {
			if (isInWater()) {
				ticksInWater++;
			}

			if (ticksInWater == 15) {
				remove();
			}
		}
	}
	
	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BeachheadGoals.MoveForwardGoal(this, 0.02f));
		this.goalSelector.addGoal(0, new BeachheadGoals.KillTurtleOnContact(this));
	}
}
