package mod.vemerion.beachhead;

import com.mojang.blaze3d.systems.RenderSystem;

import mod.vemerion.beachhead.capability.BeachheadGame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Beachhead.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

	@SubscribeEvent
	public static void restrictCamera(CameraSetup event) {
		if (Minecraft.getInstance().world.getWorldType() instanceof BeachheadWorldType) {
			event.setPitch(MathHelper.clamp(event.getPitch(), -40, 85));
			event.setYaw(MathHelper.clamp(event.getYaw(), -89, 89));
		}
	}

	@SubscribeEvent
	public static void restrictPlayerMovement(InputUpdateEvent event) {
		if (event.getPlayer().world.getWorldType() instanceof BeachheadWorldType) {
			MovementInput movement = event.getMovementInput();
			movement.backKeyDown = false;
			movement.forwardKeyDown = false;
			movement.leftKeyDown = false;
			movement.rightKeyDown = false;
			movement.jump = false;
			movement.moveForward = 0;
			movement.moveStrafe = 0;
		}
	}

	@SubscribeEvent
	public static void noRenderScroll(RenderHandEvent event) {
		if (event.getItemStack().getItem().equals(Beachhead.SCROLL)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void gameTimersClient(ClientTickEvent event) {
		if (Minecraft.getInstance() == null || Minecraft.getInstance().world == null)
			return;

		World world = Minecraft.getInstance().world;
		if (world.getWorldType() instanceof BeachheadWorldType && event.phase == Phase.START) {
			BeachheadGame game = world.getCapability(Beachhead.BEACHHEADGAME_CAP)
					.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));

			game.tick();
		}
	}

	private static ResourceLocation nola_open_mouth = new ResourceLocation(Beachhead.MODID,
			"textures/gui/nola_open_mouth_bordered.png");
	private static ResourceLocation nola_closed_mouth = new ResourceLocation(Beachhead.MODID,
			"textures/gui/nola_closed_mouth_bordered.png");
	private static ResourceLocation save_turtle = new ResourceLocation(Beachhead.MODID, "textures/gui/save_turtle.png");

	@SubscribeEvent
	public static void renderNolaDialogue(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.ALL) {
			Minecraft mc = Minecraft.getInstance();
			if (mc.world.getWorldType() instanceof BeachheadWorldType) {
				BeachheadGame game = mc.world.getCapability(Beachhead.BEACHHEADGAME_CAP)
						.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"));
				int turtleTimer = game.getTurtleMadeItTimer();
				int cycleTimer = game.getCycleOfLifeTimer();
				if (turtleTimer > 0 || cycleTimer > 0) {
					if ((turtleTimer % 10 < 5 && turtleTimer > 0) || (cycleTimer % 10 < 5 && cycleTimer > 0)) {
						mc.getTextureManager().bindTexture(nola_open_mouth);
					} else {
						mc.getTextureManager().bindTexture(nola_closed_mouth);
					}
					double x = event.getWindow().getScaledWidth() * 0.5 - 96;
					double y = event.getWindow().getScaledHeight() - 74;

					// Draw Nola
					BufferBuilder builder = Tessellator.getInstance().getBuffer();
					builder.begin(7, DefaultVertexFormats.POSITION_TEX);
					builder.pos(x, y + 32, 94).tex(0, 1).endVertex();
					builder.pos(x + 192, y + 32, 94).tex(1, 1).endVertex();
					builder.pos(x + 192, y, 94).tex(1, 0).endVertex();
					builder.pos(x, y, 94).tex(0, 0).endVertex();
					builder.finishDrawing();
					RenderSystem.enableAlphaTest();
					RenderSystem.enableBlend();
					WorldVertexBufferUploader.draw(builder);

					// Draw Text
					mc.fontRenderer.drawString("Scrollsage Nola", (float) x + 34, (float) y + 2, 0xFFDF00);
					if (turtleTimer > 0) {
						mc.fontRenderer.drawString("A turtle made it to the water!", (float) x + 34, (float) y + 16,
								0xFFFFFF);
					} else {
						mc.fontRenderer.drawString("The cycle of life can be cruel..", (float) x + 34, (float) y + 16,
								0xFFFFFF);
					}
				}

				// Draw score
				double offset = 2;
				mc.getTextureManager().bindTexture(save_turtle);
				BufferBuilder builder = Tessellator.getInstance().getBuffer();
				builder.begin(7, DefaultVertexFormats.POSITION_TEX);
				builder.pos(offset, offset + 16, 94).tex(0, 1).endVertex();
				builder.pos(offset + 16, offset + 16, 94).tex(1, 1).endVertex();
				builder.pos(offset + 16, offset, 94).tex(1, 0).endVertex();
				builder.pos(offset, offset, 94).tex(0, 0).endVertex();
				builder.finishDrawing();
				RenderSystem.enableAlphaTest();
				RenderSystem.enableBlend();
				WorldVertexBufferUploader.draw(builder);

				// Draw Text
				mc.fontRenderer.drawString(String.valueOf(game.getScore()), (float) offset * 2 + 16, (float) offset + 5,
						0xFFDF00);
			}
		}
	}
}
