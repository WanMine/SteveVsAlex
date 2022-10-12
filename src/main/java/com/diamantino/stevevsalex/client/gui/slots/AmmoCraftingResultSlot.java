package com.diamantino.stevevsalex.client.gui.slots;

import com.diamantino.stevevsalex.containers.AmmoWorkbenchContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AmmoCraftingResultSlot extends SlotItemHandler {

    private final AmmoWorkbenchContainer container;
    private final Player player;

    public AmmoCraftingResultSlot(Player player, AmmoWorkbenchContainer container, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.player = player;
        this.container = container;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(@NotNull Player thePlayer, ItemStack stack) {
        stack.onCraftedBy(player.level, player, 1);
        container.onCrafting();
    }
}