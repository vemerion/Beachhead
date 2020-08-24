package mod.vemerion.beachhead.entities;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.BeachheadWorldType;
import mod.vemerion.beachhead.capability.BeachheadGame;
import mod.vemerion.beachhead.capability.BeachheadGameMessage;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class BeachheadTurtleEntity extends CreatureEntity {

	private int ticksInWater;

	public BeachheadTurtleEntity(EntityType<? extends BeachheadTurtleEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BeachheadGoals.MoveForwardGoal(this, 0.01f));
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (!world.isRemote) {
			if (world.getWorldType() instanceof BeachheadWorldType) {
				BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
						.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
				game.killTurtle();
				BeachheadGameMessage.INSTANCE.send(PacketDistributor.ALL.noArg(), new BeachheadGameMessage(
						game.getScore(), game.getTurtleMadeItTimer(), game.getCycleOfLifeTimer()));
			}
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!world.isRemote) {
			if (isInWater()) {
				ticksInWater++;
			}

			// A TURTLE MADE IT TO THE WATER!
			if (ticksInWater == 15) {
				if (world.getWorldType() instanceof BeachheadWorldType) {
					BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
							.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
					game.saveTurtle();
					BeachheadGameMessage.INSTANCE.send(PacketDistributor.ALL.noArg(), new BeachheadGameMessage(
							game.getScore(), game.getTurtleMadeItTimer(), game.getTurtleMadeItTimer()));
				}
				remove();
			}
		}
	}
}
