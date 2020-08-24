package mod.vemerion.beachhead.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Created using Tabula 8.0.0
 */
public class HighlightPathModel extends Model {
    public ModelRenderer part;

    public HighlightPathModel() {
		super(RenderType::getEntityTranslucentCull);
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.part = new ModelRenderer(this, 0, 0);
        this.part.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.part.addBox(-2.0F, 0.0F, -8.0F, 4.0F, 0.0F, 16.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.part).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }
}   