package com.diamantino.stevevsalex.containers;

import com.diamantino.stevevsalex.client.gui.slots.AmmoCraftingResultSlot;
import com.diamantino.stevevsalex.network.packets.CycleItemsPacket;
import com.diamantino.stevevsalex.recipes.AmmoWorkbenchRecipe;
import com.diamantino.stevevsalex.registries.SVABlocks;
import com.diamantino.stevevsalex.registries.SVAContainers;
import com.diamantino.stevevsalex.registries.SVARecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AmmoWorkbenchContainer extends AbstractContainerMenu {
    private final ItemStackHandler itemHandler;
    private final ItemStackHandler resultItemHandler = new ItemStackHandler();
    private final ContainerLevelAccess usabilityTest;
    private final Player player;
    public final DataSlot selectedRecipe;

    public final List<AmmoWorkbenchRecipe> recipeList;

    public AmmoWorkbenchContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, BlockPos.ZERO, new ItemStackHandler(2), DataSlot.standalone());
    }

    public AmmoWorkbenchContainer(int id, Inventory playerInventory, BlockPos blockPos, ItemStackHandler itemHandler, DataSlot selectedRecipe) {
        super(SVAContainers.AMMO_WORKBENCH.get(), id);
        this.player = playerInventory.player;
        this.itemHandler = itemHandler;
        this.usabilityTest = ContainerLevelAccess.create(player.level, blockPos);
        this.recipeList = player.level.getRecipeManager().getAllRecipesFor(SVARecipeTypes.AMMO_WORKBENCH_RECIPE_TYPE.get());
        this.selectedRecipe = selectedRecipe;

        addSlot(new SlotItemHandler(itemHandler, 0, 28, 47));
        addSlot(new SlotItemHandler(itemHandler, 1, 75, 47));
        addSlot(new AmmoCraftingResultSlot(player, this, resultItemHandler, 0, 134, 47));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        addDataSlot(selectedRecipe);
        updateCraftingResult();
    }

    public void cycleItems(CycleItemsPacket.Type type) {
        int prevSelectedRecipe = selectedRecipe.get();
        ItemStack ingredient = itemHandler.getStackInSlot(0);
        ItemStack material = itemHandler.getStackInSlot(1);
        switch (type) {
            case CRAFTING_LEFT:
                do {
                    if (selectedRecipe.get() == 0) {
                        selectedRecipe.set(recipeList.size() - 1);
                    } else {
                        selectedRecipe.set(selectedRecipe.get() - 1);
                    }
                } while (selectedRecipe.get() != prevSelectedRecipe && !recipeList.get(selectedRecipe.get()).canCraft(ingredient, material));
                break;
            case CRAFTING_RIGHT:
                do {
                    if (selectedRecipe.get() == recipeList.size() - 1) {
                        selectedRecipe.set(0);
                    } else {
                        selectedRecipe.set(selectedRecipe.get() + 1);
                    }
                } while (selectedRecipe.get() != prevSelectedRecipe && !recipeList.get(selectedRecipe.get()).canCraft(ingredient, material));
        }

        updateCraftingResult();
    }

    public void onCrafting() {
        if (!player.level.isClientSide) {
            AmmoWorkbenchRecipe recipe = recipeList.get(selectedRecipe.get());
            itemHandler.extractItem(0, recipe.explosiveItemAmount(), false);
            itemHandler.extractItem(1, recipe.hullAmount(), false);
            updateCraftingResult();
        }
    }

    protected void updateCraftingResult() {
        if (!this.player.level.isClientSide) {
            ServerPlayer serverPlayerEntity = (ServerPlayer) this.player;
            ItemStack result = ItemStack.EMPTY;
            ItemStack ingredientStack = itemHandler.getStackInSlot(0);
            ItemStack materialStack = itemHandler.getStackInSlot(1);

            AmmoWorkbenchRecipe recipe = recipeList.get(selectedRecipe.get());

            if (recipe.canCraft(ingredientStack, materialStack))
                result = recipe.result().copy();

            resultItemHandler.setStackInSlot(0, result);
            serverPlayerEntity.connection.send(new ClientboundContainerSetSlotPacket(containerId, 0, 2, result));
        }
    }

    @Override
    public boolean stillValid(@NotNull Player playerIn) {
        return stillValid(usabilityTest, playerIn, SVABlocks.AMMO_WORKBENCH_BLOCK.get());
    }

    @Override
    public void clicked(int slotId, int dragType, @NotNull ClickType clickTypeIn, @NotNull Player player) {
        super.clicked(slotId, dragType, clickTypeIn, player);
        updateCraftingResult();
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        ItemStack originalItemStack;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack inputItemStack = slot.getItem();
            originalItemStack = inputItemStack.copy();

            if (index == 2) { // test for result slot
                inputItemStack.getItem().onCraftedBy(inputItemStack, playerIn.level, playerIn);

                // move result to the player inventory
                if (!moveItemStackTo(inputItemStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(inputItemStack, originalItemStack);

            } else if (index >= 3 && index < 39) { // test for slot in player inventory

                if (!moveItemStackTo(inputItemStack, 0, 2, false)) { // move it to recipe input

                    if (index < 30) { // test for main player inventory (excluding hotbar)

                        if (!moveItemStackTo(inputItemStack, 30, 39, false)) { // move it to the hotbar
                            return ItemStack.EMPTY;
                        }

                    } else if (!moveItemStackTo(inputItemStack, 3, 30, false)) { // if it's in hotbar, move it to the main inventory
                        return ItemStack.EMPTY;
                    }

                }

            } else if (!moveItemStackTo(inputItemStack, 3, 39, false)) { // if it's the recipe input, move it to the player inventory
                return ItemStack.EMPTY;
            }

            if (inputItemStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (inputItemStack.getCount() == originalItemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, inputItemStack);
            if (index == 2) {
                playerIn.drop(inputItemStack, false);
            }
        }

//        updateCraftingResult();
        return ItemStack.EMPTY;
    }
}