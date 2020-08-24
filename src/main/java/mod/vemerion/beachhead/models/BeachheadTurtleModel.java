package mod.vemerion.beachhead.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import mod.vemerion.beachhead.entities.BeachheadTurtleEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 8.0.0
 */
public class BeachheadTurtleModel extends EntityModel<BeachheadTurtleEntity> {
	public ModelRenderer body;
	public ModelRenderer frontLeftLeg;
	public ModelRenderer frontRightLeg;
	public ModelRenderer shell1;
	public ModelRenderer shell2;
	public ModelRenderer shell3;
	public ModelRenderer shell4;
	public ModelRenderer backRightLeg;
	public ModelRenderer backLeftLeg;
	public ModelRenderer tail;
	public ModelRenderer head;

	public BeachheadTurtleModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.frontLeftLeg = new ModelRenderer(this, 0, 0);
		this.frontLeftLeg.setRotationPoint(4.0F, 0.0F, -3.0F);
		this.frontLeftLeg.addBox(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.shell3 = new ModelRenderer(this, 27, 12);
		this.shell3.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.shell3.addBox(-3.5F, 0.5F, -3.5F, 7.0F, 1.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 0, 5);
		this.tail.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(tail, -0.3490658503988659F, 0.0F, 0.0F);
		this.frontRightLeg = new ModelRenderer(this, 24, 0);
		this.frontRightLeg.setRotationPoint(-4.0F, 0.0F, -3.0F);
		this.frontRightLeg.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body.addBox(-4.0F, -1.5F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.backRightLeg = new ModelRenderer(this, 52, 0);
		this.backRightLeg.setRotationPoint(-4.0F, 0.0F, 3.0F);
		this.backRightLeg.addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.shell4 = new ModelRenderer(this, 32, 20);
		this.shell4.setRotationPoint(0.0F, -6.0F, 0.0F);
		this.shell4.addBox(-2.0F, 0.5F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.shell1 = new ModelRenderer(this, 22, 1);
		this.shell1.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.shell1.addBox(-5.0F, 0.5F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.backLeftLeg = new ModelRenderer(this, 52, 5);
		this.backLeftLeg.setRotationPoint(4.0F, 0.0F, 3.0F);
		this.backLeftLeg.addBox(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.head = new ModelRenderer(this, 48, 12);
		this.head.setRotationPoint(0.0F, 1.0F, -4.0F);
		this.head.addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.shell2 = new ModelRenderer(this, 0, 12);
		this.shell2.setRotationPoint(-0.5F, -4.0F, -0.5F);
		this.shell2.addBox(-4.0F, 0.5F, -4.0F, 9.0F, 1.0F, 9.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.frontLeftLeg);
		this.body.addChild(this.shell3);
		this.body.addChild(this.tail);
		this.body.addChild(this.frontRightLeg);
		this.body.addChild(this.backRightLeg);
		this.body.addChild(this.shell4);
		this.body.addChild(this.shell1);
		this.body.addChild(this.backLeftLeg);
		this.body.addChild(this.head);
		this.body.addChild(this.shell2);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(BeachheadTurtleEntity entityIn, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		tail.rotateAngleY = MathHelper.sin((ageInTicks / 40) * (float)Math.PI * 2) * (float)Math.PI / 6;
		frontLeftLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 5;
		backRightLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2) * (float)Math.PI / 5;
		frontRightLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2 + (float)Math.PI) * (float)Math.PI / 5;
		backLeftLeg.rotateAngleX = MathHelper.sin((ageInTicks / 30) * (float)Math.PI * 2 + (float)Math.PI) * (float)Math.PI / 5;

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
