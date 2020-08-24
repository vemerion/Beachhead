package mod.vemerion.beachhead;

import mod.vemerion.beachhead.entities.ScrollProjectileEntity;
import mod.vemerion.beachhead.renderers.BeachheadAlbatrossRenderer;
import mod.vemerion.beachhead.renderers.BeachheadCrabRenderer;
import mod.vemerion.beachhead.renderers.BeachheadTurtleRenderer;
import mod.vemerion.beachhead.renderers.HighlightPathRenderer;
import mod.vemerion.beachhead.renderers.TargetRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Beachhead.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onRegister(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.HIGHLIGHT_PATH_ENTITY,
				(renderManager) -> new HighlightPathRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.TARGET_ENTITY,
				(renderManager) -> new TargetRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.BEACHHEAD_TURTLE_ENTITY,
				(renderManager) -> new BeachheadTurtleRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.BEACHHEAD_CRAB_ENTITY,
				(renderManager) -> new BeachheadCrabRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.BEACHHEAD_ALBATROSS_ENTITY,
				(renderManager) -> new BeachheadAlbatrossRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(Beachhead.SCROLL_PROJECTILE_ENTITY,
				(renderManager) -> new EntityRenderer<ScrollProjectileEntity>(renderManager) {
					@Override
					public ResourceLocation getEntityTexture(ScrollProjectileEntity entity) {
						return null;
					}
				});
	}

	@SubscribeEvent
	public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(Beachhead.YELLOW_SMOKE_PARTICLE_TYPE,
				sprite -> new YellowSmokeParticle.Factory(sprite));
	}
}
