package mod.vemerion.beachhead.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.Beachhead;
import mod.vemerion.beachhead.entities.TargetEntity;
import mod.vemerion.beachhead.models.TargetModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class TargetRenderer extends EntityRenderer<TargetEntity> {
	private final TargetModel model = new TargetModel();
	public static final ResourceLocation TEXTURES = new ResourceLocation(Beachhead.MODID,
			"textures/entity/target.png");

	public TargetRenderer(EntityRendererManager rendererManager) {
		super(rendererManager);
	}

	@Override
	protected int getBlockLight(TargetEntity entityIn, float partialTicks) {
		return 15;
	}

	@Override
	public void render(TargetEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(getEntityTexture(entityIn)));
		this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F,
				1.0F);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(TargetEntity entity) {
		return TEXTURES;
	}
}
