package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.recipes.AmmoWorkbenchRecipe;
import com.diamantino.stevevsalex.recipes.VehicleWorkbenchRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVARecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MODID);

    public static void init() {
        RECIPE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<RecipeType<VehicleWorkbenchRecipe>> VEHICLE_WORKBENCH_RECIPE_TYPE = RECIPE_TYPES.register("vehicle_workbench", () -> RecipeType.simple(new ResourceLocation(MODID, "vehicle_workbench")));
    public static final RegistryObject<RecipeType<AmmoWorkbenchRecipe>> AMMO_WORKBENCH_RECIPE_TYPE = RECIPE_TYPES.register("ammo_workbench", () -> RecipeType.simple(new ResourceLocation(MODID, "ammo_workbench")));
}
