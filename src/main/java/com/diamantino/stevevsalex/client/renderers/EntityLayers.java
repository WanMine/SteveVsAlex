package com.diamantino.stevevsalex.client.renderers;

import com.diamantino.stevevsalex.client.models.PlaneModel;
import com.diamantino.stevevsalex.client.models.ArrowModel;
import com.diamantino.stevevsalex.client.models.HelicopterModel;
import com.diamantino.stevevsalex.client.models.BombModel;
import com.diamantino.stevevsalex.registries.SVAEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityLayers {
    //Vehicles
    public static final ModelLayerLocation HELICOPTER_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "helicopter"), "main");
    public static final ModelLayerLocation PLANE_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "plane"), "main");

    //Bombs
    public static final ModelLayerLocation BOMB_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "bomb"), "main");

    //Projectiles
    public static final ModelLayerLocation ARROW_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "arrow"), "main");

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HELICOPTER_LAYER, HelicopterModel::createBodyLayer);
        event.registerLayerDefinition(PLANE_LAYER, PlaneModel::createBodyLayer);
        event.registerLayerDefinition(BOMB_LAYER, BombModel::createBodyLayer);
        event.registerLayerDefinition(ARROW_LAYER, ArrowModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();

        //Steve
        event.registerEntityRenderer(SVAEntityTypes.STEVE_COPTER.get(), context -> new VehicleRenderer<>(context, new HelicopterModel<>(entityModelSet.bakeLayer(HELICOPTER_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.STEVE_ANE.get(), context -> new VehicleRenderer<>(context, new PlaneModel<>(entityModelSet.bakeLayer(PLANE_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.STEVE_OMB.get(), context -> new BombRenderer<>(context, new BombModel<>(entityModelSet.bakeLayer(BOMB_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.STEVE_ARROW.get(), context -> new ProjectileRenderer<>(context, new ArrowModel<>(entityModelSet.bakeLayer(ARROW_LAYER)), 0f));

        //Alex
        event.registerEntityRenderer(SVAEntityTypes.ALEX_COPTER.get(), context -> new VehicleRenderer<>(context, new HelicopterModel<>(entityModelSet.bakeLayer(HELICOPTER_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.ALEX_ANE.get(), context -> new VehicleRenderer<>(context, new PlaneModel<>(entityModelSet.bakeLayer(PLANE_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.ALEX_OMB.get(), context -> new BombRenderer<>(context, new BombModel<>(entityModelSet.bakeLayer(BOMB_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.ALEX_ARROW.get(), context -> new ProjectileRenderer<>(context, new ArrowModel<>(entityModelSet.bakeLayer(ARROW_LAYER)), 0f));
    }

    @SubscribeEvent
    public static void bakeModelLayers(EntityRenderersEvent.AddLayers event) {
        EntityModelSet entityModelSet = event.getEntityModels();
    }
}