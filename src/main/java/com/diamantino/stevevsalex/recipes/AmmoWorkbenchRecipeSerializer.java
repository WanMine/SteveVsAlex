package com.diamantino.stevevsalex.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AmmoWorkbenchRecipeSerializer implements RecipeSerializer<AmmoWorkbenchRecipe> {

    @Override
    public @NotNull AmmoWorkbenchRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
        Ingredient explosiveItem = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "explosive_item"));
        int explosiveItemAmount = GsonHelper.getAsInt(json, "explosive_item_amount");
        Ingredient hull = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "hull"));
        int hullAmount = GsonHelper.getAsInt(json, "hull_amount");
        ItemStack result = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result")).getDefaultInstance();
        return new AmmoWorkbenchRecipe(id, explosiveItem, explosiveItemAmount, hull, hullAmount, result);
    }

    @Nullable
    @Override
    public AmmoWorkbenchRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buffer) {
        Ingredient explosiveItem = Ingredient.fromNetwork(buffer);
        int explosiveItemAmount = buffer.readVarInt();
        Ingredient hull = Ingredient.fromNetwork(buffer);
        int hullAmount = buffer.readVarInt();
        ItemStack result = buffer.readItem();
        return new AmmoWorkbenchRecipe(id, explosiveItem, explosiveItemAmount, hull, hullAmount, result);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buffer, AmmoWorkbenchRecipe recipe) {
        recipe.explosiveItem().toNetwork(buffer);
        buffer.writeVarInt(recipe.explosiveItemAmount());
        recipe.hullItem().toNetwork(buffer);
        buffer.writeVarInt(recipe.hullAmount());
        buffer.writeItem(recipe.result());
    }
}