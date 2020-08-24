package mod.vemerion.beachhead.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.entities.TargetEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Created using Tabula 8.0.0
 */
public class TargetModel extends EntityModel<TargetEntity> {
    public ModelRenderer targetY;
    public ModelRenderer targetX;
    public ModelRenderer targetZ;

    public TargetModel() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.targetX = new ModelRenderer(this, 0, 0);
        this.targetX.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.targetX.addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(targetX, 0.0F, 0.0F, 1.5707963267948966F);
        this.targetZ = new ModelRenderer(this, 0, 0);
        this.targetZ.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.targetZ.addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(targetZ, 1.5707963267948966F, 0.0F, 0.0F);
        this.targetY = new ModelRenderer(this, 0, 0);
        this.targetY.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.targetY.addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.targetX, this.targetZ, this.targetY).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(TargetEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}        