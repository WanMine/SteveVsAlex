package com.diamantino.stevevsalex.client.gui.slots;

import com.diamantino.stevevsalex.containers.VehicleWorkbenchContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class VehicleCraftingResultSlot extends SlotItemHandler {

    private final VehicleWorkbenchContainer container;
    private final Player player;

    public VehicleCraftingResultSlot(Player player, VehicleWorkbenchContainer container, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.player = player;
        this.container = container;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(Player thePlayer, ItemStack stack) {
        stack.onCraftedBy(player.level, player, 1);
        container.onCrafting();
    }
}