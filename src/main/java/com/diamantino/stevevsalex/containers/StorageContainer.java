package com.diamantino.stevevsalex.containers;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class StorageContainer extends AbstractContainerMenu {

    public final int size;

    public StorageContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(id, playerInventory, new ItemStackHandler(27));
    }

    public StorageContainer(int id, Inventory playerInventory, IItemHandler itemHandler) {
        super(SVAContainers.STORAGE.get(), id);
        addSlots(itemHandler, playerInventory, this::addSlot);
        size = itemHandler.getSlots();
    }

    @Override
    public boolean stillValid(Player playerIn) {
        Entity entity = playerIn.getVehicle();
        if (entity instanceof PlaneEntity planeEntity && entity.isAlive()) {
            return planeEntity.hasStorageUpgrade();
        }

        return false;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < size) {
                if (!this.moveItemStackTo(itemstack1, size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public static void addSlots(IItemHandler itemHandler, Inventory playerInventory, Consumer<Slot> addSlotFunction) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                addSlotFunction.accept(new SlotItemHandler(itemHandler, column + row * 9, 12 + column * 18, 18 + row * 18));
                addSlotFunction.accept(new Slot(playerInventory, column + row * 9 + 9, 12 + column * 18, 168 - (4 - row) * 18 - 10));
            }
        }

        for (int column = 0; column < 9; ++column) {
            addSlotFunction.accept(new Slot(playerInventory, column, 12 + column * 18, 168 - 24));
        }
    }
}