package mod.vemerion.beachhead.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.entities.BeachheadAlbatrossEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class BeachheadAlbatrossModel extends EntityModel<BeachheadAlbatrossEntity> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leftLeg;
    public ModelRenderer tail;
    public ModelRenderer rightLeg;
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;
    public ModelRenderer upperBeak;
    public ModelRenderer lowerBeak;
    public ModelRenderer leftToe1;
    public ModelRenderer leftToe2;
    public ModelRenderer leftToe3;
    public ModelRenderer rightToe1;
    public ModelRenderer rightToe2;
    public ModelRenderer rightToe3;

    public BeachheadAlbatrossModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.upperBeak = new ModelRenderer(this, 0, 0);
        this.upperBeak.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.upperBeak.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(upperBeak, -0.3127630032889644F, 0.0F, 0.0F);
        this.rightToe3 = new ModelRenderer(this, 0, 4);
        this.rightToe3.setRotationPoint(0.5F, 3.0F, 0.5F);
        this.rightToe3.addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightToe3, -0.46914448828868976F, 0.0F, -0.23457224414434488F);
        this.tail = new ModelRenderer(this, 28, 10);
        this.tail.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail.addBox(-6.0F, 0.0F, 0.0F, 12.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.46914448828868976F, 0.0F, 0.0F);
        this.leftToe3 = new ModelRenderer(this, 0, 4);
        this.leftToe3.setRotationPoint(0.5F, 3.0F, 0.5F);
        this.leftToe3.addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftToe3, -0.46914448828868976F, 0.0F, -0.23457224414434488F);
        this.rightWing = new ModelRenderer(this, 24, 18);
        this.rightWing.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.rightWing.addBox(-12.0F, 0.0F, -5.0F, 12.0F, 0.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 24, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -6.0F);
        this.head.addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, 0.5473352640780661F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 0, 18);
        this.leftWing.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.leftWing.addBox(0.0F, 0.0F, -5.0F, 12.0F, 0.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.leftToe1 = new ModelRenderer(this, 0, 4);
        this.leftToe1.setRotationPoint(-0.5F, 3.0F, 0.5F);
        this.leftToe1.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftToe1, -0.27366763203903305F, 0.0F, 0.35185837453889574F);
        this.leftToe2 = new ModelRenderer(this, 0, 4);
        this.leftToe2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.leftToe2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftToe2, -0.4300491170387584F, 0.0F, 0.0F);
        this.lowerBeak = new ModelRenderer(this, 36, 0);
        this.lowerBeak.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.lowerBeak.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(lowerBeak, 0.35185837453889574F, 0.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 24, 0);
        this.leftLeg.setRotationPoint(2.0F, 3.0F, 3.0F);
        this.leftLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(body, -0.35185837453889574F, 0.0F, 0.0F);
        this.rightToe1 = new ModelRenderer(this, 0, 4);
        this.rightToe1.setRotationPoint(-0.5F, 3.0F, 0.5F);
        this.rightToe1.addBox(-1.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightToe1, -0.27366763203903305F, 0.0F, 0.35185837453889574F);
        this.rightLeg = new ModelRenderer(this, 24, 0);
        this.rightLeg.setRotationPoint(-2.0F, 3.0F, 3.0F);
        this.rightLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.rightToe2 = new ModelRenderer(this, 0, 4);
        this.rightToe2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.rightToe2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightToe2, -0.4300491170387584F, 0.0F, 0.0F);
        this.head.addChild(this.upperBeak);
        this.rightLeg.addChild(this.rightToe3);
        this.body.addChild(this.tail);
        this.leftLeg.addChild(this.leftToe3);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.head);
        this.body.addChild(this.leftWing);
        this.leftLeg.addChild(this.leftToe1);
        this.leftLeg.addChild(this.leftToe2);
        this.head.addChild(this.lowerBeak);
        this.body.addChild(this.leftLeg);
        this.rightLeg.addChild(this.rightToe1);
        this.body.addChild(this.rightLeg);
        this.rightLeg.addChild(this.rightToe2);      
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(BeachheadAlbatrossEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	upperBeak.rotateAngleX = -Math.abs(MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2)) * (float)Math.PI / 12;
    	lowerBeak.rotateAngleX = Math.abs(MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2)) * (float)Math.PI / 12;
    	
    	leftWing.rotateAngleZ = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 4;
    	rightWing.rotateAngleZ = -MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 4;
    	
    	leftLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 12;
    	rightLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 12;


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
