package mod.vemerion.beachhead;

import mod.vemerion.beachhead.capability.BeachheadGame;
import mod.vemerion.beachhead.capability.BeachheadGameMessage;
import mod.vemerion.beachhead.capability.BeachheadGameStorage;
import mod.vemerion.beachhead.entities.BeachheadAlbatrossEntity;
import mod.vemerion.beachhead.entities.BeachheadCrabEntity;
import mod.vemerion.beachhead.entities.BeachheadTurtleEntity;
import mod.vemerion.beachhead.entities.HighlightPathEntity;
import mod.vemerion.beachhead.entities.ScrollProjectileEntity;
import mod.vemerion.beachhead.entities.TargetEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Beachhead.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(setup(new ScrollItem(new Item.Properties()), "scroll"));
	}

	@SubscribeEvent
	public static void onRegisterEntity(RegistryEvent.Register<EntityType<?>> event) {
		EntityType<HighlightPathEntity> missileEntity = EntityType.Builder
				.<HighlightPathEntity>create(HighlightPathEntity::new, EntityClassification.MISC).size(0.4f, 0.4f)
				.build("highlight_path_entity");
		EntityType<TargetEntity> targetEntity = EntityType.Builder
				.<TargetEntity>create(TargetEntity::new, EntityClassification.MISC).size(1, 1).build("target_entity");
		EntityType<BeachheadTurtleEntity> beachheadTurtleEntity = EntityType.Builder
				.<BeachheadTurtleEntity>create(BeachheadTurtleEntity::new, EntityClassification.MISC).size(1, 1)
				.build("beachhead_turtle_entity");
		EntityType<BeachheadCrabEntity> beachheadCrabEntity = EntityType.Builder
				.<BeachheadCrabEntity>create(BeachheadCrabEntity::new, EntityClassification.MISC).size(1, 1)
				.build("beachhead_crab_entity");
		EntityType<BeachheadAlbatrossEntity> beachheadAlbatrossEntity = EntityType.Builder
				.<BeachheadAlbatrossEntity>create(BeachheadAlbatrossEntity::new, EntityClassification.MISC).size(1, 1)
				.build("beachhead_albatross_entity");
		EntityType<ScrollProjectileEntity> scrollProjectileEntity = EntityType.Builder
				.<ScrollProjectileEntity>create(ScrollProjectileEntity::new, EntityClassification.MISC).size(1, 1)
				.build("scroll_projectile_entity");


		event.getRegistry().register(setup(missileEntity, "highlight_path_entity"));
		event.getRegistry().register(setup(targetEntity, "target_entity"));
		event.getRegistry().register(setup(beachheadTurtleEntity, "beachhead_turtle_entity"));
		event.getRegistry().register(setup(beachheadCrabEntity, "beachhead_crab_entity"));
		event.getRegistry().register(setup(beachheadAlbatrossEntity, "beachhead_albatross_entity"));
		event.getRegistry().register(setup(scrollProjectileEntity, "scroll_projectile_entity"));

	}

	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(BeachheadGame.class, new BeachheadGameStorage(), BeachheadGame::new);

		BeachheadGameMessage.INSTANCE.registerMessage(0, BeachheadGameMessage.class, BeachheadGameMessage::encode,
				BeachheadGameMessage::decode, BeachheadGameMessage::handle);

	}
	
	@SubscribeEvent
	public static void onIParticleTypeRegistration(RegistryEvent.Register<ParticleType<?>> event) {
		BasicParticleType yellowSmoke = new BasicParticleType(true);
		event.getRegistry().register(setup(yellowSmoke, "yellow_smoke_particle_type"));
	}
	
	@SubscribeEvent
	public static void onRegisterSound(RegistryEvent.Register<SoundEvent> event) {
		SoundEvent bird1Sound = new SoundEvent(new ResourceLocation(Beachhead.MODID, "bird1_sound"));
		SoundEvent bird2Sound = new SoundEvent(new ResourceLocation(Beachhead.MODID, "bird2_sound"));
		SoundEvent projectileImpactSound = new SoundEvent(new ResourceLocation(Beachhead.MODID, "projectile_impact_sound"));
		SoundEvent projectileLaunchSound = new SoundEvent(new ResourceLocation(Beachhead.MODID, "projectile_launch_sound"));

		event.getRegistry().register(setup(bird1Sound, "bird1_sound"));
		event.getRegistry().register(setup(bird2Sound, "bird2_sound"));
		event.getRegistry().register(setup(projectileImpactSound, "projectile_impact_sound"));
		event.getRegistry().register(setup(projectileLaunchSound, "projectile_launch_sound"));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Beachhead.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}

}
