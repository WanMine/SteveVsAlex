package com.diamantino.stevevsalex.client.models;

import com.diamantino.stevevsalex.entities.base.BombEntity;
import com.diamantino.stevevsalex.entities.steve.SteveOmbEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import static com.diamantino.stevevsalex.client.renderers.BombRenderer.getBombRotation;

public class BombModel<T extends BombEntity> extends EntityModel<BombEntity> {

	private final ModelPart Full;

	public BombModel(ModelPart root) {
		this.Full = root.getChild("Full");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Full = partdefinition.addOrReplaceChild("Full", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -24.0F, 12.0F, 12.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 8.0F));

		PartDefinition cube_r1 = Full.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 44).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -32.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition FinsTotal = Full.addOrReplaceChild("FinsTotal", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, -8.0F));

		PartDefinition Fins2 = FinsTotal.addOrReplaceChild("Fins2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.2679F, -8.0F, 19.2597F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r2 = Fins2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 76).addBox(-4.0F, -5.6569F, 1.853F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.7956F, 0.7854F, 0.0F, -1.5708F));

		PartDefinition cube_r3 = Fins2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 60).addBox(-4.0F, 0.0F, -3.8038F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, -5.7956F, 0.7854F, 0.0F, 1.5708F));

		PartDefinition Fins = FinsTotal.addOrReplaceChild("Fins", CubeListBuilder.create(), PartPose.offset(0.2679F, -8.0F, 19.2597F));

		PartDefinition cube_r4 = Fins.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(72, 28).addBox(-4.0F, -5.6569F, 1.853F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.7956F, 0.7854F, 0.0F, -1.5708F));

		PartDefinition cube_r5 = Fins.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, 0.0F, -3.8038F, 8.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, -5.7956F, 0.7854F, 0.0F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(BombEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Full.xRot = getBombRotation(entity, limbSwing);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Full.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}