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

public class VehicleWorkbenchRecipeSerializer implements RecipeSerializer<VehicleWorkbenchRecipe> {

    @Override
    public @NotNull VehicleWorkbenchRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
        Ingredient movementItem = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "movement_item"));
        int movementItemAmount = GsonHelper.getAsInt(json, "movement_item_amount");
        Ingredient hull = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "hull"));
        int hullAmount = GsonHelper.getAsInt(json, "hull_amount");
        ItemStack result = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result")).getDefaultInstance();
        return new VehicleWorkbenchRecipe(id, movementItem, movementItemAmount, hull, hullAmount, result);
    }

    @Nullable
    @Override
    public VehicleWorkbenchRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buffer) {
        Ingredient movementItem = Ingredient.fromNetwork(buffer);
        int movementItemAmount = buffer.readVarInt();
        Ingredient hull = Ingredient.fromNetwork(buffer);
        int hullAmount = buffer.readVarInt();
        ItemStack result = buffer.readItem();
        return new VehicleWorkbenchRecipe(id, movementItem, movementItemAmount, hull, hullAmount, result);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buffer, VehicleWorkbenchRecipe recipe) {
        recipe.movementItem().toNetwork(buffer);
        buffer.writeVarInt(recipe.movementItemAmount());
        recipe.hullItem().toNetwork(buffer);
        buffer.writeVarInt(recipe.hullAmount());
        buffer.writeItem(recipe.result());
    }
}