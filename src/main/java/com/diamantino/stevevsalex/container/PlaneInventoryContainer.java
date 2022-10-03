package com.diamantino.stevevsalex.container;

import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAContainers;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class PlaneInventoryContainer extends AbstractContainerMenu {

    public PlaneEntity planeEntity;

    public PlaneInventoryContainer(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        super(SVAContainers.PLANE_INVENTORY.get(), id);
        if (Minecraft.getInstance().level.getEntity(buffer.readVarInt()) instanceof PlaneEntity planeEntity) {
            this.planeEntity = planeEntity;
        }
        initSlotsUpgrades(playerInventory);
    }

    public PlaneInventoryContainer(int id, Inventory playerInventory, PlaneEntity planeEntity) {
        super(SVAContainers.PLANE_INVENTORY.get(), id);
        this.planeEntity = planeEntity;
        initSlotsUpgrades(playerInventory);
    }

    private void initSlotsUpgrades(Inventory playerInventory) {
        if (planeEntity != null) {
            for (Upgrade upgrade : planeEntity.upgrades.values()) {
                upgrade.addContainerData(this::addSlot, this::addDataSlot);
            }
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getVehicle() instanceof PlaneEntity;
    }
}