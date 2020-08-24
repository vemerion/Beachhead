package mod.vemerion.beachhead.renderers;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.entities.BeachheadTurtleEntity;
import mod.vemerion.beachhead.models.BeachheadTurtleModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BeachheadTurtleRenderer extends MobRenderer<BeachheadTurtleEntity, BeachheadTurtleModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Beachhead.MODID,
			"textures/entity/beachhead_turtle.png");

	public BeachheadTurtleRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new BeachheadTurtleModel(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(BeachheadTurtleEntity entity) {
		return TEXTURE;
	}

}
