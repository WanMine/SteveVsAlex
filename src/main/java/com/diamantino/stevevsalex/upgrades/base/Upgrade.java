package com.diamantino.stevevsalex.upgrades.base;

import com.diamantino.stevevsalex.client.gui.PlaneInventoryScreen;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.function.Function;

public abstract class Upgrade extends CapabilityProvider<Upgrade> implements INBTSerializable<CompoundTag> {

    private final UpgradeType type;
    protected final PlaneEntity planeEntity;
    public boolean updateClient = false;
    public boolean removed = false;

    public PlaneEntity getPlaneEntity() {
        return planeEntity;
    }

    public Upgrade(UpgradeType type, PlaneEntity planeEntity) {
        super(Upgrade.class);
        this.type = type;
        this.planeEntity = planeEntity;
    }

    /**
     * Call it when data is changed, and it needs to be synced to the client.
     * If called on a server, results in calling writePacket method on a server and readPacket on a client
     */
    protected void updateClient() {
        updateClient = true;
    }

    /**
     * Call it to remove this upgrade from the plane.
     */
    public void remove() {
        removed = true;
        invalidateCaps();
    }

    public final UpgradeType getType() {
        return type;
    }

    /**
     * Called when passenger right clicks with item.
     *
     * @param event The right click event
     */
    public void onItemRightClick(PlayerInteractEvent.RightClickItem event) {}

    /**
     * Called every tick by plane entity.
     */
    public void tick() {}

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {}

    public void onApply(ItemStack itemStack, Player playerEntity) {}

    /**
     * Called on the server.
     */
    public abstract void writePacket(FriendlyByteBuf buffer);

    /**
     * Called on the client.
     */
    public abstract void readPacket(FriendlyByteBuf buffer);

    /**
     * Called when upgrade is removed using wrench.
     */
    public abstract void onRemoved();

    public boolean hasStorage() {
        return false;
    }

    public void openStorageGui(ServerPlayer player) {}

    public void addContainerData(Function<Slot, Slot> addSlot, Function<DataSlot, DataSlot> addDataSlot) {}

    public void renderScreen(PoseStack poseStack, int mouseX, int mouseY, float partialTicks, PlaneInventoryScreen planeInventoryScreen) {}

    public void renderScreenBg(PoseStack poseStack, int x, int y, float partialTicks, PlaneInventoryScreen screen) {}
}