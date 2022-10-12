package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.SteveVsAlex;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SVAConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, SteveVsAlex.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_STIVIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SVABlocks.STIVIUM_ORE_BLOCK.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SVABlocks.DEEPSLATE_STIVIUM_ORE_BLOCK.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ALEXIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SVABlocks.ALEXIUM_ORE_BLOCK.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SVABlocks.DEEPSLATE_ALEXIUM_ORE_BLOCK.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> STIVIUM_ORE = CONFIGURED_FEATURES.register("stivium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_STIVIUM_ORES.get(),4)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ALEXIUM_ORE = CONFIGURED_FEATURES.register("alexium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ALEXIUM_ORES.get(),4)));

    public static void init() {
        CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}