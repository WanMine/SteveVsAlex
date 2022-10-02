package com.diamantino.stevevsalex.client.renderers;

import com.diamantino.stevevsalex.entities.SteveOmbEntity;
import com.diamantino.stevevsalex.entities.base.BombEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class BombRenderer<T extends BombEntity> extends EntityRenderer<T> {

    protected final EntityModel<T> bombEntityModel;

    public BombRenderer(EntityRendererProvider.Context context, EntityModel<T> planeModel, float shadowSize) {
        super(context);
        this.bombEntityModel = planeModel;
        this.shadowRadius = shadowSize;
    }

    public static float getBombRotation(BombEntity entity, float partialTicks) {
        return Mth.lerp(partialTicks, entity.bombRotationOld, entity.bombRotationNew);
    }

    @Override
    public void render(@NotNull T bombEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.375D, 0.0D);
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(180));

        poseStack.translate(0, 0, 0.9);

        poseStack.translate(0, -1.1, 0);

        VertexConsumer vertexConsumer = buffer.getBuffer(bombEntityModel.renderType(getTexture(bombEntity)));
        bombEntityModel.setupAnim(bombEntity, partialTicks, 0, 0, 0, 0);
        bombEntityModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.pushPose();
        poseStack.popPose();
        poseStack.popPose();

        super.render(bombEntity, 0, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BombEntity entity) {
        return getTexture(entity);
    }

    public static ResourceLocation getTexture(BombEntity entity) {
        if (cachedTextures.containsKey(entity.bombName)) {
            return cachedTextures.get(entity.bombName);
        }

        ResourceLocation texture = new ResourceLocation(MODID, "textures/entity/" + entity.bombName + ".png");

        cachedTextures.put(entity.bombName, texture);
        return texture;
    }

    public static final Map<String, ResourceLocation> cachedTextures = new HashMap<>();}