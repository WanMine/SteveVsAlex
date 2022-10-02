package com.diamantino.stevevsalex.client.renderers;

import com.diamantino.stevevsalex.entities.base.ProjectileEntity;
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

public class ProjectileRenderer<T extends ProjectileEntity> extends EntityRenderer<T> {

    protected final EntityModel<T> projectileEntityModel;

    public ProjectileRenderer(EntityRendererProvider.Context context, EntityModel<T> planeModel, float shadowSize) {
        super(context);
        this.projectileEntityModel = planeModel;
        this.shadowRadius = shadowSize;
    }

    @Override
    public void render(@NotNull T projectileEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.translate(0, -0.3, 0);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(projectileEntity.getXRot()));

        VertexConsumer vertexConsumer = buffer.getBuffer(projectileEntityModel.renderType(getTexture(projectileEntity)));
        projectileEntityModel.setupAnim(projectileEntity, partialTicks, 0, 0, 0, 0);
        projectileEntityModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
        super.render(projectileEntity, 0, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ProjectileEntity entity) {
        return getTexture(entity);
    }

    public static ResourceLocation getTexture(ProjectileEntity entity) {
        if (cachedTextures.containsKey(entity.projectileName)) {
            return cachedTextures.get(entity.projectileName);
        }

        ResourceLocation texture = new ResourceLocation(MODID, "textures/entity/" + entity.projectileName + ".png");

        cachedTextures.put(entity.projectileName, texture);
        return texture;
    }

    public static final Map<String, ResourceLocation> cachedTextures = new HashMap<>();}