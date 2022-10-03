package com.diamantino.stevevsalex.registries;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class SVAConfigs {
    public static ForgeConfigSpec.IntValue TURN_THRESHOLD;
    public static ForgeConfigSpec.IntValue PLANE_FUEL_COST;
    public static ForgeConfigSpec.IntValue HELICOPTER_FUEL_COST;
    public static ForgeConfigSpec.DoubleValue PLANE_CAMERA_DISTANCE_MULTIPLIER;
    public static ForgeConfigSpec.DoubleValue HELI_CAMERA_DISTANCE_MULTIPLIER;

    public static void init() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Planes settings").push("general");

        TURN_THRESHOLD = COMMON_BUILDER
                .comment("For controllers, a threshold for the joystick movement of the plane")
                .defineInRange("turnThreshold", 20, 0, 90);
        PLANE_FUEL_COST = COMMON_BUILDER
                .comment("Fuel cost of a small plane")
                .defineInRange("plane_fuel_cost", 3, 0, Integer.MAX_VALUE);
        HELICOPTER_FUEL_COST = COMMON_BUILDER
                .comment("Fuel cost of a helicopter")
                .defineInRange("helicopter_fuel_cost", 6, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());

        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_BUILDER.comment("Planes client settings").push("general_client");
        PLANE_CAMERA_DISTANCE_MULTIPLIER = CLIENT_BUILDER
                .comment("Third person camera zoom on a plane").defineInRange("plane_camera_distance_multiplier", 1.0, 1.0, 2.0);
        HELI_CAMERA_DISTANCE_MULTIPLIER = CLIENT_BUILDER
                .comment("Third person camera zoom on a helicopter").defineInRange("heli_camera_distance_multiplier", 1.2, 1.0, 2.0);
        CLIENT_BUILDER.pop();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }
}
