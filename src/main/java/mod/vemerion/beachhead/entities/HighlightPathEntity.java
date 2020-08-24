package mod.vemerion.beachhead.entities;

import mod.vemerion.beachhead.Beachhead;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HighlightPathEntity extends IndicatorEntity {

	public HighlightPathEntity(EntityType<? extends HighlightPathEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public HighlightPathEntity(World worldIn, PlayerEntity owner) {
		super(Beachhead.HIGHLIGHT_PATH_ENTITY, worldIn, owner);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
