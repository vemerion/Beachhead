package mod.vemerion.beachhead.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.entities.BeachheadCrabEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class BeachheadCrabModel extends EntityModel<BeachheadCrabEntity> {
    public ModelRenderer body;
    public ModelRenderer leftFrontLeg;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;
    public ModelRenderer leftBackLeg;
    public ModelRenderer rightFrontLeg;
    public ModelRenderer rightBackLeg;
    public ModelRenderer leftClawTop;
    public ModelRenderer leftClawBottom;
    public ModelRenderer rightClawTop;
    public ModelRenderer rightClawBottom;

    public BeachheadCrabModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.leftBackLeg = new ModelRenderer(this, 0, 0);
        this.leftBackLeg.setRotationPoint(4.0F, 0.0F, 3.0F);
        this.leftBackLeg.addBox(0.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftBackLeg, 0.0F, 0.0F, -0.3127630032889644F);
        this.rightClawBottom = new ModelRenderer(this, 0, 18);
        this.rightClawBottom.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.rightClawBottom.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightClawBottom, -0.11728612207217244F, -0.6255260065779288F, -0.3909537457888271F);
        this.rightArm = new ModelRenderer(this, 0, 12);
        this.rightArm.setRotationPoint(-3.0F, 1.0F, -4.0F);
        this.rightArm.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightArm, 0.23457224414434488F, 0.4300491170387584F, 0.0F);
        this.rightClawTop = new ModelRenderer(this, 12, 12);
        this.rightClawTop.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.rightClawTop.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightClawTop, -0.46914448828868976F, -0.6255260065779288F, -0.3909537457888271F);
        this.rightFrontLeg = new ModelRenderer(this, 0, 0);
        this.rightFrontLeg.setRotationPoint(-4.0F, 0.0F, -3.0F);
        this.rightFrontLeg.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightFrontLeg, 0.0F, 0.0F, 0.296705972839036F);
        this.leftArm = new ModelRenderer(this, 0, 12);
        this.leftArm.setRotationPoint(3.0F, 1.0F, -4.0F);
        this.leftArm.addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftArm, 0.0F, -0.3909537457888271F, -0.27366763203903305F);
        this.rightBackLeg = new ModelRenderer(this, 0, 0);
        this.rightBackLeg.setRotationPoint(-4.0F, 0.0F, 3.0F);
        this.rightBackLeg.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightBackLeg, 0.0F, 0.0F, 0.296705972839036F);
        this.leftEye = new ModelRenderer(this, 24, 0);
        this.leftEye.setRotationPoint(2.0F, -1.0F, -4.0F);
        this.leftEye.addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftEye, 0.3909537457888271F, 0.0F, 0.0F);
        this.leftFrontLeg = new ModelRenderer(this, 0, 0);
        this.leftFrontLeg.setRotationPoint(4.0F, 0.0F, -3.0F);
        this.leftFrontLeg.addBox(0.0F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftFrontLeg, 0.0F, 0.0F, -0.3127630032889644F);
        this.rightEye = new ModelRenderer(this, 24, 0);
        this.rightEye.setRotationPoint(-2.0F, -1.0F, -4.0F);
        this.rightEye.addBox(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightEye, 0.3909537457888271F, 0.0F, 0.0F);
        this.leftClawTop = new ModelRenderer(this, 12, 12);
        this.leftClawTop.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.leftClawTop.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftClawTop, -0.03909537541112055F, 0.6255260065779288F, 0.3909537457888271F);
        this.leftClawBottom = new ModelRenderer(this, 0, 18);
        this.leftClawBottom.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.leftClawBottom.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftClawBottom, 0.3909537457888271F, 0.6255260065779288F, 0.3909537457888271F);
        this.body.addChild(this.leftBackLeg);
        this.rightArm.addChild(this.rightClawBottom);
        this.body.addChild(this.rightArm);
        this.rightArm.addChild(this.rightClawTop);
        this.body.addChild(this.rightFrontLeg);
        this.body.addChild(this.leftArm);
        this.body.addChild(this.rightBackLeg);
        this.body.addChild(this.leftEye);
        this.body.addChild(this.leftFrontLeg);
        this.body.addChild(this.rightEye);
        this.leftArm.addChild(this.leftClawTop);
        this.leftArm.addChild(this.leftClawBottom);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(BeachheadCrabEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	// Legs
    	leftBackLeg.rotateAngleX = MathHelper.sin((ageInTicks / 20) * (float)Math.PI * 2) * (float)Math.PI / 8;
    	rightFrontLeg.rotateAngleX = MathHelper.sin((ageInTicks / 20) * (float)Math.PI * 2) * (float)Math.PI / 8;
    	rightBackLeg.rotateAngleX = MathHelper.sin((ageInTicks / 20) * (float)Math.PI * 2 + (float)Math.PI) * (float)Math.PI / 8;
    	leftFrontLeg.rotateAngleX = MathHelper.sin((ageInTicks / 20) * (float)Math.PI * 2 + (float)Math.PI) * (float)Math.PI / 8;

    	// Eyes
    	rightEye.rotateAngleX = MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2) * (float)Math.PI / 12 + (float)Math.PI * 0.1f;
    	leftEye.rotateAngleX = MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2 + (float)Math.PI) * (float)Math.PI / 12 + (float)Math.PI * 0.1f;
    	
    	// Claws
    	leftClawTop.rotateAngleX = -Math.abs(MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2)) * (float)Math.PI / 12;
    	leftClawBottom.rotateAngleX = Math.abs(MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2)) * (float)Math.PI / 12;
    	rightClawTop.rotateAngleX = -Math.abs(MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2 + (float)Math.PI / 2)) * (float)Math.PI / 12;
    	rightClawBottom.rotateAngleX = Math.abs(MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2 + (float)Math.PI / 2)) * (float)Math.PI / 12;

    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
