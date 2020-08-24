package mod.vemerion.beachhead.renderers;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.entities.BeachheadCrabEntity;
import mod.vemerion.beachhead.models.BeachheadCrabModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BeachheadCrabRenderer extends MobRenderer<BeachheadCrabEntity, BeachheadCrabModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Beachhead.MODID,
			"textures/entity/beachhead_crab.png");

	public BeachheadCrabRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new BeachheadCrabModel(), 0.3f);
	}

	@Override
	public ResourceLocation getEntityTexture(BeachheadCrabEntity entity) {
		return TEXTURE;
	}

}
