package com.diamantino.stevevsalex.client.models;

import com.diamantino.stevevsalex.entities.SteveAneEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import static com.diamantino.stevevsalex.client.renderers.VehichleRenderer.getPropellerRotation;

public class SteveAneModel<T extends SteveAneEntity> extends EntityModel<SteveAneEntity> {

	private final ModelPart Full;
	private final ModelPart Propeller1;
	private final ModelPart Propeller2;
	private final ModelPart Propeller3;
	private final ModelPart Propeller4;

	public SteveAneModel(ModelPart root) {
		this.Full = root.getChild("Full");
		this.Propeller1 = Full.getChild("rightala2").getChild("Propeller1");
		this.Propeller2 = Full.getChild("leftala").getChild("Propeller2");
		this.Propeller3 = Full.getChild("rightala").getChild("Propeller3");
		this.Propeller4 = Full.getChild("leftala2").getChild("Propeller4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Full = partdefinition.addOrReplaceChild("Full", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -69.0311F));

		PartDefinition bone3 = Full.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.375F, 0.0F, -12.975F, -3.1416F, 0.0F, 0.0F));

		PartDefinition Side5 = bone3.addOrReplaceChild("Side5", CubeListBuilder.create(), PartPose.offset(31.25F, 29.0F, -28.0F));

		PartDefinition Steve15 = Side5.addOrReplaceChild("Steve15", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg15 = Steve15.addOrReplaceChild("rightLeg15", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r1 = rightLeg15.addOrReplaceChild("rightLeg_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-124.297F, -23.278F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftLeg15 = Steve15.addOrReplaceChild("leftLeg15", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r1 = leftLeg15.addOrReplaceChild("leftLeg_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-129.703F, -26.722F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightArm15 = Steve15.addOrReplaceChild("rightArm15", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r1 = rightArm15.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-119.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftArm15 = Steve15.addOrReplaceChild("leftArm15", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r1 = leftArm15.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-134.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition body15 = Steve15.addOrReplaceChild("body15", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r1 = body15.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition head15 = Steve15.addOrReplaceChild("head15", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r1 = head15.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -28.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition Steve16 = Side5.addOrReplaceChild("Steve16", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg16 = Steve16.addOrReplaceChild("rightLeg16", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r2 = rightLeg16.addOrReplaceChild("rightLeg_r2", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg16 = Steve16.addOrReplaceChild("leftLeg16", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r2 = leftLeg16.addOrReplaceChild("leftLeg_r2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm16 = Steve16.addOrReplaceChild("rightArm16", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r2 = rightArm16.addOrReplaceChild("rightArm_r2", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm16 = Steve16.addOrReplaceChild("leftArm16", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r2 = leftArm16.addOrReplaceChild("leftArm_r2", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body16 = Steve16.addOrReplaceChild("body16", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r2 = body16.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head16 = Steve16.addOrReplaceChild("head16", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r2 = head16.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, 4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Side6 = bone3.addOrReplaceChild("Side6", CubeListBuilder.create(), PartPose.offset(8.5F, 29.0F, -28.0F));

		PartDefinition Steve17 = Side6.addOrReplaceChild("Steve17", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg17 = Steve17.addOrReplaceChild("rightLeg17", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r3 = rightLeg17.addOrReplaceChild("rightLeg_r3", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(85.297F, -23.278F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftLeg17 = Steve17.addOrReplaceChild("leftLeg17", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r3 = leftLeg17.addOrReplaceChild("leftLeg_r3", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(90.703F, -26.722F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightArm17 = Steve17.addOrReplaceChild("rightArm17", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r3 = rightArm17.addOrReplaceChild("rightArm_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(80.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftArm17 = Steve17.addOrReplaceChild("leftArm17", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r3 = leftArm17.addOrReplaceChild("leftArm_r3", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(95.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition body17 = Steve17.addOrReplaceChild("body17", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r3 = body17.addOrReplaceChild("body_r3", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition head17 = Steve17.addOrReplaceChild("head17", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r3 = head17.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -28.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition Steve18 = Side6.addOrReplaceChild("Steve18", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg18 = Steve18.addOrReplaceChild("rightLeg18", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r4 = rightLeg18.addOrReplaceChild("rightLeg_r4", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg18 = Steve18.addOrReplaceChild("leftLeg18", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r4 = leftLeg18.addOrReplaceChild("leftLeg_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm18 = Steve18.addOrReplaceChild("rightArm18", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r4 = rightArm18.addOrReplaceChild("rightArm_r4", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm18 = Steve18.addOrReplaceChild("leftArm18", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r4 = leftArm18.addOrReplaceChild("leftArm_r4", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body18 = Steve18.addOrReplaceChild("body18", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r4 = body18.addOrReplaceChild("body_r4", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head18 = Steve18.addOrReplaceChild("head18", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r4 = head18.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition bone7 = Full.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 64.0F));

		PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(31.25F, -29.0F, 79.0F));

		PartDefinition Side11 = bone8.addOrReplaceChild("Side11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Steve30 = Side11.addOrReplaceChild("Steve30", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg30 = Steve30.addOrReplaceChild("rightLeg30", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r5 = rightLeg30.addOrReplaceChild("rightLeg_r5", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-125.1F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftLeg30 = Steve30.addOrReplaceChild("leftLeg30", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r5 = leftLeg30.addOrReplaceChild("leftLeg_r5", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-128.9F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition rightArm30 = Steve30.addOrReplaceChild("rightArm30", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r5 = rightArm30.addOrReplaceChild("rightArm_r5", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-122.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftArm30 = Steve30.addOrReplaceChild("leftArm30", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r5 = leftArm30.addOrReplaceChild("leftArm_r5", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-132.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition body30 = Steve30.addOrReplaceChild("body30", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r5 = body30.addOrReplaceChild("body_r5", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition head30 = Steve30.addOrReplaceChild("head30", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r5 = head30.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition Steve31 = Side11.addOrReplaceChild("Steve31", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg31 = Steve31.addOrReplaceChild("rightLeg31", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg31 = Steve31.addOrReplaceChild("leftLeg31", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightArm31 = Steve31.addOrReplaceChild("rightArm31", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-19.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm31 = Steve31.addOrReplaceChild("leftArm31", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-17.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition body31 = Steve31.addOrReplaceChild("body31", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-20.4821F, 0.0F, -37.346F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head31 = Steve31.addOrReplaceChild("head31", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-20.4821F, -8.0F, -39.346F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Side12 = bone8.addOrReplaceChild("Side12", CubeListBuilder.create(), PartPose.offset(-22.75F, 0.0F, 0.0F));

		PartDefinition Steve32 = Side12.addOrReplaceChild("Steve32", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg32 = Steve32.addOrReplaceChild("rightLeg32", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r6 = rightLeg32.addOrReplaceChild("rightLeg_r6", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(86.1F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftLeg32 = Steve32.addOrReplaceChild("leftLeg32", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r6 = leftLeg32.addOrReplaceChild("leftLeg_r6", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(89.9F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition rightArm32 = Steve32.addOrReplaceChild("rightArm32", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r6 = rightArm32.addOrReplaceChild("rightArm_r6", CubeListBuilder.create().texOffs(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(83.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftArm32 = Steve32.addOrReplaceChild("leftArm32", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r6 = leftArm32.addOrReplaceChild("leftArm_r6", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(93.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition body32 = Steve32.addOrReplaceChild("body32", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r6 = body32.addOrReplaceChild("body_r6", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition head32 = Steve32.addOrReplaceChild("head32", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r6 = head32.addOrReplaceChild("head_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition Steve33 = Side12.addOrReplaceChild("Steve33", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg33 = Steve33.addOrReplaceChild("rightLeg33", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg33 = Steve33.addOrReplaceChild("leftLeg33", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm33 = Steve33.addOrReplaceChild("rightArm33", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm33 = Steve33.addOrReplaceChild("leftArm33", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body33 = Steve33.addOrReplaceChild("body33", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head33 = Steve33.addOrReplaceChild("head33", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition bone9 = bone7.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.375F, 0.0F, 51.025F, -3.1416F, 0.0F, 0.0F));

		PartDefinition Side13 = bone9.addOrReplaceChild("Side13", CubeListBuilder.create(), PartPose.offset(31.25F, 29.0F, -28.0F));

		PartDefinition Steve34 = Side13.addOrReplaceChild("Steve34", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg34 = Steve34.addOrReplaceChild("rightLeg34", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r7 = rightLeg34.addOrReplaceChild("rightLeg_r7", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-124.297F, -23.278F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftLeg34 = Steve34.addOrReplaceChild("leftLeg34", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r7 = leftLeg34.addOrReplaceChild("leftLeg_r7", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-129.703F, -26.722F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightArm34 = Steve34.addOrReplaceChild("rightArm34", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r7 = rightArm34.addOrReplaceChild("rightArm_r7", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-119.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftArm34 = Steve34.addOrReplaceChild("leftArm34", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r7 = leftArm34.addOrReplaceChild("leftArm_r7", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-134.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition body34 = Steve34.addOrReplaceChild("body34", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r7 = body34.addOrReplaceChild("body_r7", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition head34 = Steve34.addOrReplaceChild("head34", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r7 = head34.addOrReplaceChild("head_r7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -28.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition Steve35 = Side13.addOrReplaceChild("Steve35", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg35 = Steve35.addOrReplaceChild("rightLeg35", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r8 = rightLeg35.addOrReplaceChild("rightLeg_r8", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg35 = Steve35.addOrReplaceChild("leftLeg35", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r8 = leftLeg35.addOrReplaceChild("leftLeg_r8", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm35 = Steve35.addOrReplaceChild("rightArm35", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r8 = rightArm35.addOrReplaceChild("rightArm_r8", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.4821F, 0.0F, -35.346F, -1.4399F, 0.0F, -3.1416F));

		PartDefinition leftArm35 = Steve35.addOrReplaceChild("leftArm35", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r8 = leftArm35.addOrReplaceChild("leftArm_r8", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body35 = Steve35.addOrReplaceChild("body35", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r8 = body35.addOrReplaceChild("body_r8", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head35 = Steve35.addOrReplaceChild("head35", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r8 = head35.addOrReplaceChild("head_r8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, 4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Side14 = bone9.addOrReplaceChild("Side14", CubeListBuilder.create(), PartPose.offset(8.5F, 29.0F, -28.0F));

		PartDefinition Steve36 = Side14.addOrReplaceChild("Steve36", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg36 = Steve36.addOrReplaceChild("rightLeg36", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r9 = rightLeg36.addOrReplaceChild("rightLeg_r9", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(85.297F, -23.278F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftLeg36 = Steve36.addOrReplaceChild("leftLeg36", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r9 = leftLeg36.addOrReplaceChild("leftLeg_r9", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(90.703F, -26.722F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightArm36 = Steve36.addOrReplaceChild("rightArm36", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r9 = rightArm36.addOrReplaceChild("rightArm_r9", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(80.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftArm36 = Steve36.addOrReplaceChild("leftArm36", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r9 = leftArm36.addOrReplaceChild("leftArm_r9", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(95.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition body36 = Steve36.addOrReplaceChild("body36", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r9 = body36.addOrReplaceChild("body_r9", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition head36 = Steve36.addOrReplaceChild("head36", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r9 = head36.addOrReplaceChild("head_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -28.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition Steve37 = Side14.addOrReplaceChild("Steve37", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg37 = Steve37.addOrReplaceChild("rightLeg37", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r10 = rightLeg37.addOrReplaceChild("rightLeg_r10", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg37 = Steve37.addOrReplaceChild("leftLeg37", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r10 = leftLeg37.addOrReplaceChild("leftLeg_r10", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm37 = Steve37.addOrReplaceChild("rightArm37", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r10 = rightArm37.addOrReplaceChild("rightArm_r10", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, -1.7453F, 0.0F, -3.1416F));

		PartDefinition leftArm37 = Steve37.addOrReplaceChild("leftArm37", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r10 = leftArm37.addOrReplaceChild("leftArm_r10", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body37 = Steve37.addOrReplaceChild("body37", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r10 = body37.addOrReplaceChild("body_r10", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head37 = Steve37.addOrReplaceChild("head37", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r10 = head37.addOrReplaceChild("head_r10", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftala2 = Full.addOrReplaceChild("leftala2", CubeListBuilder.create(), PartPose.offset(162.0F, 0.0F, 46.0F));

		PartDefinition Steve41 = leftala2.addOrReplaceChild("Steve41", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -22.0F, 56.0F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition rightLeg41 = Steve41.addOrReplaceChild("rightLeg41", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg41 = Steve41.addOrReplaceChild("leftLeg41", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-57.9F, -37.0F, 0.0F));

		PartDefinition rightArm41 = Steve41.addOrReplaceChild("rightArm41", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-51.0F, -47.0F, 0.0F));

		PartDefinition leftArm41 = Steve41.addOrReplaceChild("leftArm41", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-61.0F, -47.0F, 0.0F));

		PartDefinition body41 = Steve41.addOrReplaceChild("body41", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, -49.0F, 0.0F));

		PartDefinition head41 = Steve41.addOrReplaceChild("head41", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, -49.0F, 0.0F));

		PartDefinition Steve42 = leftala2.addOrReplaceChild("Steve42", CubeListBuilder.create(), PartPose.offsetAndRotation(-106.0F, -22.0F, 56.0F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition rightLeg42 = Steve42.addOrReplaceChild("rightLeg42", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg42 = Steve42.addOrReplaceChild("leftLeg42", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm42 = Steve42.addOrReplaceChild("rightArm42", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm42 = Steve42.addOrReplaceChild("leftArm42", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body42 = Steve42.addOrReplaceChild("body42", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head42 = Steve42.addOrReplaceChild("head42", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition Propeller4 = leftala2.addOrReplaceChild("Propeller4", CubeListBuilder.create(), PartPose.offsetAndRotation(-49.0F, -16.0F, 5.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition rightLeg43 = Propeller4.addOrReplaceChild("rightLeg43", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg43 = Propeller4.addOrReplaceChild("leftLeg43", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm43 = Propeller4.addOrReplaceChild("rightArm43", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r11 = rightArm43.addOrReplaceChild("rightArm_r11", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm43 = Propeller4.addOrReplaceChild("leftArm43", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r11 = leftArm43.addOrReplaceChild("leftArm_r11", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body43 = Propeller4.addOrReplaceChild("body43", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head43 = Propeller4.addOrReplaceChild("head43", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition rightala2 = Full.addOrReplaceChild("rightala2", CubeListBuilder.create(), PartPose.offset(-162.0F, 0.0F, 47.0F));

		PartDefinition Steve44 = rightala2.addOrReplaceChild("Steve44", CubeListBuilder.create(), PartPose.offsetAndRotation(106.0F, -22.0F, 56.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition rightLeg44 = Steve44.addOrReplaceChild("rightLeg44", CubeListBuilder.create(), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg44 = Steve44.addOrReplaceChild("leftLeg44", CubeListBuilder.create(), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm44 = Steve44.addOrReplaceChild("rightArm44", CubeListBuilder.create(), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm44 = Steve44.addOrReplaceChild("leftArm44", CubeListBuilder.create(), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body44 = Steve44.addOrReplaceChild("body44", CubeListBuilder.create(), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head44 = Steve44.addOrReplaceChild("head44", CubeListBuilder.create(), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition Propeller1 = rightala2.addOrReplaceChild("Propeller1", CubeListBuilder.create(), PartPose.offsetAndRotation(50.0F, -16.0F, 5.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition rightLeg45 = Propeller1.addOrReplaceChild("rightLeg45", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg45 = Propeller1.addOrReplaceChild("leftLeg45", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm45 = Propeller1.addOrReplaceChild("rightArm45", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r12 = rightArm45.addOrReplaceChild("rightArm_r12", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm45 = Propeller1.addOrReplaceChild("leftArm45", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r12 = leftArm45.addOrReplaceChild("leftArm_r12", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body45 = Propeller1.addOrReplaceChild("body45", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head45 = Propeller1.addOrReplaceChild("head45", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve46 = rightala2.addOrReplaceChild("Steve46", CubeListBuilder.create(), PartPose.offsetAndRotation(128.0F, 12.0F, 0.0311F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition rightLeg46 = Steve46.addOrReplaceChild("rightLeg46", CubeListBuilder.create(), PartPose.offset(-1.9F, -59.0F, 33.9689F));

		PartDefinition rightLeg_r11 = rightLeg46.addOrReplaceChild("rightLeg_r11", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition leftLeg46 = Steve46.addOrReplaceChild("leftLeg46", CubeListBuilder.create(), PartPose.offset(1.9F, -59.0F, 33.9689F));

		PartDefinition leftLeg_r11 = leftLeg46.addOrReplaceChild("leftLeg_r11", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition rightArm46 = Steve46.addOrReplaceChild("rightArm46", CubeListBuilder.create(), PartPose.offset(-5.0F, -69.0F, 33.9689F));

		PartDefinition rightArm_r13 = rightArm46.addOrReplaceChild("rightArm_r13", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 22.0F, 0.0F, 3.1416F, 0.0F, -3.1416F));

		PartDefinition leftArm46 = Steve46.addOrReplaceChild("leftArm46", CubeListBuilder.create(), PartPose.offset(5.0F, -69.0F, 33.9689F));

		PartDefinition leftArm_r13 = leftArm46.addOrReplaceChild("leftArm_r13", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 22.0F, 0.0F, 3.1416F, 0.0F, -3.1416F));

		PartDefinition body46 = Steve46.addOrReplaceChild("body46", CubeListBuilder.create(), PartPose.offset(0.0F, -71.0F, 33.9689F));

		PartDefinition body_r11 = body46.addOrReplaceChild("body_r11", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition head46 = Steve46.addOrReplaceChild("head46", CubeListBuilder.create(), PartPose.offset(0.0F, -71.0F, 33.9689F));

		PartDefinition head_r11 = head46.addOrReplaceChild("head_r11", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition Steve47 = rightala2.addOrReplaceChild("Steve47", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -22.0F, 56.0F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition rightLeg47 = Steve47.addOrReplaceChild("rightLeg47", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg47 = Steve47.addOrReplaceChild("leftLeg47", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm47 = Steve47.addOrReplaceChild("rightArm47", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm47 = Steve47.addOrReplaceChild("leftArm47", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body47 = Steve47.addOrReplaceChild("body47", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head47 = Steve47.addOrReplaceChild("head47", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition Steve40 = Full.addOrReplaceChild("Steve40", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -49.0F, 166.0F, -1.5708F, -1.0908F, 1.5708F));

		PartDefinition rightLeg40 = Steve40.addOrReplaceChild("rightLeg40", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg40 = Steve40.addOrReplaceChild("leftLeg40", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm40 = Steve40.addOrReplaceChild("rightArm40", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm40 = Steve40.addOrReplaceChild("leftArm40", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body40 = Steve40.addOrReplaceChild("body40", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head40 = Steve40.addOrReplaceChild("head40", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve39 = Full.addOrReplaceChild("Steve39", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -31.0F, 148.0F, -1.5708F, -1.0908F, 1.5708F));

		PartDefinition rightLeg39 = Steve39.addOrReplaceChild("rightLeg39", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg39 = Steve39.addOrReplaceChild("leftLeg39", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm39 = Steve39.addOrReplaceChild("rightArm39", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm39 = Steve39.addOrReplaceChild("leftArm39", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body39 = Steve39.addOrReplaceChild("body39", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head39 = Steve39.addOrReplaceChild("head39", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve38 = Full.addOrReplaceChild("Steve38", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -31.0F, 166.0F, -1.5708F, -1.0908F, 1.5708F));

		PartDefinition rightLeg38 = Steve38.addOrReplaceChild("rightLeg38", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r12 = rightLeg38.addOrReplaceChild("rightLeg_r12", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, 1.0F, 0.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition leftLeg38 = Steve38.addOrReplaceChild("leftLeg38", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r12 = leftLeg38.addOrReplaceChild("leftLeg_r12", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9F, 2.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition rightArm38 = Steve38.addOrReplaceChild("rightArm38", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm38 = Steve38.addOrReplaceChild("leftArm38", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body38 = Steve38.addOrReplaceChild("body38", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head38 = Steve38.addOrReplaceChild("head38", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition bone6 = Full.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone5 = bone6.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(31.25F, -29.0F, 79.0F));

		PartDefinition Side9 = bone5.addOrReplaceChild("Side9", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Steve26 = Side9.addOrReplaceChild("Steve26", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg26 = Steve26.addOrReplaceChild("rightLeg26", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r13 = rightLeg26.addOrReplaceChild("rightLeg_r13", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-125.1F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftLeg26 = Steve26.addOrReplaceChild("leftLeg26", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r13 = leftLeg26.addOrReplaceChild("leftLeg_r13", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-128.9F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition rightArm26 = Steve26.addOrReplaceChild("rightArm26", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r14 = rightArm26.addOrReplaceChild("rightArm_r14", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-122.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftArm26 = Steve26.addOrReplaceChild("leftArm26", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r14 = leftArm26.addOrReplaceChild("leftArm_r14", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-132.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition body26 = Steve26.addOrReplaceChild("body26", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r12 = body26.addOrReplaceChild("body_r12", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition head26 = Steve26.addOrReplaceChild("head26", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r12 = head26.addOrReplaceChild("head_r12", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition Steve27 = Side9.addOrReplaceChild("Steve27", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg27 = Steve27.addOrReplaceChild("rightLeg27", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg27 = Steve27.addOrReplaceChild("leftLeg27", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightArm27 = Steve27.addOrReplaceChild("rightArm27", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-19.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm27 = Steve27.addOrReplaceChild("leftArm27", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-17.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition body27 = Steve27.addOrReplaceChild("body27", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-20.4821F, 0.0F, -37.346F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head27 = Steve27.addOrReplaceChild("head27", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-20.4821F, -8.0F, -39.346F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Side10 = bone5.addOrReplaceChild("Side10", CubeListBuilder.create(), PartPose.offset(-22.75F, 0.0F, 0.0F));

		PartDefinition Steve28 = Side10.addOrReplaceChild("Steve28", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg28 = Steve28.addOrReplaceChild("rightLeg28", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r14 = rightLeg28.addOrReplaceChild("rightLeg_r14", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(86.1F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftLeg28 = Steve28.addOrReplaceChild("leftLeg28", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r14 = leftLeg28.addOrReplaceChild("leftLeg_r14", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(89.9F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition rightArm28 = Steve28.addOrReplaceChild("rightArm28", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r15 = rightArm28.addOrReplaceChild("rightArm_r15", CubeListBuilder.create().texOffs(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(83.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftArm28 = Steve28.addOrReplaceChild("leftArm28", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r15 = leftArm28.addOrReplaceChild("leftArm_r15", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(93.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition body28 = Steve28.addOrReplaceChild("body28", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r13 = body28.addOrReplaceChild("body_r13", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition head28 = Steve28.addOrReplaceChild("head28", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r13 = head28.addOrReplaceChild("head_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition Steve29 = Side10.addOrReplaceChild("Steve29", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg29 = Steve29.addOrReplaceChild("rightLeg29", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg29 = Steve29.addOrReplaceChild("leftLeg29", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm29 = Steve29.addOrReplaceChild("rightArm29", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm29 = Steve29.addOrReplaceChild("leftArm29", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body29 = Steve29.addOrReplaceChild("body29", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head29 = Steve29.addOrReplaceChild("head29", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition bone4 = bone6.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.375F, 0.0F, 51.025F, -3.1416F, 0.0F, 0.0F));

		PartDefinition Side7 = bone4.addOrReplaceChild("Side7", CubeListBuilder.create(), PartPose.offset(31.25F, 29.0F, -28.0F));

		PartDefinition Steve22 = Side7.addOrReplaceChild("Steve22", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg22 = Steve22.addOrReplaceChild("rightLeg22", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r15 = rightLeg22.addOrReplaceChild("rightLeg_r15", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-124.297F, -23.278F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftLeg22 = Steve22.addOrReplaceChild("leftLeg22", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r15 = leftLeg22.addOrReplaceChild("leftLeg_r15", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-129.703F, -26.722F, -6.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightArm22 = Steve22.addOrReplaceChild("rightArm22", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r16 = rightArm22.addOrReplaceChild("rightArm_r16", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-119.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftArm22 = Steve22.addOrReplaceChild("leftArm22", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r16 = leftArm22.addOrReplaceChild("leftArm_r16", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-134.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition body22 = Steve22.addOrReplaceChild("body22", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r14 = body22.addOrReplaceChild("body_r14", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition head22 = Steve22.addOrReplaceChild("head22", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r14 = head22.addOrReplaceChild("head_r14", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -28.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition Steve23 = Side7.addOrReplaceChild("Steve23", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg23 = Steve23.addOrReplaceChild("rightLeg23", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r16 = rightLeg23.addOrReplaceChild("rightLeg_r16", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg23 = Steve23.addOrReplaceChild("leftLeg23", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r16 = leftLeg23.addOrReplaceChild("leftLeg_r16", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm23 = Steve23.addOrReplaceChild("rightArm23", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r17 = rightArm23.addOrReplaceChild("rightArm_r17", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm23 = Steve23.addOrReplaceChild("leftArm23", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r17 = leftArm23.addOrReplaceChild("leftArm_r17", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body23 = Steve23.addOrReplaceChild("body23", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r15 = body23.addOrReplaceChild("body_r15", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head23 = Steve23.addOrReplaceChild("head23", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r15 = head23.addOrReplaceChild("head_r15", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, 4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Side8 = bone4.addOrReplaceChild("Side8", CubeListBuilder.create(), PartPose.offset(8.5F, 29.0F, -28.0F));

		PartDefinition Steve24 = Side8.addOrReplaceChild("Steve24", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg24 = Steve24.addOrReplaceChild("rightLeg24", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r17 = rightLeg24.addOrReplaceChild("rightLeg_r17", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(85.297F, -23.278F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftLeg24 = Steve24.addOrReplaceChild("leftLeg24", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r17 = leftLeg24.addOrReplaceChild("leftLeg_r17", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(90.703F, -26.722F, -6.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightArm24 = Steve24.addOrReplaceChild("rightArm24", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r18 = rightArm24.addOrReplaceChild("rightArm_r18", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(80.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftArm24 = Steve24.addOrReplaceChild("leftArm24", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r18 = leftArm24.addOrReplaceChild("leftArm_r18", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(95.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition body24 = Steve24.addOrReplaceChild("body24", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r16 = body24.addOrReplaceChild("body_r16", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition head24 = Steve24.addOrReplaceChild("head24", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r16 = head24.addOrReplaceChild("head_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -28.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition Steve25 = Side8.addOrReplaceChild("Steve25", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg25 = Steve25.addOrReplaceChild("rightLeg25", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r18 = rightLeg25.addOrReplaceChild("rightLeg_r18", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftLeg25 = Steve25.addOrReplaceChild("leftLeg25", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r18 = leftLeg25.addOrReplaceChild("leftLeg_r18", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightArm25 = Steve25.addOrReplaceChild("rightArm25", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r19 = rightArm25.addOrReplaceChild("rightArm_r19", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm25 = Steve25.addOrReplaceChild("leftArm25", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r19 = leftArm25.addOrReplaceChild("leftArm_r19", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body25 = Steve25.addOrReplaceChild("body25", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r17 = body25.addOrReplaceChild("body_r17", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head25 = Steve25.addOrReplaceChild("head25", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r17 = head25.addOrReplaceChild("head_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition rightala = Full.addOrReplaceChild("rightala", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 46.0F));

		PartDefinition Steve9 = rightala.addOrReplaceChild("Steve9", CubeListBuilder.create(), PartPose.offsetAndRotation(106.0F, -22.0F, 56.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition rightLeg9 = Steve9.addOrReplaceChild("rightLeg9", CubeListBuilder.create(), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg9 = Steve9.addOrReplaceChild("leftLeg9", CubeListBuilder.create(), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm9 = Steve9.addOrReplaceChild("rightArm9", CubeListBuilder.create(), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm9 = Steve9.addOrReplaceChild("leftArm9", CubeListBuilder.create(), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body9 = Steve9.addOrReplaceChild("body9", CubeListBuilder.create(), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head9 = Steve9.addOrReplaceChild("head9", CubeListBuilder.create(), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition Propeller3 = rightala.addOrReplaceChild("Propeller3", CubeListBuilder.create(), PartPose.offsetAndRotation(50.0F, -16.0F, 5.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition rightLeg21 = Propeller3.addOrReplaceChild("rightLeg21", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg21 = Propeller3.addOrReplaceChild("leftLeg21", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm21 = Propeller3.addOrReplaceChild("rightArm21", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r20 = rightArm21.addOrReplaceChild("rightArm_r20", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm21 = Propeller3.addOrReplaceChild("leftArm21", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r20 = leftArm21.addOrReplaceChild("leftArm_r20", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body21 = Propeller3.addOrReplaceChild("body21", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head21 = Propeller3.addOrReplaceChild("head21", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Steve10 = rightala.addOrReplaceChild("Steve10", CubeListBuilder.create(), PartPose.offsetAndRotation(106.0F, -22.0F, 56.0F, 0.0F, 1.5708F, -1.5708F));

		PartDefinition rightLeg10 = Steve10.addOrReplaceChild("rightLeg10", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg10 = Steve10.addOrReplaceChild("leftLeg10", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm10 = Steve10.addOrReplaceChild("rightArm10", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm10 = Steve10.addOrReplaceChild("leftArm10", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body10 = Steve10.addOrReplaceChild("body10", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head10 = Steve10.addOrReplaceChild("head10", CubeListBuilder.create(), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head_r18 = head10.addOrReplaceChild("head_r18", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition Steve8 = rightala.addOrReplaceChild("Steve8", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -22.0F, 56.0F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition rightLeg8 = Steve8.addOrReplaceChild("rightLeg8", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg8 = Steve8.addOrReplaceChild("leftLeg8", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm8 = Steve8.addOrReplaceChild("rightArm8", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm8 = Steve8.addOrReplaceChild("leftArm8", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body8 = Steve8.addOrReplaceChild("body8", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head8 = Steve8.addOrReplaceChild("head8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition leftala = Full.addOrReplaceChild("leftala", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 47.0F));

		PartDefinition Steve7 = leftala.addOrReplaceChild("Steve7", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -22.0F, 56.0F, 0.0F, -1.5708F, -1.5708F));

		PartDefinition rightLeg7 = Steve7.addOrReplaceChild("rightLeg7", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg7 = Steve7.addOrReplaceChild("leftLeg7", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-57.9F, -37.0F, 0.0F));

		PartDefinition rightArm7 = Steve7.addOrReplaceChild("rightArm7", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-51.0F, -47.0F, 0.0F));

		PartDefinition leftArm7 = Steve7.addOrReplaceChild("leftArm7", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-61.0F, -47.0F, 0.0F));

		PartDefinition body7 = Steve7.addOrReplaceChild("body7", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, -49.0F, 0.0F));

		PartDefinition head7 = Steve7.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, -49.0F, 0.0F));

		PartDefinition Steve19 = leftala.addOrReplaceChild("Steve19", CubeListBuilder.create(), PartPose.offsetAndRotation(-106.0F, -22.0F, 56.0F, 0.0F, 1.5708F, 1.5708F));

		PartDefinition rightLeg19 = Steve19.addOrReplaceChild("rightLeg19", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(54.1F, -37.0F, 0.0F));

		PartDefinition leftLeg19 = Steve19.addOrReplaceChild("leftLeg19", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(57.9F, -37.0F, 0.0F));

		PartDefinition rightArm19 = Steve19.addOrReplaceChild("rightArm19", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(51.0F, -47.0F, 0.0F));

		PartDefinition leftArm19 = Steve19.addOrReplaceChild("leftArm19", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(61.0F, -47.0F, 0.0F));

		PartDefinition body19 = Steve19.addOrReplaceChild("body19", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition head19 = Steve19.addOrReplaceChild("head19", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, -49.0F, 0.0F));

		PartDefinition Propeller2 = leftala.addOrReplaceChild("Propeller2", CubeListBuilder.create(), PartPose.offsetAndRotation(-49.0F, -16.0F, 5.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition rightLeg20 = Propeller2.addOrReplaceChild("rightLeg20", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg20 = Propeller2.addOrReplaceChild("leftLeg20", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm20 = Propeller2.addOrReplaceChild("rightArm20", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r21 = rightArm20.addOrReplaceChild("rightArm_r21", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm20 = Propeller2.addOrReplaceChild("leftArm20", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r21 = leftArm20.addOrReplaceChild("leftArm_r21", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body20 = Propeller2.addOrReplaceChild("body20", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head20 = Propeller2.addOrReplaceChild("head20", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition bone2 = Full.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.375F, 0.0F, -76.975F, -3.1416F, 0.0F, 0.0F));

		PartDefinition Side3 = bone2.addOrReplaceChild("Side3", CubeListBuilder.create(), PartPose.offset(31.25F, 29.0F, -28.0F));

		PartDefinition Steve11 = Side3.addOrReplaceChild("Steve11", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg11 = Steve11.addOrReplaceChild("rightLeg11", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r19 = rightLeg11.addOrReplaceChild("rightLeg_r19", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-128.1422F, -21.485F, -7.7574F, 0.7854F, 0.0F, 1.1345F));

		PartDefinition leftLeg11 = Steve11.addOrReplaceChild("leftLeg11", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r19 = leftLeg11.addOrReplaceChild("leftLeg_r19", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-133.5481F, -24.929F, -7.7574F, 0.7849F, -0.0308F, 1.1653F));

		PartDefinition rightArm11 = Steve11.addOrReplaceChild("rightArm11", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r22 = rightArm11.addOrReplaceChild("rightArm_r22", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-119.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition leftArm11 = Steve11.addOrReplaceChild("leftArm11", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r22 = leftArm11.addOrReplaceChild("leftArm_r22", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-134.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition body11 = Steve11.addOrReplaceChild("body11", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r18 = body11.addOrReplaceChild("body_r18", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -18.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition head11 = Steve11.addOrReplaceChild("head11", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r19 = head11.addOrReplaceChild("head_r19", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, -37.0F, -28.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition Steve12 = Side3.addOrReplaceChild("Steve12", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg12 = Steve12.addOrReplaceChild("rightLeg12", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r20 = rightLeg12.addOrReplaceChild("rightLeg_r20", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -4.5963F, -39.2027F, -0.6981F, 0.0F, 3.1416F));

		PartDefinition leftLeg12 = Steve12.addOrReplaceChild("leftLeg12", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r20 = leftLeg12.addOrReplaceChild("leftLeg_r20", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -4.5963F, -39.2027F, -0.6981F, 0.0F, 3.1416F));

		PartDefinition rightArm12 = Steve12.addOrReplaceChild("rightArm12", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r23 = rightArm12.addOrReplaceChild("rightArm_r23", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-17.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm12 = Steve12.addOrReplaceChild("leftArm12", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r23 = leftArm12.addOrReplaceChild("leftArm_r23", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.4821F, -4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body12 = Steve12.addOrReplaceChild("body12", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r19 = body12.addOrReplaceChild("body_r19", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, -6.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head12 = Steve12.addOrReplaceChild("head12", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r20 = head12.addOrReplaceChild("head_r20", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-16.4821F, 4.0F, -35.346F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Side4 = bone2.addOrReplaceChild("Side4", CubeListBuilder.create(), PartPose.offset(8.5F, 29.0F, -28.0F));

		PartDefinition Steve13 = Side4.addOrReplaceChild("Steve13", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg13 = Steve13.addOrReplaceChild("rightLeg13", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r21 = rightLeg13.addOrReplaceChild("rightLeg_r21", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(89.1422F, -21.485F, -7.7574F, 0.7854F, 0.0F, -1.1345F));

		PartDefinition leftLeg13 = Steve13.addOrReplaceChild("leftLeg13", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r21 = leftLeg13.addOrReplaceChild("leftLeg_r21", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(94.5481F, -24.929F, -7.6967F, 0.7854F, 0.0F, -1.1345F));

		PartDefinition rightArm13 = Steve13.addOrReplaceChild("rightArm13", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r24 = rightArm13.addOrReplaceChild("rightArm_r24", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(80.4643F, -29.5622F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition leftArm13 = Steve13.addOrReplaceChild("leftArm13", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r24 = leftArm13.addOrReplaceChild("leftArm_r24", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(95.5357F, -40.4378F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition body13 = Steve13.addOrReplaceChild("body13", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r20 = body13.addOrReplaceChild("body_r20", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -18.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition head13 = Steve13.addOrReplaceChild("head13", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r21 = head13.addOrReplaceChild("head_r21", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, -37.0F, -28.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition Steve14 = Side4.addOrReplaceChild("Steve14", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg14 = Steve14.addOrReplaceChild("rightLeg14", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg_r22 = rightLeg14.addOrReplaceChild("rightLeg_r22", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.2426F, -4.2426F, -0.7854F, 0.0F, 3.1416F));

		PartDefinition leftLeg14 = Steve14.addOrReplaceChild("leftLeg14", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg_r22 = leftLeg14.addOrReplaceChild("leftLeg_r22", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.2426F, -4.2426F, -0.7854F, 0.0F, -3.1416F));

		PartDefinition rightArm14 = Steve14.addOrReplaceChild("rightArm14", CubeListBuilder.create(), PartPose.offset(5.0F, 22.0F, 0.0F));

		PartDefinition rightArm_r25 = rightArm14.addOrReplaceChild("rightArm_r25", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition leftArm14 = Steve14.addOrReplaceChild("leftArm14", CubeListBuilder.create(), PartPose.offset(-5.0F, 22.0F, 0.0F));

		PartDefinition leftArm_r25 = leftArm14.addOrReplaceChild("leftArm_r25", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition body14 = Steve14.addOrReplaceChild("body14", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r21 = body14.addOrReplaceChild("body_r21", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition head14 = Steve14.addOrReplaceChild("head14", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head_r22 = head14.addOrReplaceChild("head_r22", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Steve6 = Full.addOrReplaceChild("Steve6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -32.0F));

		PartDefinition rightLeg6 = Steve6.addOrReplaceChild("rightLeg6", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r23 = rightLeg6.addOrReplaceChild("rightLeg_r23", CubeListBuilder.create().texOffs(16, 48).addBox(-2.1F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 1.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition leftLeg6 = Steve6.addOrReplaceChild("leftLeg6", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r23 = leftLeg6.addOrReplaceChild("leftLeg_r23", CubeListBuilder.create().texOffs(0, 16).addBox(-1.9F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 1.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition rightArm6 = Steve6.addOrReplaceChild("rightArm6", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r26 = rightArm6.addOrReplaceChild("rightArm_r26", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition leftArm6 = Steve6.addOrReplaceChild("leftArm6", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r26 = leftArm6.addOrReplaceChild("leftArm_r26", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition body6 = Steve6.addOrReplaceChild("body6", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head6 = Steve6.addOrReplaceChild("head6", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition bone = Full.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(31.25F, -29.0F, 15.0F));

		PartDefinition Side2 = bone.addOrReplaceChild("Side2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Steve4 = Side2.addOrReplaceChild("Steve4", CubeListBuilder.create(), PartPose.offset(111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg4 = Steve4.addOrReplaceChild("rightLeg4", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r24 = rightLeg4.addOrReplaceChild("rightLeg_r24", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-125.1F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftLeg4 = Steve4.addOrReplaceChild("leftLeg4", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r24 = leftLeg4.addOrReplaceChild("leftLeg_r24", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-128.9F, 25.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition rightArm4 = Steve4.addOrReplaceChild("rightArm4", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r27 = rightArm4.addOrReplaceChild("rightArm_r27", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-122.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition leftArm4 = Steve4.addOrReplaceChild("leftArm4", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r27 = leftArm4.addOrReplaceChild("leftArm_r27", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-132.0F, 35.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition body4 = Steve4.addOrReplaceChild("body4", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r22 = body4.addOrReplaceChild("body_r22", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition head4 = Steve4.addOrReplaceChild("head4", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r23 = head4.addOrReplaceChild("head_r23", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-127.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 2.0071F));

		PartDefinition Steve5 = Side2.addOrReplaceChild("Steve5", CubeListBuilder.create(), PartPose.offsetAndRotation(23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.1345F));

		PartDefinition rightLeg5 = Steve5.addOrReplaceChild("rightLeg5", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg5 = Steve5.addOrReplaceChild("leftLeg5", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-18.4821F, 0.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightArm5 = Steve5.addOrReplaceChild("rightArm5", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-19.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm5 = Steve5.addOrReplaceChild("leftArm5", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-17.4821F, -2.0F, -37.346F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition body5 = Steve5.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-20.4821F, 0.0F, -37.346F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head5 = Steve5.addOrReplaceChild("head5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-20.4821F, -8.0F, -39.346F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition Side = bone.addOrReplaceChild("Side", CubeListBuilder.create(), PartPose.offset(-22.75F, 0.0F, 0.0F));

		PartDefinition Steve3 = Side.addOrReplaceChild("Steve3", CubeListBuilder.create(), PartPose.offset(-111.0F, 0.0F, 0.05F));

		PartDefinition rightLeg3 = Steve3.addOrReplaceChild("rightLeg3", CubeListBuilder.create(), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition rightLeg_r25 = rightLeg3.addOrReplaceChild("rightLeg_r25", CubeListBuilder.create().texOffs(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(86.1F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftLeg3 = Steve3.addOrReplaceChild("leftLeg3", CubeListBuilder.create(), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg_r25 = leftLeg3.addOrReplaceChild("leftLeg_r25", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(89.9F, 25.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition rightArm3 = Steve3.addOrReplaceChild("rightArm3", CubeListBuilder.create(), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition rightArm_r28 = rightArm3.addOrReplaceChild("rightArm_r28", CubeListBuilder.create().texOffs(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(83.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition leftArm3 = Steve3.addOrReplaceChild("leftArm3", CubeListBuilder.create(), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition leftArm_r28 = leftArm3.addOrReplaceChild("leftArm_r28", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(93.0F, 35.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition body3 = Steve3.addOrReplaceChild("body3", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition body_r23 = body3.addOrReplaceChild("body_r23", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition head3 = Steve3.addOrReplaceChild("head3", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head_r24 = head3.addOrReplaceChild("head_r24", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(88.0F, 37.0F, 0.0F, 1.5708F, 0.0F, -2.0071F));

		PartDefinition Steve2 = Side.addOrReplaceChild("Steve2", CubeListBuilder.create(), PartPose.offsetAndRotation(-23.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -1.1345F));

		PartDefinition rightLeg2 = Steve2.addOrReplaceChild("rightLeg2", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition leftLeg2 = Steve2.addOrReplaceChild("leftLeg2", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

		PartDefinition rightArm2 = Steve2.addOrReplaceChild("rightArm2", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition leftArm2 = Steve2.addOrReplaceChild("leftArm2", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition body2 = Steve2.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head2 = Steve2.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SteveAneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Propeller1.zRot = getPropellerRotation(entity, limbSwing);
		Propeller2.zRot = getPropellerRotation(entity, limbSwing);
		Propeller3.zRot = -getPropellerRotation(entity, limbSwing);
		Propeller4.zRot = -getPropellerRotation(entity, limbSwing);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Full.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}