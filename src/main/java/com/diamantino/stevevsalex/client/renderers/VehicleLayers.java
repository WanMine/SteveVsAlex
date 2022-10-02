package com.diamantino.stevevsalex.client.renderers;

import com.diamantino.stevevsalex.client.models.SteveAneModel;
import com.diamantino.stevevsalex.client.models.SteveCopterModel;
import com.diamantino.stevevsalex.client.models.SteveOmbModel;
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
public class VehicleLayers {
    public static final ModelLayerLocation STEVE_COPTER_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "steve_copter"), "main");
    public static final ModelLayerLocation STEVE_ANE_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "steve_ane"), "main");

    public static final ModelLayerLocation STEVE_OMB_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "steve_omb"), "main");

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(STEVE_COPTER_LAYER, SteveCopterModel::createBodyLayer);
        event.registerLayerDefinition(STEVE_ANE_LAYER, SteveAneModel::createBodyLayer);
        event.registerLayerDefinition(STEVE_OMB_LAYER, SteveOmbModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();
        event.registerEntityRenderer(SVAEntityTypes.STEVE_COPTER.get(), context -> new VehichleRenderer<>(context, new SteveCopterModel<>(entityModelSet.bakeLayer(STEVE_COPTER_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.STEVE_ANE.get(), context -> new VehichleRenderer<>(context, new SteveAneModel<>(entityModelSet.bakeLayer(STEVE_ANE_LAYER)), 0.6f));
        event.registerEntityRenderer(SVAEntityTypes.STEVE_OMB.get(), context -> new BombRenderer<>(context, new SteveOmbModel<>(entityModelSet.bakeLayer(STEVE_OMB_LAYER)), 0.6f));
    }

    @SubscribeEvent
    public static void bakeModelLayers(EntityRenderersEvent.AddLayers event) {
        EntityModelSet entityModelSet = event.getEntityModels();
    }
}