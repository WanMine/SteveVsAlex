package com.diamantino.stevevsalex.recipes;

import com.diamantino.stevevsalex.registries.SVARecipeSerializers;
import com.diamantino.stevevsalex.registries.SVARecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record AmmoWorkbenchRecipe(ResourceLocation id,
                                  Ingredient explosiveItem, int explosiveItemAmount,
                                  Ingredient hullItem, int hullAmount,
                                  ItemStack result) implements Recipe<Container> {

    public boolean canCraft(ItemStack explosiveItemStack, ItemStack hullStack) {
        return explosiveItemStack.getCount() >= explosiveItemAmount && hullStack.getCount() >= hullAmount && explosiveItem.test(explosiveItemStack) && hullItem.test(hullStack);
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return result;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SVARecipeSerializers.AMMO_WORKBENCH_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return SVARecipeTypes.AMMO_WORKBENCH_RECIPE_TYPE.get();
    }

    @Override
    public boolean matches(@NotNull Container p_77569_1_, @NotNull Level p_77569_2_) {
        return false;
    }

    @Override
    public ItemStack assemble(@NotNull Container p_77572_1_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return false;
    }
}