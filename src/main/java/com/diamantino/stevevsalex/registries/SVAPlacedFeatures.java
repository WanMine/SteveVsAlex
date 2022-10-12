package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.SteveVsAlex;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SVAPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SteveVsAlex.MODID);

    public static final RegistryObject<PlacedFeature> STIVIUM_ORE_PLACED = PLACED_FEATURES.register("stivium_ore_placed",
            () -> new PlacedFeature(SVAConfiguredFeatures.STIVIUM_ORE.getHolder().get(),
                    commonOrePlacement(4, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))));

    public static final RegistryObject<PlacedFeature> ALEXIUM_ORE_PLACED = PLACED_FEATURES.register("alexium_ore_placed",
            () -> new PlacedFeature(SVAConfiguredFeatures.ALEXIUM_ORE.getHolder().get(),
                    commonOrePlacement(4, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void init() {
        PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}