package com.diamantino.stevevsalex.upgrades.engines;

import com.diamantino.stevevsalex.client.gui.slots.FuelSlot;
import com.diamantino.stevevsalex.client.gui.VehicleInventoryScreen;
import com.diamantino.stevevsalex.entities.base.PlaneEntity;
import com.diamantino.stevevsalex.registries.SVAItems;
import com.diamantino.stevevsalex.registries.SVAUpgrades;
import com.diamantino.stevevsalex.upgrades.VehicleEngineUpgrade;
import com.diamantino.stevevsalex.utils.ClientUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class FurnaceVehicleEngineUpgrade extends VehicleEngineUpgrade {

    public final ItemStackHandler itemStackHandler = new ItemStackHandler();
    public final LazyOptional<ItemStackHandler> itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
    public int burnTime;
    public int burnTimeTotal;

    public FurnaceVehicleEngineUpgrade(PlaneEntity planeEntity) {
        super(SVAUpgrades.COMBUSTION_VEHICLE_ENGINE.get(), planeEntity);
    }

    @Override
    public void tick() {
        if (burnTime > 0) {
            burnTime -= planeEntity.getFuelCost();
            updateClient();
        } else if (planeEntity.getThrottle() > 0) {
            ItemStack itemStack = itemStackHandler.getStackInSlot(0);
            int itemBurnTime = ForgeHooks.getBurnTime(itemStack, null);
            if (itemBurnTime > 0) {
                burnTimeTotal = itemBurnTime;
                burnTime = itemBurnTime;
                if (itemStack.hasCraftingRemainingItem()) {
                    itemStackHandler.setStackInSlot(0, itemStack.getCraftingRemainingItem());
                } else {
                    itemStackHandler.extractItem(0, 1, false);
                }
                updateClient();
            }
        }
    }

    @Override
    public boolean isPowered() {
        return burnTime > 0;
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        compound.put("item", itemStackHandler.serializeNBT());
        compound.putInt("burnTime", burnTime);
        compound.putInt("burnTimeTotal", burnTimeTotal);
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        itemStackHandler.deserializeNBT(compound.getCompound("item"));
        burnTime = compound.getInt("burnTime");
        burnTimeTotal = compound.getInt("burnTimeTotal");
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {
        buffer.writeItem(itemStackHandler.getStackInSlot(0));
        buffer.writeVarInt(burnTime);
        buffer.writeVarInt(burnTimeTotal);
    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {
        itemStackHandler.setStackInSlot(0, buffer.readItem());
        burnTime = buffer.readVarInt();
        burnTimeTotal = buffer.readVarInt();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(SVAItems.COMBUSTION_VEHICLE_ENGINE.get());
        planeEntity.spawnAtLocation(itemStackHandler.getStackInSlot(0));
    }

    @Override
    public void renderPowerHUD(PoseStack matrixStack, HumanoidArm side, int scaledWidth, int scaledHeight, float partialTicks) {
        int i = scaledWidth / 2;
        Minecraft mc = Minecraft.getInstance();
        if (side == HumanoidArm.LEFT) {
            ClientUtils.blit(matrixStack, -90, i - 91 - 29, scaledHeight - 40, 0, 44, 22, 40);
        } else {
            ClientUtils.blit(matrixStack, -90, i + 91, scaledHeight - 40, 0, 44, 22, 40);
        }

        if (burnTime > 0) {
            int burnTimeTotal2 = burnTimeTotal == 0 ? 200 : burnTimeTotal;
            int burnLeftScaled = burnTime * 13 / burnTimeTotal2;
            if (side == HumanoidArm.LEFT) {
                // render on left side
                ClientUtils.blit(matrixStack, -90, i - 91 - 29 + 4, scaledHeight - 40 + 16 - burnLeftScaled, 22, 56 - burnLeftScaled, 14, burnLeftScaled + 1);
            } else {
                // render on right side
                ClientUtils.blit(matrixStack, -90, i + 91 + 4, scaledHeight - 40 + 16 - burnLeftScaled, 22, 56 - burnLeftScaled, 14, burnLeftScaled + 1);
            }
        }

        ItemStack fuelStack = itemStackHandler.getStackInSlot(0);
        if (!fuelStack.isEmpty()) {
            int i2 = scaledHeight - 16 - 3;
            if (side == HumanoidArm.LEFT) {
                ClientUtils.renderHotbarItem(matrixStack, i - 91 - 26, i2, partialTicks, fuelStack, mc);
            } else {
                ClientUtils.renderHotbarItem(matrixStack, i + 91 + 3, i2, partialTicks, fuelStack, mc);
            }
        }
    }

    @Override
    public void addContainerData(Function<Slot, Slot> addSlot, Function<DataSlot, DataSlot> addDataSlot) {
        addSlot.apply(new FuelSlot(itemStackHandler, 0, 152, 62));
    }

    @Override
    public void renderScreenBg(PoseStack poseStack, int x, int y, float partialTicks, VehicleInventoryScreen screen) {
        screen.blit(poseStack, screen.getGuiLeft() + 151, screen.getGuiTop() + 44, 208, 0, 18, 35);

        if (burnTime > 0) {
            int burnLeftScaled = burnTime * 13 / (burnTimeTotal == 0 ? 200 : burnTimeTotal);
            screen.blit(poseStack, screen.getGuiLeft() + 152, screen.getGuiTop() + 57 - burnLeftScaled, 208, 47 - burnLeftScaled, 14, burnLeftScaled + 1);
        }
    }
}