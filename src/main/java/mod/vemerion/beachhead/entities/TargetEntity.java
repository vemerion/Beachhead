package mod.vemerion.beachhead.entities;

import mod.vemerion.beachhead.Beachhead;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TargetEntity extends IndicatorEntity {
	

	public TargetEntity(EntityType<? extends TargetEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public TargetEntity(World worldIn, PlayerEntity owner) {
		super(Beachhead.TARGET_ENTITY, worldIn, owner);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
