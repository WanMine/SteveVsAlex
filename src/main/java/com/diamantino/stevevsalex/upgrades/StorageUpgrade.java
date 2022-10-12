package com.diamantino.stevevsalex.upgrades;

import com.diamantino.stevevsalex.containers.StorageContainer;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.base.Upgrade;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.diamantino.stevevsalex.SteveVsAlex.MODID;

public class StorageUpgrade extends Upgrade implements MenuProvider {

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(27);
    public final LazyOptional<ItemStackHandler> itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);

    public StorageUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.STORAGE_UPGRADE.get(), planeEntity);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {

    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {

    }

    @Override
    public void onRemoved() {
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            ItemStack itemStack = itemStackHandler.getStackInSlot(i);
            if (!itemStack.isEmpty()) {
                planeEntity.spawnAtLocation(itemStack);
            }
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(MODID + ":chest");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventoryIn, @NotNull Player playerEntity) {
        return new StorageContainer(id, playerInventoryIn, itemStackHandler);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public boolean hasStorage() {
        return true;
    }

    @Override
    public void openStorageGui(ServerPlayer player) {
        NetworkHooks.openScreen(player, this);
    }
}