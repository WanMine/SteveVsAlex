package com.diamantino.stevevsalex.registries;

import com.diamantino.stevevsalex.recipes.AmmoWorkbenchRecipeSerializer;
import com.diamantino.stevevsalex.recipes.VehicleWorkbenchRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class SVARecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static void init() {
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<RecipeSerializer<?>> VEHICLE_WORKBENCH_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("vehicle_workbench", VehicleWorkbenchRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> AMMO_WORKBENCH_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("ammo_workbench", AmmoWorkbenchRecipeSerializer::new);
}
