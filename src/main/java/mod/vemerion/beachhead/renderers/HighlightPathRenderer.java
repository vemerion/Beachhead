package mod.vemerion.beachhead.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.entities.HighlightPathEntity;
import mod.vemerion.beachhead.models.HighlightPathModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class HighlightPathRenderer extends EntityRenderer<HighlightPathEntity> {
	private final HighlightPathModel model = new HighlightPathModel();
	public static final ResourceLocation TEXTURES = new ResourceLocation(Beachhead.MODID,
			"textures/entity/highlight.png");

	public HighlightPathRenderer(EntityRendererManager rendererManager) {
		super(rendererManager);
	}

	@Override
	protected int getBlockLight(HighlightPathEntity entityIn, float partialTicks) {
		return 15;
	}

	@Override
	public void render(HighlightPathEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(getEntityTexture(entityIn)));
		model.part.rotateAngleX = -(float)Math.toRadians(entityIn.rotationPitch);
		model.part.rotateAngleY = -(float)Math.toRadians(entityIn.rotationYaw);
		this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F,
				1.0F);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(HighlightPathEntity entity) {
		return TEXTURES;
	}
}
