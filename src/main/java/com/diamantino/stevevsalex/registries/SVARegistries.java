package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.upgrades.base.UpgradeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SVARegistries {

    public static final ResourceLocation UPGRADE_TYPES_NAME = new ResourceLocation(MODID, "upgrade_types");
    public static Supplier<IForgeRegistry<UpgradeType>> UPGRADE_TYPES;

    @SubscribeEvent
    public static void registerRegistries(final NewRegistryEvent event) {
        UPGRADE_TYPES = event.create(new RegistryBuilder<UpgradeType>().setName(UPGRADE_TYPES_NAME));
    }
}