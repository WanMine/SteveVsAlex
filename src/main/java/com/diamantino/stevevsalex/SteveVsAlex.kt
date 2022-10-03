package com.diamantino.stevevsalex

import com.diamantino.stevevsalex.events.ClientEventHandler
import com.diamantino.stevevsalex.network.SVANetworking
import com.diamantino.stevevsalex.registries.*
import com.mojang.logging.LogUtils
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(SteveVsAlex.MODID)
class SteveVsAlex {
    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus
        SVAEntityTypes.init()
        SVAContainers.init()
        SVAItems.init()
        SVAConfigs.init()
        SVASoundEvents.init()
        SVADataSerializers.init()
        SVANetworking.init()
        modEventBus.addListener { event: FMLCommonSetupEvent -> commonSetup(event) }
        modEventBus.addListener { event: FMLClientSetupEvent -> clientSetup(event) }
        FMLJavaModLoadingContext.get().modEventBus.register(SVAConfigs::class)
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}
    private fun clientSetup(event: FMLClientSetupEvent) {
        ClientEventHandler.clientSetup()
    }

    companion object {
        const val MODID = "stevevsalex"
        val LOGGER = LogUtils.getLogger()
    }
}