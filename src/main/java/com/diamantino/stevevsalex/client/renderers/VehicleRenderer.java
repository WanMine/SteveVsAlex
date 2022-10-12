package com.diamantino.stevevsalex.client.renderers;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.utils.MathUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class VehicleRenderer<T extends PlaneEntity> extends EntityRenderer<T> {

    protected final EntityModel<T> vehichleEntityModel;

    public VehicleRenderer(EntityRendererProvider.Context context, EntityModel<T> planeModel, float shadowSize) {
        super(context);
        this.vehichleEntityModel = planeModel;
        this.shadowRadius = shadowSize;
    }

    public static float getPropellerRotation(PlaneEntity entity, float partialTicks) {
        return Mth.lerp(partialTicks, entity.propellerRotationOld, entity.propellerRotationNew);
    }

    public static float getMinigunRotation(PlaneEntity entity, float partialTicks) {
        return Mth.lerp(partialTicks, entity.minigunRotationOld, entity.minigunRotationNew);
    }

    @Override
    public void render(T planeEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.375D, 0.0D);
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(180));

        Quaternion q = MathUtils.lerpQ(partialTicks, planeEntity.getQ_Prev(), planeEntity.getQ_Client());
        poseStack.mulPose(q);
        EntityType<?> entityType = planeEntity.getType();

        poseStack.translate(0, 0, 0.9);

        float timeSinceHitWithPartial = (float) planeEntity.getTimeSinceHit() - partialTicks;

        if (timeSinceHitWithPartial > 0.0F) {
            float angle = Mth.clamp(timeSinceHitWithPartial / 10.0F, -30, 30);
            timeSinceHitWithPartial = planeEntity.tickCount + partialTicks;
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(timeSinceHitWithPartial) * angle));
        }

        poseStack.translate(0, -1.1, 0);

        VertexConsumer vertexConsumer = buffer.getBuffer(vehichleEntityModel.renderType(getTexture(planeEntity)));
        vehichleEntityModel.setupAnim(planeEntity, partialTicks, 0, 0, 0, 0);
        vehichleEntityModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.pushPose();
        poseStack.popPose();
        poseStack.popPose();

        super.render(planeEntity, 0, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PlaneEntity entity) {
        return getTexture(entity);
    }

    public static ResourceLocation getTexture(PlaneEntity entity) {
        if (cachedTextures.containsKey(entity.vehichleName)) {
            return cachedTextures.get(entity.vehichleName);
        }

        ResourceLocation texture = new ResourceLocation(MODID, "textures/entity/" + entity.vehichleName + ".png");

        cachedTextures.put(entity.vehichleName, texture);
        return texture;
    }

    public static final Map<String, ResourceLocation> cachedTextures = new HashMap<>();}