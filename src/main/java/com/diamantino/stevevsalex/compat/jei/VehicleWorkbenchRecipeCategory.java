package com.diamantino.stevevsalex.compat.jei;

import com.diamantino.stevevsalex.recipes.VehicleWorkbenchRecipe;
import com.diamantino.stevevsalex.registries.SVABlocks;
import com.diamantino.stevevsalex.registries.SVAItems;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class VehicleWorkbenchRecipeCategory implements IRecipeCategory<VehicleWorkbenchRecipe> {

    public static final RecipeType<VehicleWorkbenchRecipe> RECIPE_TYPE = RecipeType.create(MODID, "vehicle_workbench", VehicleWorkbenchRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public VehicleWorkbenchRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(new ResourceLocation(ModIds.JEI_ID, "textures/gui/gui_vanilla.png"), 0, 168, 125, 18);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SVAItems.VEHICLE_WORKBENCH.get()));
    }

    @Override
    public @NotNull RecipeType<VehicleWorkbenchRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return SVABlocks.VEHICLE_WORKBENCH_BLOCK.get().getName();
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, VehicleWorkbenchRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(Ingredient.of(Arrays.stream(recipe.movementItem().getItems()).map(itemStack -> new ItemStack(itemStack.getItem(), recipe.movementItemAmount()))));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 1).addIngredients(Ingredient.of(Arrays.stream(recipe.hullItem().getItems()).map(itemStack -> new ItemStack(itemStack.getItem(), recipe.hullAmount()))));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 108, 1).addItemStack(recipe.result());
    }
}