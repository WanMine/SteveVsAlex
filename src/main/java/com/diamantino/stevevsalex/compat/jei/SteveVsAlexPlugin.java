package com.diamantino.stevevsalex.compat.jei;

import com.diamantino.stevevsalex.client.gui.AmmoWorkbenchScreen;
import com.diamantino.stevevsalex.client.gui.VehicleWorkbenchScreen;
import com.diamantino.stevevsalex.containers.AmmoWorkbenchContainer;
import com.diamantino.stevevsalex.containers.VehicleWorkbenchContainer;
import com.diamantino.stevevsalex.registries.SVAContainers;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVARecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

@JeiPlugin
public class SteveVsAlexPlugin implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new VehicleWorkbenchRecipeCategory(guiHelper));
        registration.addRecipeCategories(new AmmoWorkbenchRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        registration.addRecipes(VehicleWorkbenchRecipeCategory.RECIPE_TYPE, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(SVARecipeTypes.VEHICLE_WORKBENCH_RECIPE_TYPE.get()));
        registration.addRecipes(AmmoWorkbenchRecipeCategory.RECIPE_TYPE, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(SVARecipeTypes.AMMO_WORKBENCH_RECIPE_TYPE.get()));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(VehicleWorkbenchContainer.class, SVAContainers.VEHICLE_WORKBENCH.get(), VehicleWorkbenchRecipeCategory.RECIPE_TYPE, 0, 2, 3, 36);
        registration.addRecipeTransferHandler(AmmoWorkbenchContainer.class, SVAContainers.AMMO_WORKBENCH.get(), AmmoWorkbenchRecipeCategory.RECIPE_TYPE, 0, 2, 3, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(SVAItems.VEHICLE_WORKBENCH.get()), VehicleWorkbenchRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(SVAItems.AMMO_WORKBENCH.get()), AmmoWorkbenchRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(VehicleWorkbenchScreen.class, 102, 48, 22, 15, VehicleWorkbenchRecipeCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(AmmoWorkbenchScreen.class, 102, 48, 22, 15, AmmoWorkbenchRecipeCategory.RECIPE_TYPE);
    }
}