package mod.vemerion.beachhead;

import mod.vemerion.beachhead.capability.BeachheadGame;
import mod.vemerion.beachhead.entities.BeachheadAlbatrossEntity;
import mod.vemerion.beachhead.entities.BeachheadCrabEntity;
import mod.vemerion.beachhead.entities.BeachheadTurtleEntity;
import mod.vemerion.beachhead.entities.HighlightPathEntity;
import mod.vemerion.beachhead.entities.ScrollProjectileEntity;
import mod.vemerion.beachhead.entities.TargetEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(Beachhead.MODID)
public class Beachhead {
	public static final String MODID = "beachhead";
	
	@ObjectHolder(MODID + ":scroll")
	public static final Item SCROLL = null;
	
	@ObjectHolder(MODID + ":highlight_path_entity")
	public static final EntityType<HighlightPathEntity> HIGHLIGHT_PATH_ENTITY = null;
	
	@ObjectHolder(MODID + ":target_entity")
	public static final EntityType<TargetEntity> TARGET_ENTITY = null;

	@ObjectHolder(MODID + ":beachhead_turtle_entity")
	public static final EntityType<BeachheadTurtleEntity> BEACHHEAD_TURTLE_ENTITY = null;
	
	@ObjectHolder(MODID + ":beachhead_crab_entity")
	public static final EntityType<BeachheadCrabEntity> BEACHHEAD_CRAB_ENTITY = null;

	@ObjectHolder(MODID + ":beachhead_albatross_entity")
	public static final EntityType<BeachheadAlbatrossEntity> BEACHHEAD_ALBATROSS_ENTITY = null;
	
	@ObjectHolder(MODID + ":scroll_projectile_entity")
	public static final EntityType<ScrollProjectileEntity> SCROLL_PROJECTILE_ENTITY = null;
	
	@ObjectHolder(Beachhead.MODID + ":yellow_smoke_particle_type")
	public static final BasicParticleType YELLOW_SMOKE_PARTICLE_TYPE = null;
	
	@ObjectHolder(Beachhead.MODID + ":bird1_sound")
	public static final SoundEvent BIRD1_SOUND = null;
	
	@ObjectHolder(Beachhead.MODID + ":bird2_sound")
	public static final SoundEvent BIRD2_SOUND = null;

	@ObjectHolder(Beachhead.MODID + ":projectile_impact_sound")
	public static final SoundEvent PROJECTILE_IMPACT_SOUND = null;

	@ObjectHolder(Beachhead.MODID + ":projectile_launch_sound")
	public static final SoundEvent PROJECTILE_LAUNCH_SOUND = null;
	
	public static final BeachheadWorldType BEACHHEAD_WORLD_TYPE = new BeachheadWorldType();
	
	@CapabilityInject(BeachheadGame.class)
	public static final Capability<BeachheadGame> BEACHHEADGAME_CAP = null;
}
