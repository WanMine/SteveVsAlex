package com.diamantino.stevevsalex;

import com.diamantino.stevevsalex.events.ClientEventHandler;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(SteveVsAlex.MODID)
public class SteveVsAlex {

    public static final String MODID = "stevevsalex";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SteveVsAlex() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SVAEntityTypes.init();
        SVAContainers.init();
        SVAItems.init();
        SVAConfigs.init();
        SVASoundEvents.init();
        SVADataSerializers.init();
        SVANetworking.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(SVAConfigs.class);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientEventHandler.clientSetup();
    }
}
