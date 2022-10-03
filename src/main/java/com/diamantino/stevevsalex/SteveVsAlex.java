package com.diamantino.stevevsalex;

import com.diamantino.stevevsalex.events.ClientEventHandler;
import com.diamantino.stevevsalex.network.SVANetworking;
import com.diamantino.stevevsalex.registries.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Items;
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
            SVAUpgrades.registerUpgradeItem(SVAItems.FLOATY_BEDDING.get(), SVAUpgrades.FLOATY_BEDDING.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.BOOSTER.get(), SVAUpgrades.BOOSTER.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.HEALING.get(), SVAUpgrades.HEALING.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.ARMOR.get(), SVAUpgrades.ARMOR.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.SOLAR_PANEL.get(), SVAUpgrades.SOLAR_PANEL.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.FURNACE_ENGINE.get(), SVAUpgrades.FURNACE_ENGINE.get());
            SVAUpgrades.registerUpgradeItem(SVAItems.ELECTRIC_ENGINE.get(), SVAUpgrades.ELECTRIC_ENGINE.get());
            SVAUpgrades.registerUpgradeItem(Items.CHEST, SVAUpgrades.CHEST.get());
            SVAUpgrades.registerUpgradeItem(Items.JUKEBOX, SVAUpgrades.JUKEBOX.get());
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientEventHandler.clientSetup();
    }
}