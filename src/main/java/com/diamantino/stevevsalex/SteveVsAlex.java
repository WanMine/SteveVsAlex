package com.diamantino.stevevsalex;

import com.diamantino.stevevsalex.events.ClientEventHandler;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        SVAUpgrades.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().register(SVAConfigs.class);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SVAUpgrades.registerUpgradeItem(SVAItems.FLOATING_PAD_UPGRADE.get(), SVAUpgrades.FLOATING_PAD_UPGRADE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.ROCKET_BOOSTER_UPGRADE.get(), SVAUpgrades.ROCKET_BOOSTER_UPGRADE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.HEAL_VEHICLE.get(), SVAUpgrades.HEAL_VEHICLE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.REINFORCED_ARMOR_UPGRADE.get(), SVAUpgrades.REINFORCED_ARMOR_UPGRADE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.SOLAR_GENERATOR_UPGRADE.get(), SVAUpgrades.SOLAR_GENERATOR_UPGRADE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.COMBUSTION_VEHICLE_ENGINE.get(), SVAUpgrades.COMBUSTION_VEHICLE_ENGINE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.ELECTRIC_VEHICLE_ENGINE.get(), SVAUpgrades.ELECTRIC_VEHICLE_ENGINE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.STORAGE_UPGRADE.get(), SVAUpgrades.STORAGE_UPGRADE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.JUKEBOX_UPGRADE.get(), SVAUpgrades.JUKEBOX_UPGRADE.get());
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientEventHandler.clientSetup();
    }
}