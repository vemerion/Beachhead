package mod.vemerion.beachhead.renderers;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.entities.BeachheadAlbatrossEntity;
import mod.vemerion.beachhead.models.BeachheadAlbatrossModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BeachheadAlbatrossRenderer extends MobRenderer<BeachheadAlbatrossEntity, BeachheadAlbatrossModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Beachhead.MODID,
			"textures/entity/beachhead_albatross.png");

	public BeachheadAlbatrossRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new BeachheadAlbatrossModel(), 0);
	}

	@Override
	public ResourceLocation getEntityTexture(BeachheadAlbatrossEntity entity) {
		return TEXTURE;
	}

}
