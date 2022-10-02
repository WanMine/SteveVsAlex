package com.diamantino.stevevsalex.client.models;

import com.diamantino.stevevsalex.entities.SteveCopterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import static com.diamantino.stevevsalex.client.renderers.VehichleRenderer.getMinigunRotation;
import static com.diamantino.stevevsalex.client.renderers.VehichleRenderer.getPropellerRotation;

public class SteveCopterModel<T extends SteveCopterEntity> extends EntityModel<SteveCopterEntity> {

	private final ModelPart Full;
	private final ModelPart Propeller;
	private final ModelPart BackPropeller;
	private final ModelPart Minigun1;
	private final ModelPart Minigun2;

	public SteveCopterModel(ModelPart root) {
		this.Full = root.getChild("Full");
		this.Propeller = this.Full.getChild("Propeller");
		this.BackPropeller = this.Full.getChild("BackPropeller");
		this.Minigun1 = this.Full.getChild("Minigun1").getChild("ShootingPart");
		this.Minigun2 = this.Full.getChild("Minigun2").getChild("ShootingPart");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Full = partdefinition.addOrReplaceChild("Full", CubeListBuilder.create(), PartPose.offset(0.0F, 27.0F, 0.0F));

		PartDefinition Base = Full.addOrReplaceChild("Base", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Steve6 = Base.addOrReplaceChild("Steve6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg6 = Steve6.addOrReplaceChild("rightLeg6", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r1 = rightLeg6.addOrReplaceChild("rightLeg_r1", CubeListBuilder.create().texOffs(16, 48).addBox(-2.1F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition leftLeg6 = Steve6.addOrReplaceChild("leftLeg6", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r1 = leftLeg6.addOrReplaceChild("leftLeg_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition rightArm6 = Steve6.addOrReplaceChild("rightArm6", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r1 = rightArm6.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm6 = Steve6.addOrReplaceChild("leftArm6", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r1 = leftArm6.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body6 = Steve6.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head6 = Steve6.addOrReplaceChild("head6", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Side2 = Base.addOrReplaceChild("Side2", CubeListBuilder.create(), PartPose.offset(31.25F, -29.0F, 15.0F));

		PartDefinition Steve4 = Side2.addOrReplaceChild("Steve4", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg4 = Steve4.addOrReplaceChild("rightLeg4", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r2 = rightLeg4.addOrReplaceChild("rightLeg_r2", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-125.1F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftLeg4 = Steve4.addOrReplaceChild("leftLeg4", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r2 = leftLeg4.addOrReplaceChild("leftLeg_r2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-128.9F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition rightArm4 = Steve4.addOrReplaceChild("rightArm4", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r2 = rightArm4.addOrReplaceChild("rightArm_r2", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-122.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftArm4 = Steve4.addOrReplaceChild("leftArm4", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r2 = leftArm4.addOrReplaceChild("leftArm_r2", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-132.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition body4 = Steve4.addOrReplaceChild("body4", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r1 = body4.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition head4 = Steve4.addOrReplaceChild("head4", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r1 = head4.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition Steve5 = Side2.addOrReplaceChild("Steve5", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg5 = Steve5.addOrReplaceChild("rightLeg5", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg5 = Steve5.addOrReplaceChild("leftLeg5", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightArm5 = Steve5.addOrReplaceChild("rightArm5", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-19.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm5 = Steve5.addOrReplaceChild("leftArm5", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-17.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition body5 = Steve5.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-20.4821F, 0.0F, -37.346F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head5 = Steve5.addOrReplaceChild("head5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-20.4821F, -8.0F, -39.346F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Side = Base.addOrReplaceChild("Side", CubeListBuilder.create(), PartPose.offset(8.5F, -29.0F, 15.0F));

		PartDefinition Steve3 = Side.addOrReplaceChild("Steve3", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg3 = Steve3.addOrReplaceChild("rightLeg3", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r3 = rightLeg3.addOrReplaceChild("rightLeg_r3", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(86.1F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftLeg3 = Steve3.addOrReplaceChild("leftLeg3", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r3 = leftLeg3.addOrReplaceChild("leftLeg_r3", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(89.9F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition rightArm3 = Steve3.addOrReplaceChild("rightArm3", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r3 = rightArm3.addOrReplaceChild("rightArm_r3", CubeListBuilder.create().texOffs(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(83.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftArm3 = Steve3.addOrReplaceChild("leftArm3", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r3 = leftArm3.addOrReplaceChild("leftArm_r3", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(93.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition body3 = Steve3.addOrReplaceChild("body3", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r2 = body3.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition head3 = Steve3.addOrReplaceChild("head3", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r2 = head3.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition Steve2 = Side.addOrReplaceChild("Steve2", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg2 = Steve2.addOrReplaceChild("rightLeg2", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg2 = Steve2.addOrReplaceChild("leftLeg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm2 = Steve2.addOrReplaceChild("rightArm2", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm2 = Steve2.addOrReplaceChild("leftArm2", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body2 = Steve2.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head2 = Steve2.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve9 = Base.addOrReplaceChild("Steve9", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, 19.0F, -1.5708F, 0.0F, -1.5708F));

		PartDefinition rightLeg9 = Steve9.addOrReplaceChild("rightLeg9", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 17.0F, 19.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, -21.0F));

		PartDefinition leftLeg9 = Steve9.addOrReplaceChild("leftLeg9", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 17.0F, 19.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, -21.0F));

		PartDefinition rightArm9 = Steve9.addOrReplaceChild("rightArm9", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, 15.0F, 19.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, -21.0F));

		PartDefinition leftArm9 = Steve9.addOrReplaceChild("leftArm9", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, 15.0F, 19.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, -21.0F));

		PartDefinition body9 = Steve9.addOrReplaceChild("body9", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 17.0F, 19.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -21.0F));

		PartDefinition head9 = Steve9.addOrReplaceChild("head9", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 9.0F, 17.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -21.0F));

		PartDefinition Steve10 = Base.addOrReplaceChild("Steve10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, 19.0F, -1.5708F, 0.0F, -1.5708F));

		PartDefinition rightLeg10 = Steve10.addOrReplaceChild("rightLeg10", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, -21.0F));

		PartDefinition rightLeg_r4 = rightLeg10.addOrReplaceChild("rightLeg_r4", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg10 = Steve10.addOrReplaceChild("leftLeg10", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, -21.0F));

		PartDefinition leftLeg_r4 = leftLeg10.addOrReplaceChild("leftLeg_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm12 = Steve10.addOrReplaceChild("rightArm12", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, -21.0F));

		PartDefinition rightArm_r4 = rightArm12.addOrReplaceChild("rightArm_r4", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm10 = Steve10.addOrReplaceChild("leftArm10", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, -21.0F));

		PartDefinition leftArm_r4 = leftArm10.addOrReplaceChild("leftArm_r4", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body10 = Steve10.addOrReplaceChild("body10", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, -21.0F));

		PartDefinition body_r3 = body10.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head11 = Steve10.addOrReplaceChild("head11", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, -21.0F));

		PartDefinition head_r3 = head11.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 21.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Propeller = Full.addOrReplaceChild("Propeller", CubeListBuilder.create(), PartPose.offset(0.0F, -32.0F, 0.0F));

		PartDefinition Steve = Propeller.addOrReplaceChild("Steve", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg = Steve.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg = Steve.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm = Steve.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r5 = rightArm.addOrReplaceChild("rightArm_r5", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm = Steve.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r5 = leftArm.addOrReplaceChild("leftArm_r5", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body = Steve.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head = Steve.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve7 = Propeller.addOrReplaceChild("Steve7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg7 = Steve7.addOrReplaceChild("rightLeg7", CubeListBuilder.create().texOffs(16, 48).addBox(30.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg7 = Steve7.addOrReplaceChild("leftLeg7", CubeListBuilder.create().texOffs(0, 16).addBox(30.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm7 = Steve7.addOrReplaceChild("rightArm7", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r6 = rightArm7.addOrReplaceChild("rightArm_r6", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(33.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm7 = Steve7.addOrReplaceChild("leftArm7", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r6 = leftArm7.addOrReplaceChild("leftArm_r6", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(31.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body7 = Steve7.addOrReplaceChild("body7", CubeListBuilder.create().texOffs(16, 16).addBox(28.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head7 = Steve7.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(0, 0).addBox(28.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve8 = Propeller.addOrReplaceChild("Steve8", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg8 = Steve8.addOrReplaceChild("rightLeg8", CubeListBuilder.create().texOffs(16, 48).addBox(-34.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg8 = Steve8.addOrReplaceChild("leftLeg8", CubeListBuilder.create().texOffs(0, 16).addBox(-34.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm8 = Steve8.addOrReplaceChild("rightArm8", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r7 = rightArm8.addOrReplaceChild("rightArm_r7", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm8 = Steve8.addOrReplaceChild("leftArm8", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r7 = leftArm8.addOrReplaceChild("leftArm_r7", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-33.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body8 = Steve8.addOrReplaceChild("body8", CubeListBuilder.create().texOffs(16, 16).addBox(-36.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head8 = Steve8.addOrReplaceChild("head8", CubeListBuilder.create().texOffs(0, 0).addBox(-36.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition BackPropeller = Full.addOrReplaceChild("BackPropeller", CubeListBuilder.create(), PartPose.offset(-2.0F, -22.0F, 62.0F));

		PartDefinition rightArm11 = BackPropeller.addOrReplaceChild("rightArm11", CubeListBuilder.create(), PartPose.offset(9.0F, -22.0F, -32.0F));

		PartDefinition rightArm_r8 = rightArm11.addOrReplaceChild("rightArm_r8", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 12.0F, 32.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition rightArm10 = BackPropeller.addOrReplaceChild("rightArm10", CubeListBuilder.create().texOffs(32, 48).addBox(-15.0F, 26.0F, 30.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -22.0F, -32.0F));

		PartDefinition head10 = BackPropeller.addOrReplaceChild("head10", CubeListBuilder.create(), PartPose.offset(4.0F, -24.0F, -32.0F));

		PartDefinition head_r4 = head10.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 24.0F, 32.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Minigun2 = Full.addOrReplaceChild("Minigun2", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-26.5F, -22.5F, -3.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -3.0F, 0.0F));

		PartDefinition ShootingPart2 = Minigun2.addOrReplaceChild("ShootingPart", CubeListBuilder.create(), PartPose.offset(-23.5F, -19.5F, 0.0F));

		PartDefinition Barrels2 = ShootingPart2.addOrReplaceChild("Barrels2", CubeListBuilder.create().texOffs(18, 34).mirror().addBox(-22.5F, -21.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-25.5F, -21.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-25.5F, -18.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-22.5F, -18.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-21.5F, -20.0F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-26.5F, -20.0F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-24.0F, -22.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 34).mirror().addBox(-24.0F, -17.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(23.5F, 19.5F, 0.0F));

		PartDefinition Disk3 = ShootingPart2.addOrReplaceChild("Disk3", CubeListBuilder.create().texOffs(20, 37).mirror().addBox(-25.5F, -20.5F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 35).mirror().addBox(-24.5F, -21.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 35).mirror().addBox(-24.5F, -18.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(23.5F, 19.5F, 0.0F));

		PartDefinition Disk4 = ShootingPart2.addOrReplaceChild("Disk4", CubeListBuilder.create().texOffs(20, 37).mirror().addBox(-25.5F, -20.5F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 35).mirror().addBox(-24.5F, -21.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 35).mirror().addBox(-24.5F, -18.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(23.5F, 19.5F, -8.0F));

		PartDefinition Minigun1 = Full.addOrReplaceChild("Minigun1", CubeListBuilder.create().texOffs(0, 36).addBox(17.5F, -22.5F, -3.0F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.0F, 0.0F));

		PartDefinition ShootingPart = Minigun1.addOrReplaceChild("ShootingPart", CubeListBuilder.create(), PartPose.offset(22.5F, -19.5F, 0.0F));

		PartDefinition Barrels = ShootingPart.addOrReplaceChild("Barrels", CubeListBuilder.create().texOffs(18, 34).addBox(20.5F, -21.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(23.5F, -21.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(23.5F, -18.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(20.5F, -18.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(19.5F, -20.0F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(24.5F, -20.0F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(22.0F, -22.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(18, 34).addBox(22.0F, -17.5F, -16.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.5F, 19.5F, 0.0F));

		PartDefinition Disk1 = ShootingPart.addOrReplaceChild("Disk1", CubeListBuilder.create().texOffs(20, 37).addBox(20.5F, -20.5F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 35).addBox(21.5F, -21.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 35).addBox(21.5F, -18.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.5F, 19.5F, 0.0F));

		PartDefinition Disk2 = ShootingPart.addOrReplaceChild("Disk2", CubeListBuilder.create().texOffs(20, 37).addBox(20.5F, -20.5F, -4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 35).addBox(21.5F, -21.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 35).addBox(21.5F, -18.5F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-22.5F, 19.5F, -8.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Full.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(SteveCopterEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		Propeller.yRot = getPropellerRotation(p_102618_, p_102619_);
		BackPropeller.xRot = getPropellerRotation(p_102618_, p_102619_);
		if (p_102618_.getIsShooting()) {
			Minigun1.zRot = getMinigunRotation(p_102618_, p_102619_);
			Minigun2.zRot = getMinigunRotation(p_102618_, p_102619_);
		}
	}
}